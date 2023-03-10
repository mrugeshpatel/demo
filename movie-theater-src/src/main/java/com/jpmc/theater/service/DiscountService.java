package com.jpmc.theater.service;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiscountService {

	 public double getDiscount(final Movie movie, final Showing showing) throws ParseException {
	    double specialDiscount = 0;
	    log.info("Checking discount if any..");
	    if (Constants.MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
	        specialDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
	    }
		if (showing.getSequenceOfTheDay() == 1) {
			specialDiscount = specialDiscount > 3 ? specialDiscount : 3;  // $3 discount for 1st show
		}else if (showing.getSequenceOfTheDay() == 2) {
		    specialDiscount = specialDiscount > 2 ? specialDiscount : 2;  // $2 discount for 2nd show
		}else if (showing.getSequenceOfTheDay() == 7) {
		    specialDiscount = specialDiscount > 1 ? specialDiscount : 1;  // $1 discount for 7th show
		}
		LocalDateTime showStartTime = showing.getShowStartDateTime();
		//TODO : Below code can be moved into different table (let's table for discount with start and end date)
		if(showStartTime.getHour() >= 11 && showStartTime.getHour() <= 16) {
			double timeDis = movie.getTicketPrice() * 0.25;  // 25% discount for special movie
        	specialDiscount = specialDiscount > timeDis ? specialDiscount : timeDis; 
		}
		log.info("Discount : " + specialDiscount);
	    return specialDiscount;
	 }   
}
