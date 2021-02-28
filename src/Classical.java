
public class Classical extends Card {

	public Classical() {
	}

	public Classical(String name, int cost) {
		this.name=name;
		this.cost = cost;
	}
	
	public Classical clone() {
		return new Classical(name, cost);
	}
}
