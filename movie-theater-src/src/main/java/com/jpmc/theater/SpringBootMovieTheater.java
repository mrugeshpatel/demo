/**
 * 
 */
package com.jpmc.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;


/**
 * @author Mrugesh Patel
 *
 */
@EnableJpaRepositories(basePackages = "com.jpmc.theater.repository") 
@ComponentScan(basePackages = "com.jpmc.theater")
@SpringBootApplication
@Slf4j
@EnableAutoConfiguration
public class SpringBootMovieTheater {
	
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMovieTheater.class, args);
        log.info("Theater application started successfully.");
    }
}    
