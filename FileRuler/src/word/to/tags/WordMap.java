package word.to.tags;

import java.util.HashMap;


public class WordMap{
	private HashMap<String, HashMap<String, TagInfo>> words;

	public void addWord(Word word){
		words.put(word.getName(), word.getInfo());
	}
	
	public void changeTagInfo(String word,String tag, TagInfo info){
		words.get(word).put(tag, info);
	}
}
	

