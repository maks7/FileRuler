package com.hackbulgaria.fileruler;

import java.util.concurrent.CopyOnWriteArrayList;

public class MoviesCollecion {
	public static CopyOnWriteArrayList<Movie> movieCollection;
	
	public void add(Movie movie) {
		movieCollection.add(movie);
	}
	
	public CopyOnWriteArrayList<Movie> getMovies() {
		return movieCollection;
	}

}
