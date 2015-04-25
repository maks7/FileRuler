package com.hackbulgaria.fileruler;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD
public class MovieFactory {
=======

public class MovieFactory extends Thread{

>>>>>>> cb5d3707dc0b0a90e234c19da6b2b6ae25cc7249
    String path;

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

    static MoviesCollecion movieCollection = new MoviesCollecion();

    public MovieFactory(String filePathAndName) {
        path = filePathAndName;
    }

    public void run() {

        fileName = new File(path).getName();
        fileName = fileName.substring(0, fileName.lastIndexOf('.'));

        try {
            String content = MovieUtils.getMovieContent(fileName);

            if (content != null) {
                filePath = Paths.get(path);
                movieContent = new JSONObject(content);
                name = movieContent.getString("Title");
                yearOfRelease = movieContent.getString("Year");
                actors = movieContent.getString("Actors");
                scenarist = movieContent.getString("Writer");
                director = movieContent.getString("Director");
                runtime = movieContent.getString("Runtime");
                ganre = movieContent.getString("Genre");

                movieCollection.add(new Movie(name, yearOfRelease, actors, scenarist, director, runtime, ganre,
                        filePath));
            }
        } catch (JSONException e) {
            System.out.println("json parse crashed");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void generateAllMovies() {
        for (String pathToFile : HDDCrawler.listOfMovies) {
            new MovieFactory(pathToFile).run();
        }
    }
}
