package com.jpmc.theater.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	public Optional<Movie> findByTitle(final String title);
}
