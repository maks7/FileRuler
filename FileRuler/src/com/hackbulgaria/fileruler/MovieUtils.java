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

    private static String getIMDBid(String name) throws IOException {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

        String search = name + " imdb";
        String charset = "UTF-8";

        URL url = new URL(google + URLEncoder.encode(search, charset));
        Reader reader = new InputStreamReader(url.openStream(), charset);
        GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

        String foundedUrl = results.getResponseData().getResults().get(0).getUrl();

        return foundedUrl.substring(foundedUrl.indexOf("title/") + 6, foundedUrl.length() - 1);
    }

    public static ArrayList<String> getActors(String name) throws IOException {
        String imdbID = getIMDBid(name);
        ArrayList<String> actors = new ArrayList<String>();

        URL url = new URL("http://www.omdbapi.com/?i=" + imdbID + "&plot=short&r=json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder content = new StringBuilder();
        String currentLine = reader.readLine();

        while (currentLine != null) {
            content.append(currentLine);
            currentLine = reader.readLine();
        }

        String actorsRawData = content.substring(content.indexOf("\"Actors\":\"") + 10, content.indexOf("\",\"Plot\""));

        for (String actor : actorsRawData.split(",")) {
            actors.add(actor);
        }

        return actors;
    }

    public static String getDirector(String name) throws IOException {
        String imdbID = getIMDBid(name);

        URL url = new URL("http://www.omdbapi.com/?i=" + imdbID + "&plot=short&r=json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

        StringBuilder content = new StringBuilder();
        String currentLine = reader.readLine();

        while (currentLine != null) {
            content.append(currentLine);
            currentLine = reader.readLine();
        }

        String director = content.substring(content.indexOf("\"Director\"") + 12, content.indexOf("\",\"Writer\""));

        return director;
    }
}