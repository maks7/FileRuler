package fileSystemCrawler;

import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

public class HDDCrawler {
	static String videoFormats = "mkv,flv, mng,wmv, mp4, mpeg,3gp";
	static String imageFormats = "hdr,jpeg,tiff,rif,gif,bmp,png,exif,ppm,pgm,pbm,pnm";
	
	public static ArrayList<String> listOfMovies = new ArrayList<String>();
	public static ArrayList<String> listOfImages = new ArrayList<String>();

	public ArrayList<String> getListOfMovies() {
		return listOfMovies;
	}

	public ArrayList<String> getListOfImages() {
		return listOfImages;
	}

	public void addImage(String filePathAndName) {
		if (isImageFormat(getExtension(filePathAndName))) {
			listOfImages.add(filePathAndName);
		}
	}

	public void addVideo(String filePathAndName) {
		if (isVideoFormat(getExtension(filePathAndName))) {
			listOfImages.add(filePathAndName);
		}
	}

	private static boolean isVideoFormat(String ext) {
		if (videoFormats.contains(ext)) {
			return true;
		}
		return false;
	}

	private static boolean isImageFormat(String ext) {
		if (imageFormats.contains(ext.toLowerCase())) {
			return true;
		}
		return false;
	}

	public static String getExtension(String filePathAndName) {
		String ext = FilenameUtils.getExtension(filePathAndName);
		return ext;
	}

	void fillTheCollection(String filePathAndName) {
		String ext = getExtension(filePathAndName);
		if (isImageFormat(ext)) {
			listOfImages.add(filePathAndName);
		} else if (isVideoFormat(ext)) {
			listOfMovies.add(filePathAndName);
		}
	}



}
