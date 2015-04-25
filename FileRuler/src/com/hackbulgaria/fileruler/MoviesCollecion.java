package com.hackbulgaria.fileruler;

import java.util.ArrayList;

public class MoviesCollecion {
    // public static CopyOnWriteArrayList<Movie> movieCollection = new
    // CopyOnWriteArrayList<Movie>();
    public static ArrayList<Movie> movieCollection = new ArrayList<Movie>();

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