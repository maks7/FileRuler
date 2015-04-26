package word.to.tags;
import java.io.IOException;
import java.util.HashMap;

import rita.RiWordNet;

public class Synonyms {

	public static HashMap<String, String[]> getSynonyms(String word,
			int maxNumber) throws IOException {
		// You have to install wordnet for this to work.
		RiWordNet wordnet = new RiWordNet("/usr/share/wordnet-3.0/");
		String[] synonyms = wordnet.getAllSynonyms(word, "n", maxNumber);
		HashMap<String, String[]> synonymsHash = new HashMap<String, String[]>();
		synonymsHash.put(stem(wordnet, word), synonyms);
		return synonymsHash;
	}
	
	public static String stem(RiWordNet wordnet, String word) {
		return wordnet.getStems(word, wordnet.getPosStr(word))[0];
	}

}
