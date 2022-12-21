package com.jpmc.theater.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Theater;
import com.jpmc.theater.repository.TheaterRepository;

@Service
public class TheaterService {
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	public Theater save(Theater theater) {
        return theaterRepository.save(theater);
    }
	
	public Optional<Theater> findTheaterByNameAndLocation(final String name, final String location) {
	     return theaterRepository.findByNameAndLocation(name, location);
	}
	
	public long getTheaterCount() {
        return theaterRepository.count();
    }

}
