package com.jpmc.theater.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpmc.theater.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
