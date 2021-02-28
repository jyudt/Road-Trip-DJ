
public class Rock extends Card {

	public Rock() {
	}
	
	public Rock(String name, int cost) {
		this.name=name;
		this.cost = cost;
	}
	
	public Rock clone() {
		return new Rock(name, cost);
	}

}
