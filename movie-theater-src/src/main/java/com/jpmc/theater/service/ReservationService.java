package com.jpmc.theater.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.repository.ReservationRepository;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public Reservation save(final Reservation reservation) {
        return reservationRepository.save(reservation);
    }
	
	public Iterable<Reservation> list() {
         return reservationRepository.findAll();
    }
	
	public Optional<List<Reservation>> findByCustomer(final Long customerId) {
        return reservationRepository.findByCustomer(customerId);
    }

}
