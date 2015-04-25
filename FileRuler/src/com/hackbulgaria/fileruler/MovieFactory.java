package com.hackbulgaria.fileruler;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

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
	Path filePath;

	public MovieFactory(String filePathAndName) {
		super(filePathAndName);
	}

	@Override
	public void run() {

		fileName = new File(getName()).getName();
		fileName = fileName.substring(0, fileName.lastIndexOf('.'));

		try {
			filePath = Paths.get(new URI(getName()));
			movieContent = new JSONObject(MovieUtils.getMovieContent(fileName));
			name = movieContent.getString("Title");
			yearOfRelease = movieContent.getString("Year");
			actors = movieContent.getString("Actors");
			scenarist = movieContent.getString("Writer");
			director = movieContent.getString("Director");
			runtime = movieContent.getString("Runtime");
			ganre = movieContent.getString("Genre");
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		MoviesCollecion.movieCollection.add(new Movie(name, yearOfRelease,
				actors, scenarist, director, runtime, ganre, filePath));

	}

	void generateAllMovies() {
		for (String pathToFile : HDDCrawler.listOfMovies) {
			new MovieFactory(pathToFile).start();

		}
	}

}
