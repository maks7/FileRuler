package word.to.tags;

import java.util.ArrayList;
import java.util.HashMap;

public class Phrase {
	private final ArrayList<String> words;
	private HashMap<String, Double> tags;
	
	Phrase(ArrayList<String> words){
		this.words = new ArrayList<String>(words);
		this.tags = new HashMap<String, Double>();
	}
	
	public ArrayList<String> getWords(){
		return this.words;
	}
	
	public HashMap<String, Double> getInfo(){
		return tags;
	}
	
}
