package com.jpmc.theater.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.repository.ShowingRepository;

import lombok.NonNull;

@Service
public class ShowingService {

	@Autowired
	ShowingRepository showingRepository;
	
	public Optional<Showing> findByTheaterAndMovieAndShowStartDateTime(final Theater theater, final Movie movie, final @NonNull LocalDateTime showStartDateTime){
		return showingRepository.findByTheaterAndMovieAndShowStartDateTime(theater, movie, showStartDateTime);
	}
	
	public Showing save(Showing Showing) {
		return showingRepository.save(Showing);
	}
	 
	public long getShowingCount() {
		return showingRepository.count();
	}
}
