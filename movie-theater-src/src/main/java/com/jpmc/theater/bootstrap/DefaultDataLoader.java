package com.jpmc.theater.bootstrap;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.ShowingService;
import com.jpmc.theater.service.TheaterService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultDataLoader implements CommandLineRunner {
	
	@Autowired
    private TheaterService theaterService;
	
	@Autowired
    private MovieService movieService;
	
	@Autowired
	private ShowingService showingService;
	
	@Autowired
    private Faker faker;

    @Override
    public void run(String... args) {
        List<Theater> theaters = loadTheaterData();
        List<Movie> movies = loadMovieData();
        loadShowingData(theaters, movies);
    }

    private List<Theater> loadTheaterData() {
    	List<Theater> theaters = new ArrayList<>();
        if (theaterService.getTheaterCount() == 0) {
            log.info("Saving the default theaters into the database.");
            for (int x = 0; x < 1; x++) {
            	Theater theater = theaterService.save(createNewTheater());
            	theaters.add(theater);
            }
        } else {
            log.info("Default theaters are already present in the database.");
        }
        return theaters;
    }

    private Theater createNewTheater() {
        return new Theater("Regal","South Brunswick");
    }
    
    private List<Movie> loadMovieData() {
    	List<Movie> movies = new ArrayList<>();
    	if (movieService.getMovieCount() == 0) {
            log.info("Saving the default movies into the database.");
            Movie movie = movieService.save(createNewMovie("Spider-Man: No Way Home","Spider-Man: No Way Home",1));
            movies.add(movie);
            movie = movieService.save(createNewMovie("Turning Red","Turning Red",2));
            movies.add(movie);
            movie = movieService.save(createNewMovie("The Batman","The Batman",3));
            movies.add(movie);
        } else {
            log.info("Default movies are already present in the database.");
        }
    	return movies;
    }

    private Movie createNewMovie(String name, String description, Integer specialCode) {
        return new Movie(name, description, "1 hour 30 mins",
        		faker.number().randomDouble(2, 5, 35), specialCode);
    }
    
    private void loadShowingData(List<Theater> theaters, List<Movie> movies) {
    	if (showingService.getShowingCount() == 0 && theaters != null && movies != null) {
            log.info("Saving the default showings into the database.");
            LocalDateTime localDateTime = LocalDateTime.now();
            for (Theater theater : theaters) {
            	for (Movie movie : movies) {
            		Showing showing1 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0)),1);
            		showingService.save(showing1);
            		
            		Showing showing2 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(11, 0)),2);
            		showingService.save(showing2);
            		
            		Showing showing3 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(12, 50)),3);
            		showingService.save(showing3);
            		
            		Showing showing4 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(14, 30)),4);
            		showingService.save(showing4);
            		
            		Showing showing5 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(16, 10)),5);
            		showingService.save(showing5);
            		
            		Showing showing6 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(17, 50)),6);
            		showingService.save(showing6);
            		
            		Showing showing7 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(19, 30)),7);
            		showingService.save(showing7);
            		
            		Showing showing8 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(21, 10)),8);
            		showingService.save(showing8);
            		
            		Showing showing9 = createNewShowing(theater, movie, 
            				LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(23, 10)),9);
            		showingService.save(showing9);
                }
            }
        } else {
            log.info("Default showings not saved in database. ");
        }
    }
    
    private Showing createNewShowing(Theater theater, Movie movie, LocalDateTime showStartTime, Integer sequenceOfTheDay) {
    	return new Showing(theater, movie, showStartTime, sequenceOfTheDay);
    }
}
