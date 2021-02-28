
public class Pop extends Card {

	public Pop() {
	}

	public Pop(String name, int cost) {
		this.name=name;
		this.cost = cost;
	}
	
	public Pop clone() {
		return new Pop(name, cost);
	}
}
