package com.jpmc.theater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpmc.theater.exception.MovieNotFoundException;
import com.jpmc.theater.exception.ShowNotFoundException;
import com.jpmc.theater.exception.TheaterNotFoundException;
import com.jpmc.theater.service.ShowBookingService;
import com.jpmc.theater.vo.BookingRequest;
import com.jpmc.theater.vo.BookingResponse;
import com.jpmc.theater.vo.ReservationResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/theater/")
public class ShowBookingController {
	
	@Autowired
	private ShowBookingService showingService;
	
	@PostMapping(value = "reserve")
    public ResponseEntity<BookingResponse> reserve(@RequestBody final BookingRequest booking) {
        log.info("Starting ticket booking", booking.toString());
        try {
        	BookingResponse bookingResponse = showingService.book(booking);
        	return new ResponseEntity<BookingResponse>(bookingResponse, HttpStatus.CREATED);
        }catch(TheaterNotFoundException | MovieNotFoundException | ShowNotFoundException ex ) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping(value = "list")
    public ResponseEntity<List<ReservationResponse>> list() {
        log.info("Listing reservations");
        try {
        	List<ReservationResponse> reservationList = showingService.list();
        	return new ResponseEntity<List<ReservationResponse>>(reservationList, HttpStatus.OK);
        }catch(TheaterNotFoundException | MovieNotFoundException | ShowNotFoundException ex ) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
	
	
}
