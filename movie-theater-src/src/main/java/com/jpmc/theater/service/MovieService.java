package com.jpmc.theater.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.repository.MovieRepository;

@Service
public class MovieService {
	
	
	 @Autowired
	 private MovieRepository movieRepository;
	 
	 public Optional<Movie> findByTitle(final String title) {
		 return movieRepository.findByTitle(title);
	 }
	 
	 public Movie save(Movie movie) {
	     return movieRepository.save(movie);
	 }
	 
	 public long getMovieCount() {
		 return movieRepository.count();
	 }
}
