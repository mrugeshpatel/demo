package com.jpmc.theater.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Customer implements Serializable{

	private static final long serialVersionUID = -8783645827555357103L;

	@Getter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Getter @Setter 
	@Column(name = "name", nullable = false)
    private String name;
	
	@Getter @Setter
    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reservation> reservations = new ArrayList<>();
	
	public Customer(String name) {
		this.name = name;
	}
	
}