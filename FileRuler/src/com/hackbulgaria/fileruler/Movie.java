package com.hackbulgaria.fileruler;

import java.nio.file.Path;

public class Movie {

    int id;
    private final String name;
    private final String yearOfRelease;
    private final String actors;
    private final String scenarist;
    private final String director;
    private final String runtime;
    private final String ganre;
    private final Path filePath;

    public Movie(String name, String yearOfRelease, String actors, String scenarist, String director, String runtime,
            String ganre, Path filePath) {
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.actors = actors;
        this.scenarist = scenarist;
        this.director = director;
        this.runtime = runtime;
        this.ganre = ganre;
        this.filePath = filePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public String getActors() {
        return actors;
    }

    public String getScenarist() {
        return scenarist;
    }

    public String getDirector() {
        return director;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGanre() {
        return ganre;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String toString() {
        return String.format("%s %s %s %s %s %s %s", /* getId(), */getName(), getYearOfRelease(), getActors(),
                getScenarist(), getDirector(), getRuntime(), getGanre());
    }
}
