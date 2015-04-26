package com.hackbulgaria.fileruler;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;

public class Image {
    public final String name;
    public final Path path;
    public final int width;
    public final int height;
    public final Date createdDate;
    public final ArrayList<String> imaggaTags;
    public ArrayList<String> fileRulerTags;

    public ArrayList<String> getImaggaTags() {
        return imaggaTags;
    }

    public void addTagInFileRuler(String tag) {
        this.fileRulerTags.add(tag);
    }

    public Image(Path path, String name, int width, int height, Date createdDate, ArrayList<String> imaggaTags) {
        this.path = path;
        this.name = name;
        this.width = width;
        this.height = height;
        this.createdDate = getDateOfCreation(path);
        this.imaggaTags = imaggaTags;
    }

    private Date getDateOfCreation(Path path) {

        return null;
    }
}