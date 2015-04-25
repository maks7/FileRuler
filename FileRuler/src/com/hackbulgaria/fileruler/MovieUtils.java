package com.hackbulgaria.fileruler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.Gson;

class MovieUtils {

    private static String getIMDBid(Movie movie) throws IOException {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

        String search = movie.getName() + " imdb";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        String foundedUrl = results.getResponseData().getResults().get(0).getUrl();

        return foundedUrl.substring(foundedUrl.indexOf("title/") + 6, foundedUrl.length() - 1);
    }

    public static ArrayList<String> getActors(Movie movie) throws IOException {
        ArrayList<String> actors = new ArrayList<String>();

        String content = getMovieContent(movie);

        String actorsRawData = content.substring(content.indexOf("\"Actors\":\"") + 10, content.indexOf("\",\"Plot\""));

        for (String actor : actorsRawData.split(",")) {
            actors.add(actor);
        }

        return actors;
    }

    public static String getDirector(Movie movie) throws IOException {
        String content = getMovieContent(movie);

        String director = content.substring(content.indexOf("\"Director\"") + 12, content.indexOf("\",\"Writer\""));

        return director;
    }

    public static String getMovieContent(Movie movie) {
        URL url = new URL("http://www.omdbapi.com/?i=" + getIMDBid(movie) + "&plot=short&r=json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder content = new StringBuilder();
        String currentLine = reader.readLine();

        while (currentLine != null) {
            content.append(currentLine);
            currentLine = reader.readLine();
        }
    }
}