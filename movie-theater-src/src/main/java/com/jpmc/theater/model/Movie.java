package com.jpmc.theater.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie")
@NoArgsConstructor 
@ToString @EqualsAndHashCode
public class Movie implements Serializable{
    
	private static final long serialVersionUID = 3779627911467648918L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id")
	private Long id;
	
	@Getter @Setter 
	@Column(name = "title", nullable = false)
    private String title;
	
	@Getter @Setter 
	@Column(name = "description", nullable = true)
    private String description;
	
	@Getter @Setter 
	@Column(name = "runningTime", nullable = false)
    private String runningTime;
    
	@Getter @Setter 
	@Column(name = "ticketPrice", nullable = false) 	
	private Double ticketPrice;
    
	@Getter @Setter 
	@Column(name = "specialCode", nullable = true) 
	private Integer specialCode;
 
	public Movie(String title, String description, String runningTime, Double ticketPrice, Integer specialCode) {
		this.title = title;
		this.description = description;
		this.runningTime = runningTime;
		this.ticketPrice = ticketPrice;
		this.specialCode = specialCode;
	}
}