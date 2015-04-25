package word.to.tags;

public class TagInfo {
	private int occurence = 0;
	private double rank;
	
	public void addOcuurence(){
		this.occurence += 1;
	}
	
	public int getOccurence(){
		return this.occurence;
	}
	
	public void setRank(double rank){
		this.rank = rank;
	}
	
	public double getRank(){
		return rank;
	}
}

