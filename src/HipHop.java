
public class HipHop extends Card {

	public HipHop() {
	}
	public HipHop(String name, int cost) {
		this.name=name;
		this.cost = cost;
	}

	public HipHop clone() {
		return new HipHop(name, cost);
	}
}
