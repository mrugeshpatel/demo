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

import com.jpmc.theater.model.Movie;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	
	private Movie movie;
	
	@BeforeEach
    public void setUp() {
		movie = new Movie("X-Man", "X-man des", "1 hour 30 mins", 15.00, 1);
    }
	
	@AfterEach
	public void tearDown() {
		movieRepository.deleteAll();
		movie = null;
    }
	
	@Test
	public void testSave() {
		Movie m = movieRepository.save(movie);
		assertNotNull(m);
	}
	
	@Test
	public void testFindByTitle() {
		movieRepository.save(movie);
		Optional<Movie> m = movieRepository.findByTitle("X-Man");
		assertFalse(m.isEmpty());
	}
}
