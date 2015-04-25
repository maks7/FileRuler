package fileSystemCrawler;

public class Movie {

	int id;
	final String name;
	final String yearOfRelease;
	final String actors;
	final String scenarist;
	final String director;
	final String runtime;
	final String ganre;

	public Movie(String name, String yearOfRelease, String actors,String scenarist, String director, String runtime,
			String ganre) {
		this.name = name;
		this.yearOfRelease = yearOfRelease;
		this.actors = actors;
		this.scenarist = scenarist;
		this.director = director;
		this.runtime = runtime;
		this.ganre = ganre;
	}
}
