package word.to.tags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PhraseMap {
	private HashMap<ArrayList<String>, HashMap<String, Double>> phrases;

	public void addPhrase(Phrase phrase){
		phrases.put(phrase.getWords(), phrase.getInfo());
	}

	private Pair extractCombinations(ArrayList<String> keywords, ArrayList<String> original){
		Iterator<String> iter = keywords.iterator();
		ArrayList<String> combination = new ArrayList<String>();
		while(iter.hasNext()){
			String word = iter.next();
			if(original.contains(word)){
				combination.add(word);
			}
		}
		if(!combination.isEmpty())
			return null;
		HashMap<String, Double>tags = this.phrases.get(original);
		return new Pair(combination, tags);
	}

	@SuppressWarnings("unchecked")
	public HashMap<ArrayList<String>, HashMap<String, Double>> getCombinations(ArrayList<String> list){
		Iterator<ArrayList<String>> iter = phrases.keySet().iterator();
		HashMap<ArrayList<String>, HashMap<String, Double>> result = new HashMap<ArrayList<String>, HashMap<String, Double>>();
		while(iter.hasNext()){
			Pair combination = this.extractCombinations(list, iter.next());
			if(combination != null){
				result.put((ArrayList<String>)combination.at(0), (HashMap<String, Double>) combination.at(1));
			}
		}
		
		return null;
	}

	
}
