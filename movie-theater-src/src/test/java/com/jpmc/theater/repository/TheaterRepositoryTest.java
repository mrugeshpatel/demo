package com.jpmc.theater.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jpmc.theater.model.Theater;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TheaterRepositoryTest {

	@Autowired
	private TheaterRepository theaterRepository;
	
	private Theater theater;
	
	@BeforeEach
    public void setUp() {
		theater = new Theater("Regal", "New Brunswik");
    }
	
	@AfterEach
	public void tearDown() {
		theaterRepository.deleteAll();
		theater = null;
    }
	
	@Test
	public void testSave() {
		Theater save = theaterRepository.save(theater);
		assertNotNull(save);
	}
	
	@Test
	public void testFindByNameAndLocation() {
		theaterRepository.save(theater);
		Optional<Theater> t = theaterRepository.findByNameAndLocation("X-Man","New Brunswik");
		assertFalse(t.isEmpty());
	}
}
