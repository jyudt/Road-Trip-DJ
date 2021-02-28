import java.util.ArrayList;

public abstract class Card {
	protected String name;
	protected int cost;
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String toString() {
		ArrayList<Object> outs = new ArrayList<Object>();
		outs.add(this.getClass().getName());
		outs.add(name);
		outs.add(cost);
		
		String outStr = "";
		for(Object o:outs) {
			outStr+=o + " ";
		}
		return outStr;
	}
	
	public Object clone() {
		return null;
	}
}
