package com.jpmc.theater.util;

import java.time.format.DateTimeFormatter;

public interface Constants {

	public static final Integer MOVIE_CODE_SPECIAL = 1;
	
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:00");
}
