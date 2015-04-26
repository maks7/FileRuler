package word.to.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TagGenerator {
	private WordMap words;
	private PhraseMap phrases;
	private ImageMap photos;
	
	@SuppressWarnings("unchecked")
	private HashMap<String, HashMap<String, TagInfo>> getWordTags(ArrayList<String> words){
		HashMap<String, HashMap<String, TagInfo>> wordTags = new HashMap<String, HashMap<String, TagInfo>>();
		Iterator<String> iter = words.iterator();
		while(iter.hasNext()){
			String currentWord = iter.next();
			HashMap<String, TagInfo> tags = (HashMap<String, TagInfo>) this.words.get(currentWord).at(1);
			wordTags.put(currentWord, tags);
		}
		return wordTags;
	}
	
	
	private HashMap<ArrayList<String>, HashMap<String, Double>> getCombos(ArrayList<String> words){
		return phrases.getCombinations(words);
	}

//	private queryTags()
//	
////	public HashMap<String, Double> analysePhrase(HashMap<String, ArrayList<String>> phrase){
////		return ;
////	}
//
//	private iterImages(){
//		
//	}
	
	
	public static void main(String[] args){
	
	}
}
