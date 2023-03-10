package com.jpmc.theater.client;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jpmc.theater.vo.BookingRequest;
import com.jpmc.theater.vo.BookingResponse;
import com.jpmc.theater.vo.ReservationResponse;

import lombok.extern.slf4j.Slf4j;

/*
 * Rest Client
 */
@Slf4j
@Component
public class ShowBookingClient {

	 static RestTemplate restTemplate = new RestTemplate();
	 
	 public static void main(String[] args) {
		 reserve();
		 list();
     }
	 
	 private static void reserve() {
		 BookingRequest bookingRequest = createRequest();
		 HttpEntity<BookingRequest> request = new HttpEntity<>(bookingRequest);	 
		 BookingResponse body = restTemplate.exchange("http://localhost:8080/api/v1/theater/reserve", 
				 							HttpMethod.POST, request, BookingResponse.class,request).getBody();
	     log.info("Response : " + body.toString());
	 }
	
	 private static void list() {
		 ReservationResponse[] responses = restTemplate.
				 getForEntity("http://localhost:8080/api/v1/theater/list", ReservationResponse[].class).getBody();
		 for (ReservationResponse response : responses) {
			
			 log.info("Response : " + response.toString());
		 }
	 }
	 
	 private static BookingRequest createRequest() {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0));
		BookingRequest request = BookingRequest.builder()
				   .customer("Mike").theater("Regal").location("South Brunswick")
				   .showStartTime(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0)))
				   .movie("The Batman").noOfSeat(3).build();
		return request;
	}
}
