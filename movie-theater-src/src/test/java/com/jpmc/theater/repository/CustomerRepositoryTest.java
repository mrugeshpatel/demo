package com.jpmc.theater.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jpmc.theater.model.Customer;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryTest {

	Customer customer;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@BeforeEach
    public void setUp() {
		customer = new Customer("Alex");
    }
	
	@AfterEach
	public void tearDown() {
		customerRepository.deleteAll();
		customer = null;
    }
	
	@Test
	public void testSave() {
		customerRepository.save(customer);
	}

}
