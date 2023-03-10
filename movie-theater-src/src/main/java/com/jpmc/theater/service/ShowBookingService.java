package com.jpmc.theater.service;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpmc.theater.exception.MovieNotFoundException;
import com.jpmc.theater.exception.ShowNotFoundException;
import com.jpmc.theater.exception.TheaterNotFoundException;
import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.vo.BookingRequest;
import com.jpmc.theater.vo.BookingResponse;
import com.jpmc.theater.vo.ReservationResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShowBookingService {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ShowingService showingService;
	
	@Autowired
	private DiscountService discountService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TheaterService theaterService;
	
	@Transactional
	public BookingResponse book(final BookingRequest booking) throws ParseException {
		try {
			Customer customer = saveCustomer(booking);
			Theater theater = getTheater(booking);
			Movie movie = getMovie(booking);
			Showing showing = getShow(booking, theater, movie);
			
			double discount = discountService.getDiscount(movie, showing);
			double gross = movie.getTicketPrice() * booking.getNoOfSeat();
			double total = gross - discount;
			
			Reservation newRes = new Reservation(customer, showing, booking.getNoOfSeat(), gross, discount, total);
			Reservation reservation = reservationService.save(newRes);
			log.info("Reservation saved in database successfully");
			return BookingResponse.builder().theater(theater.getName()).movie(movie.getTitle())
								  .show(showing.getShowStartDateTime()).sequenceOfTheDay(showing.getSequenceOfTheDay())
								  .duration(movie.getRunningTime()).customer(customer.getName())
								  .movieFees(movie.getTicketPrice()).noOfSeat(reservation.getAudienceCount())
								  .grossAmount(gross).discount(discount).total(total)
								  .build();
		}catch(TheaterNotFoundException | MovieNotFoundException | ShowNotFoundException ex ) {
			log.error(ex.getMessage());
			throw ex;
		}catch (Exception ex) {
			log.error(ex.getMessage());
			throw ex;
		}
	}
	
	public List<ReservationResponse> list() {
		Iterable<Reservation> list = reservationService.list();
		if(list == null) {
			log.info("No reservation found in database");
			return new ArrayList<ReservationResponse>();
		}
		Iterator<Reservation> iterator = list.iterator();
		List<ReservationResponse> responseList = new ArrayList<>();
		while (iterator.hasNext()) {
			Reservation reservation = iterator.next();
		    ReservationResponse res = ReservationResponse.builder().customer(reservation.getCustomer().getName()).theater(reservation.getShowing().getTheater().getName())
					 .movie(reservation.getShowing().getMovie().getTitle()).showtime(reservation.getShowing().getShowStartDateTime())
					 .movieFees(reservation.getShowing().getMovie().getTicketPrice()).audienceCount(reservation.getAudienceCount())
					 .grossAmount(reservation.getGrossAmount()).discount(reservation.getDiscount()).total(reservation.getTotal())
					 .build();
					responseList.add(res);
		}
		return responseList;
	}
	
	/**
	 * Get Show
	 * @param booking
	 * @param theater
	 * @param movie
	 * @return show
	 */
	private Showing getShow(final BookingRequest booking, Theater theater, Movie movie) {
		// Show
		Optional<Showing> showingOptional = showingService.findByTheaterAndMovieAndShowStartDateTime(theater,movie, booking.getShowStartTime());
		if(showingOptional.isEmpty())
			throw new ShowNotFoundException("Show Not Found Exception");
		Showing showing = showingOptional.get();
		
		log.info("Retrieved Show from database successfully");
		return showing;
	}
	
	/**\
	 * Get Moving
	 * @param booking
	 * @return movie
	 */
	private Movie getMovie(final BookingRequest booking) {
		Optional<Movie> movieOptional = movieService.findByTitle(booking.getMovie());
		if(movieOptional.isEmpty())
			throw new MovieNotFoundException("Movie Not Found Exception");
		Movie movie = movieOptional.get();
		log.info("Retrieved Movie from database successfully");
		return movie;
	}

	/*
	 * Get Theater
	 */
	private Theater getTheater(final BookingRequest booking) {
		Optional<Theater> theaterOptional = theaterService.findTheaterByNameAndLocation(booking.getTheater(), booking.getLocation());
		if(theaterOptional.isEmpty())
			throw new TheaterNotFoundException("Theater Not Found Exception");
		
		log.info("Retrieved Theater from database successfully");
		Theater theater = theaterOptional.get();
		return theater;
	}

	/**
	 * Save Customer
	 * @param booking
	 * @return custome
	 */
	private Customer saveCustomer(final BookingRequest booking) {
		// Customer 
		Customer customer = customerService.save(new Customer(booking.getCustomer()));
		log.info("Customer saved in database");
		return customer;
	}
}
