package com.hackbulgaria.fileruler;

public class ImageFactory {

    // private String path;

    // private String actors;
    // private String scenarist;
    // private String director;
    // private String runtime;
    // private String ganre;
    // private String yearOfRelease;
    // private String name;
    // private JSONObject movieContent;
    // private String fileName;
    // private Path filePath;

    static ImageCollection imageCollection = new ImageCollection();

    public ImageFactory(String filePathAndName) {
        // path = filePathAndName;
    }

    public void run() {
        // fileName = new File(path).getName();
        // fileName = fileName.substring(0, fileName.lastIndexOf('.'));

        try {
            // String content = MovieUtils.getMovieContent(fileName);

            // if (content != null) {

            // Get tags from IMAGGA (JSON)
            // imageCollection.add(..);
            // }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void generateAllMovies() {
        for (String pathToFile : HDDCrawler.listOfImages) {
            new ImageFactory(pathToFile).run();
        }
    }
}