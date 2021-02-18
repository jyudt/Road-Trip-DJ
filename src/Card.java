import java.util.ArrayList;

public abstract class Card {
	String name;
	int cost;
	
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
}
