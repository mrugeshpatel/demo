package com.jpmc.theater.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.model.Theater;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ShowingRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TheaterRepository theaterRepository;
	
	@Autowired
	private ShowingRepository showingRepository;
	
	private Theater theater;
	private Movie movie;
	private Showing showing;
	
	@BeforeEach
    public void setUp() {
		theater = theaterRepository.save(new Theater("Regal", "New Brunswik"));
		movie= movieRepository.save(new Movie("X-Man", "X-man des", "1 :30", 15.00, 1));
    }
	
	@Test
	public void testSave() {
		
		showing = new Showing(theater, movie, LocalDateTime.now() , 1) ;
		Showing save = showingRepository.save(showing);
		assertNotNull(save);

	}
		
	@AfterEach
	public void tearDown() {
		movieRepository.deleteAll();
		theaterRepository.deleteAll();
		showingRepository.deleteAll();
		movie = null;
		theater = null;
		showing = null;
    }

}
