package word.to.tags;

public class Pair {
	private Object obj1;
	private Object obj2;
	
	Pair(Object obj1, Object obj2){
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	
	public Object at(int index){
		if(index < 0 && index > 1)
			throw new NullPointerException("Pair has only two values");
		else if(index == 0)
			return this.obj1;

		return this.obj2;
	}
}
