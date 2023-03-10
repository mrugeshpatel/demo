package com.jpmc.theater.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

	//public Optional<List<Reservation>> finadAll();
	
	public Optional<List<Reservation>> findByCustomer(final Long customerId);
}
