package com.jpmc.theater.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class BookingRequest {

	@NonNull
	private String customer;
	
	@NonNull
	private String theater;
	
	@NonNull
	private String location;
	
	@NonNull
	private LocalDateTime showStartTime;
	
	@NonNull
	private String movie;
		
	private int noOfSeat;
	
}
