package com.jpmc.theater.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Reservation implements Serializable{
	
    private static final long serialVersionUID = -3724346098269850402L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Column(name = "id")
	private Long id;
    
    @ManyToOne(targetEntity = Customer.class, optional = false)
    @JoinColumn(name = "customer_id")
    @Getter @Setter 
    private Customer customer;
    
    @ManyToOne(targetEntity = Showing.class, optional = false)
    @JoinColumn(name = "showing_id")
    @Getter @Setter 
    private Showing showing;
    
    @Getter @Setter 
    @Column(name = "audience_count")
    private Integer audienceCount;
   
    @Getter @Setter 
    @Column(name = "gross_amount")
    private Double grossAmount;
    
    @Getter @Setter 
    @Column(name = "discount")
    private Double discount;
    
    @Getter @Setter 
    @Column(name = "total")
    private Double total;
    
    public Reservation(Customer customer, Showing showing, Integer audienceCount, Double grossAmount, Double discount, Double total ) {
    	this.customer = customer;
    	this.showing = showing;
    	this.audienceCount = audienceCount;
    	this.grossAmount = grossAmount;
    	this.discount = discount;
    	this.total = total;
    }
}