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
@Table(name = "theater")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Theater implements Serializable{

    private static final long serialVersionUID = 1113601360773813917L;
    
    @Getter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Getter @Setter 
    @Column(name = "name", nullable = false)
    private String name;
    
    @Getter @Setter 
    @Column(name = "location", nullable = false)
    private String location;
	
    @Getter @Setter 
    @OneToMany(mappedBy = "theater",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Showing> schedule  = new ArrayList<>();
    
    public Theater(String name, String location) {
    	this.name = name;
    	this.location = location;
    }
}
