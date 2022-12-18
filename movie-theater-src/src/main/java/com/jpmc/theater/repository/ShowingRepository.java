package com.jpmc.theater.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;

@Repository
public interface ShowingRepository extends CrudRepository<Showing, Integer> {

	public Optional<Showing> findByTheaterAndMovieAndShowStartDateTime(final Theater theater, final Movie movie, final LocalDateTime showStartDateTime);
}
