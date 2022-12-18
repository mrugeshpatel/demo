package com.jpmc.theater.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Theater;

@Repository
public interface TheaterRepository extends CrudRepository<Theater, Integer> {

	public Optional<Theater> findByNameAndLocation(final String name, final String location);
}
