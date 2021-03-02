import java.util.ArrayList;

public class Rider {
	private ArrayList<String> traits;
	private ArrayList<String> traitList = new ArrayList<String>();
	
	public Rider() {
		initTraits();
		traits.add(traitList.get((int) (Math.random()*traitList.size()-1)));
	}
	
	public Rider(int n) {
		initTraits();
		for(int i=0;i<n;i++) {
			traits.add(traitList.remove((int) (Math.random()*traitList.size()-1)));
		};
	}

	public ArrayList<String> getTraits() {

		return traits;
	}

	public void setTraits(ArrayList<String> traits) {
		this.traits = traits;
	}

	public void reactToCard(Card c) {
		
	}
	
	public void initTraits() {
		traitList.add("Chill");
		traitList.add("Picky");
		traitList.add("Flexible");
		traitList.add("Impatient");
		traitList.add("Fickle");
		traitList.add("Sleepy");
	}

}
