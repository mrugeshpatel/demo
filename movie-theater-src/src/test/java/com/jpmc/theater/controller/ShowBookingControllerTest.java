package com.jpmc.theater.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.theater.vo.BookingRequest;
import com.jpmc.theater.vo.BookingResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShowBookingControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	WebApplicationContext context;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	LocalDateTime localDateTime = LocalDateTime.now();
	
	@Test
	public void reserve() throws Exception {
		BookingRequest request = createRequest();
		test1(request);
		// 11 o'clock show
		test2(request);
		// Special code
		test3(request);
	}
	
	@Test
	public void list() throws Exception {
		//ckMvcRequestBuilders.get("").
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
		  			.get("/api/v1/theater/list")
		  			.accept(MediaType.APPLICATION_JSON))
		      		.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	
	private BookingRequest createRequest() {
		LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0));
		BookingRequest request = BookingRequest.builder()
				   .customer("Mike")
				   .theater("Regal")
				   .location("South Brunswick")
				   .showStartTime(LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0)))
				   .movie("The Batman")
				   .noOfSeat(3)
				   .build();
		return request;
	}
	
	private void test1(BookingRequest request) throws Exception, JsonProcessingException {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/theater/reserve")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	private void test2(BookingRequest request) throws Exception, JsonProcessingException {
		request.setShowStartTime((LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(11, 0))));
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/theater/reserve")
					.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String json = mvcResult.getResponse().getContentAsString();
		BookingResponse response = objectMapper.readValue(json, BookingResponse.class);
		assertNotNull(response);
		assertNotNull(response.getDiscount());
		assertNotNull(response.getGrossAmount());
		assertNotNull(response.getTotal());
	}
	
	private void test3(BookingRequest request) throws Exception, JsonProcessingException {
		request.setShowStartTime((LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.of(9, 0))));
		request.setMovie("Spider-Man: No Way Home");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/v1/theater/reserve")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(request))).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String json = mvcResult.getResponse().getContentAsString();
		BookingResponse response = objectMapper.readValue(json, BookingResponse.class);
		assertNotNull(response);
		assertNotNull(response.getDiscount());
		assertNotNull(response.getGrossAmount());
		assertNotNull(response.getTotal());
	}
}
