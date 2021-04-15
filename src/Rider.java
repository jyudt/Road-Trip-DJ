import java.util.ArrayList;

public class Rider {
	private ArrayList<String> traits = new ArrayList<String>();
	private ArrayList<String> traitList = new ArrayList<String>();
	private int happiness = 100;
	private Card likes;
	private Card dislikes;
	private double dislikeChance = .1;
	private Card lastHeard;
	private String name;
	
	public Rider() {
		initTraits();
		traits.add(traitList.get((int) (Math.random()*traitList.size())));
		likes = pickGenre();
		if(Math.random()<=dislikeChance) {
			do {
				dislikes = pickGenre();
			} while(likes.getClass().equals(dislikes.getClass()));
		}
		name = pickName();
	}
	
	public Rider(int n) {
		initTraits();
		for(int i=0;i<n;i++) {
			traits.add(traitList.remove((int) (Math.random()*traitList.size()-1)));
		};
		likes = pickGenre();
		if(Math.random()<=dislikeChance) {
			do {
				dislikes = pickGenre();
			} while(likes.getClass().equals(dislikes.getClass()));
		}
		name = pickName();
	}
	
	public Card pickGenre() {
		int rng = (int) (Math.random()*5);
		switch(rng){
			case(0):
				return new Rock("dummy card",0);
			case(1):
				return new HipHop("dummy card",0);
			case(2):
				return new Electronic("dummy card",0);
			case(3):
				return new Pop("dummy card",0);
			case(4):
				return new Classical("dummy card",0);
		}
		return null;
	}

	public ArrayList<String> getTraits() {
		return traits;
	}

	public void setTraits(ArrayList<String> traits) {
		this.traits = traits;
	}
	
	public String getName() {
		return name;
	}
	
	public int getHappiness() {
		return happiness;
	}
	
	public Object getLikes() {
		return likes;
	}
	
	public String getLikesString() {
		return likes.getClass().toString().substring(6);
	}
	
	public Object getDislikes() {
		if(dislikes==null)
			return null;
		return dislikes;
	}
	
	public String getDislikesString() {
		if(dislikes==null)
			return null;
		return dislikes.getClass().toString().substring(6);
	}
	
	private void gainHappiness(int n) {
		int determ = happiness+n;
		if(determ<=100) {
			happiness=determ;
			return;
		}
		double gainMulti = 1;
		if(determ>200) {
			gainMulti=0;
		} else if (determ>175) {
			gainMulti=.25;
		} else if (determ>150) {
			gainMulti=.5;
		} else if (determ>100) {
			gainMulti=.75;
		}
		int gainRem = n-(100-happiness);
		happiness = 100 + (int)Math.ceil(gainRem * gainMulti);
	}

	public void reactToCard(Card c) {
		lastHeard = c;
		
		int happinessIncrease = 10;
		if(c.getClass().equals(likes.getClass())) {
			happinessIncrease = 20;
		}
		if(dislikes!=null && c.getClass().equals(dislikes.getClass())) {
			happinessIncrease = -10;
		}
		
		gainHappiness(happinessIncrease);
	}
	
	public void takeTurn() {
		int happinessIncrease = -40;
		if(lastHeard!=null && lastHeard.getClass().equals(likes.getClass())) {
			happinessIncrease = -20;
		}
		if(lastHeard!=null && dislikes!=null && lastHeard.getClass().equals(dislikes.getClass())) {
			happinessIncrease = -50;
		}
		gainHappiness(happinessIncrease);
		lastHeard = null;
	}
	
	public void initTraits() {
		traitList.add("Chill");
		traitList.add("Picky");
		traitList.add("Flexible");
		traitList.add("Impatient");
		traitList.add("Fickle");
		traitList.add("Sleepy");
	}
	
	private String pickName() {
		String[] names = {"Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "William", "Sophia", "Elijah", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper", "Ethan",
		                  "Evelyn", "Alexander", "Abigail", "Henry", "Emily", "Jacob", "Ella", "Michael", "Elizabeth", "Daniel", "Camila", "Logan", "Luna", "Jackson", "Sofia", "Sebastian", "Avery", "Jack",
		                  "Mila", "Aiden", "Aria", "Owen", "Scarlett", "Samuel", "Penelope", "Matthew", "Layla", "Joseph", "Chloe", "Levi", "Victoria", "Mateo", "Madison", "David", "Eleanor", "John", "Grace",
		                  "Wyatt", "Nora", "Carter", "Riley", "Julian", "Zoey", "Luke", "Hannah", "Grayson", "Hazel", "Isaac", "Lily", "Jayden", "Ellie"};
		return names[(int)(Math.random()*names.length)];
	}

}
