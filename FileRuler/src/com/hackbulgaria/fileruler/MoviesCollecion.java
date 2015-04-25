package com.hackbulgaria.fileruler;

import java.util.ArrayList;

public class MoviesCollecion {
<<<<<<< HEAD
    // public static CopyOnWriteArrayList<Movie> movieCollection = new
    // CopyOnWriteArrayList<Movie>();
    public static ArrayList<Movie> movieCollection = new ArrayList<Movie>();
=======

    public static CopyOnWriteArrayList<Movie> movieCollection = new CopyOnWriteArrayList<Movie>();
>>>>>>> cb5d3707dc0b0a90e234c19da6b2b6ae25cc7249

    public void add(Movie movie) {
        movieCollection.add(movie);
    }

    // public CopyOnWriteArrayList<Movie> getMovies() {
    // return movieCollection;
    // }

    public ArrayList<Movie> getMovies() {
        return movieCollection;
    }
}
