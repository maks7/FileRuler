package word.to.tags;

import java.util.HashMap;
import java.util.Iterator;

public class Word {
	public static final double[][] exponents = new double[][]{{5,10,15,30,60,100},{3,4,5,6,7,8}};
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
	
	public Pair getInfo(){
		Pair info = new Pair(this.wordOccurence, this.tags);
		return info;
	}
	
	private double calculateOccurancePercentage(String tagName){
		double delimeter = (double) this.wordOccurence / 100;
		return (double)(this.tags.get(tagName).getOccurence())/delimeter;
	}
	
	private double calculateExponent(double percentage){
		for(int i = 0; i < exponents.length; i++){
			if(percentage < exponents[0][i]){
				return exponents[0][i];
			}
		}
		return 0;
	}
	
	
	private double calculateRank(String tagName, double percentige){
		double tagOccurence = tags.get(tagName).getOccurence();
		double tagRank = tags.get(tagName).getRank();
		double percentage = this.calculateOccurancePercentage(tagName);
		double exponent = this.calculateExponent(percentage);
		double rank = (tagRank + 0.1*Math.pow(2, exponent));
		rank = (rank*tagOccurence + percentige) / this.wordOccurence;
		return rank;
	}
	
	private void addTag(String tagName, double percentige){
		tags.get(tagName).addOcuurence();
		double recalculated = calculateRank(tagName, percentige);
		tags.get(tagName).setRank(recalculated);
	}
}