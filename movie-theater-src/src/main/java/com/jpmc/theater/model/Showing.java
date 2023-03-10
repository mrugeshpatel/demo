package com.jpmc.theater.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "showing")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Showing implements Serializable{
   
	private static final long serialVersionUID = -3163396814036919549L;

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Getter @Setter 
	@ManyToOne(targetEntity = Theater.class, optional = false)
    @JoinColumn(name = "theater_id")
	private Theater theater;
	
	@Getter @Setter 
	@ManyToOne(targetEntity = Movie.class, optional = false)
    @JoinColumn(name = "movie_id")
	private Movie movie;
	
	@Getter @Setter 
	@Column(name = "show_start_date_time", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime showStartDateTime;

	@Getter @Setter 
	@Column(name = "sequence_of_the_day", nullable = false)
	private Integer sequenceOfTheDay;
	
	@OneToMany(mappedBy = "showing",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations = new ArrayList<>();
    
	public Showing(Theater theater, Movie movie, LocalDateTime showStartDateTime, Integer sequenceOfTheDay) {
		this.theater = theater;
		this.movie = movie;
		this.showStartDateTime = showStartDateTime;
		this.sequenceOfTheDay = sequenceOfTheDay;
	}
}
