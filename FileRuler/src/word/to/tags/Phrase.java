package word.to.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
	
	public void addTags(HashMap<String, Double> tags){
		Iterator<String> iter = tags.keySet().iterator();
		while(iter.hasNext()){
			String tag = iter.next();
			this.tags.put(tag, tags.get(tag));
		}
	}
	
	public HashMap<String, Double> getInfo(){
		return tags;
	}
	
}
