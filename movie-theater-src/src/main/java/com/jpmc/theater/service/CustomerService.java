package com.jpmc.theater.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.repository.CustomerRepository;


@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public long getCustomerCount() {
        return customerRepository.count();
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(final int customerId) {
        return customerRepository.findById(customerId);
    }
}
