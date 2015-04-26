package com.hackbulgaria.fileruler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

class MovieUtils {

    private static String getIMDBid(String name) {
        String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";

        String search = String.format("%s%s", name, " imdb");
        String charset = "UTF-8";

        try {
            URL url = new URL(google + URLEncoder.encode(search, charset));
            Reader reader = new InputStreamReader(url.openStream(), charset);
            GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);
            reader.close();

            if (results.getResponseData().getResults() == null || results.getResponseData().getResults().isEmpty()) {
                return null;
            }

            System.out.println(results.toString());

            Thread.sleep(3000);

            if (results.getResponseData() == null) {
                return null;
            }

            String foundedUrl = results.getResponseData().getResults().get(0).getUrl();

            if (foundedUrl == null || !results.getResponseData().getResults().get(0).getTitle().contains("IMDb")) {
                return null;
            }

            if (!foundedUrl.contains("title")) {
                return null;
            }

            return foundedUrl.substring(foundedUrl.indexOf("title/") + 6, foundedUrl.length() - 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public static String getMovieContent(String name) {
        String id = getIMDBid(name);

        if (id == null || id.length() != 9) {
            return null;
        }

        try {
            URL url = new URL("http://www.omdbapi.com/?i=" + id + "&plot=short&r=json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            StringBuilder content = new StringBuilder();
            String currentLine = reader.readLine();

            while (currentLine != null) {
                content.append(currentLine);
                currentLine = reader.readLine();
            }

            reader.close();

            return content.toString();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }

        return null;
    }
}