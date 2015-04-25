package com.hackbulgaria.fileruler;

import java.io.File;
import java.io.FilePermission;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.*;

public class MovieFactory extends Thread {
	String actors;
	String scenarist;
	String director;
	String runtime;
	String ganre;
	String yearOfRelease;
	String name;
	JSONObject movieContent;
	String fileName;

	public MovieFactory(String filePathAndName) {
		super(filePathAndName);
	}

	@Override
	public void run() {
		// String fileName = new
		// File(Paths.get(URI.create(getName()))).getName();
		fileName = new File(getName()).getName();
		fileName = fileName.substring(0, fileName.lastIndexOf('.'));
		movieContent = MovieUtils.getMovieContent(fileName);
		name = movieContent.getString("Title");
		yearOfRelease = movieContent.getString("Year");
		actors = movieContent.getString("Actors");
		scenarist = movieContent.getString("Writer");
		director = movieContent.getString("Director");
		runtime = movieContent.getString("Runtime");
		ganre = movieContent.getString("Genre");
		MoviesCollecion.movieCollection.add(new Movie(name, yearOfRelease,
				actors, scenarist, director, runtime, ganre));

	}

	void generateAllMovies() {
		for (String pathToFile : HDDCrawler.listOfMovies) {
			new MovieFactory(pathToFile).start();

		}
	}

}
