package com.hackbulgaria.fileruler;

import java.util.concurrent.CopyOnWriteArrayList;

public class MoviesCollecion {
//<<<<<<< HEAD
//    public static CopyOnWriteArrayList<Movie> movieCollection = new CopyOnWriteArrayList<Movie>();
//=======
	public static CopyOnWriteArrayList<Movie> movieCollection;
	
	public void add(Movie movie) {
		movieCollection.add(movie);
	}
	
	public CopyOnWriteArrayList<Movie> getMovies() {
		return movieCollection;
	}

//>>>>>>> ad82a6a5215abcae4a91bc76e7f22e2b7347beab
}
