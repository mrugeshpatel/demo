package com.jpmc.theater.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class BookingResponse {

	@NonNull
	private String theater;
	
	@NonNull
	private String movie;
	
	@NonNull
	private Integer sequenceOfTheDay;
	
	@NonNull
	private LocalDateTime show;
	
	@NonNull
	private String duration;
	
	@NonNull
	private String customer;
	
	@NonNull
	private Double movieFees;
	
	@NonNull
	private Integer noOfSeat;
	
	@NonNull
	private Double grossAmount;
	
	@NonNull
	private Double discount;
	
	@NonNull
	private Double total;
}
