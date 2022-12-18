package com.jpmc.theater.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 437589461012552120L;

	public MovieNotFoundException(final String message) {
        super(message);
    }

}
