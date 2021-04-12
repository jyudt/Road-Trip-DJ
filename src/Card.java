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
	
	public void playCard() {
		
	}
	
	public Object clone() {
		return null;
	}
	
	public boolean sameClass() {
		//TODO
		return false;
	}
	
	public String getText() {
		return "missing card text";
	}
	
	public boolean exhausts() {
		return false;
	}
	
	public String getType() {
		return this.getClass().getName();
	}
}
