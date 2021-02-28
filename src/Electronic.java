
public class Electronic extends Card {

	public Electronic() {
	}

	public Electronic(String name, int cost) {
		this.name=name;
		this.cost = cost;
	}
	
	public Electronic clone() {
		return new Electronic(name, cost);
	}
}
