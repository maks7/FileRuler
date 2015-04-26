package word.to.tags;

import java.util.HashMap;


public class WordMap{
	private HashMap<String, Pair> words;
	
	WordMap(){
		words = new HashMap<String, Pair>();
	}

	public void put(Word word){
		words.put(word.getName(), word.getInfo());
	}
	
	public Pair get(String word){
		return words.get(word);
	}

	@SuppressWarnings("unchecked")
	public void changeTagInfo(String word,String tag, TagInfo info){
		try{
			((HashMap<String, TagInfo>) words.get(word).at(1)).put(tag, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
	

