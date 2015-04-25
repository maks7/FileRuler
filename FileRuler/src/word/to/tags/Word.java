package word.to.tags;

import java.util.HashMap;
import java.util.Iterator;

public class Word {
	private final String name;
	private int wordOccurence;
	private HashMap<String, TagInfo> tags = new HashMap<String, TagInfo>();
	
	public Word(String name){
		this.name = name;
	}
	
	public void addOcurrense(HashMap<String, Double> tags){
		this.wordOccurence += 1;
		Iterator<String> keys =  tags.keySet().iterator();
		while(keys.hasNext()){
			String tagName = keys.next();
			this.addTag(tagName, tags.get(tagName));
		}
	}
	
	public String getName(){
		return this.name;
	}
	
	public HashMap<String, TagInfo> getInfo(){
		return null;
	}
	
	private double calculateRank(String tagName, double percentige){
		double tagOccurence = tags.get(tagName).getOccurence();
		double tagRank = tags.get(tagName).getRank();
		
		double offset =(double) (this.wordOccurence - tagOccurence);
		double rank = (tagRank + 0.1*Math.pow(2, offset));
		rank = (rank*tagOccurence + percentige) / this.wordOccurence;
		return rank;
	}
	
	private void addTag(String tagName, double percentige){
		tags.get(tagName).addOcuurence();
		double recalculated = calculateRank(tagName, percentige);
		tags.get(tagName).setRank(recalculated);
	}
}