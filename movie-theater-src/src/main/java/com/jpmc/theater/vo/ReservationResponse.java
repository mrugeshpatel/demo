package com.jpmc.theater.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@ToString
public class ReservationResponse {

	public String customer;
	
	public String theater;
	
	public String movie;
	
	public LocalDateTime showtime;
	
	public double movieFees;
	
	private Integer audienceCount;
   
    private Double grossAmount;
    
    private Double discount;
    
    private Double total; 
}
