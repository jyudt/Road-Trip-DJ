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
	
	public Card getLikes() {
		return likes;
	}
	
	public String getLikesString() {
		return likes.getClass().toString().substring(6);
	}
	
	public Card getDislikes() {
		if(dislikes==null)
			return null;
		return dislikes;
	}
	
	public String getDislikesString() {
		if(dislikes==null)
			return null;
		return dislikes.getClass().toString().substring(6);
	}
	
	public boolean hasTrait(String s) {
		for(String t:traits) {
			if(t.equals(s))
				return true;
		}
		return false;
	}
	
	private void gainHappiness(int n,double multi) {
		n*=multi;
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
		double multi = 1;
		if(hasTrait("Chill")) {
			if(happinessIncrease>0) {
				multi +=.2;
			} else {
				multi-=.2;
			}
		} 
		if(hasTrait("Picky")) {
			if(happinessIncrease<0) {
				multi+=.2;
			}
		}
		if(hasTrait("Flexible")) {
			if(happinessIncrease<0) {
				multi-=.2;
			}
		}
		if(hasTrait("Impatient")) {
			if(!(lastHeard!=null&&lastHeard.getType().equals(likes.getType()))) {
				if(happinessIncrease>0) {
					multi-=.4;
				} else {
					multi+=4;
				}
			}
		}
		
		
		if(hasTrait("Sleepy")) {
			if((int)(Math.random()*4)==0) {
				multi = 0;
			}
		}
		
		gainHappiness(happinessIncrease,multi);
	}
	
	public void takeTurn() {
		int happinessIncrease = -40;
		if(lastHeard!=null && lastHeard.getClass().equals(likes.getClass())) {
			happinessIncrease = -20;
		}
		if(lastHeard!=null && dislikes!=null && lastHeard.getClass().equals(dislikes.getClass())) {
			happinessIncrease = -50;
		}
		
		double multi = 1;
		if(hasTrait("Chill")) {
			if(happinessIncrease>0) {
				multi +=.2;
			} else {
				multi-=.2;
			}
		} 
		if(hasTrait("Picky")) {
			if(happinessIncrease<0) {
				multi+=.2;
			}
		}
		if(hasTrait("Flexible")) {
			if(happinessIncrease<0) {
				multi-=.2;
			}
		}
		if(hasTrait("Impatient")) {
			if(!(lastHeard!=null&&lastHeard.getType().equals(likes.getType()))) {
				if(happinessIncrease>0) {
					multi-=.4;
				} else {
					multi+=.4;
				}
			}
		}
		
		if(hasTrait("Sleepy")) {
			if((int)(Math.random()*4)==0) {
				multi = 0;
			}
		}
		gainHappiness(happinessIncrease,multi);
		lastHeard = null;
	}
	
	public void initTraits() {
		traitList.add("Chill");
		traitList.add("Picky");
		traitList.add("Flexible");
		traitList.add("Impatient");
		//traitList.add("Fickle");
		traitList.add("Sleepy");
	}
	
	public static String getTraitDesc(String t) {
		switch(t) {
		case("Chill"):
			return "Gains more and loses less happiness.";
		case("Picky"):
			return "Loses more happiness.";
		case("Flexible"):
			return "Loses less happiness.";
		case("Impatient"):
			return "Gains less and loses more happiness if they don't hear their liked music.";
		case("Sleepy"):
			return "Sometimes ignores played songs";
		
		}
		return "";
	}
	
	private String pickName() {
		String[] names = {"Liam", "Olivia", "Noah", "Emma", "Oliver", "Ava", "William", "Sophia", "Elijah", "Isabella", "James", "Charlotte", "Benjamin", "Amelia", "Lucas", "Mia", "Mason", "Harper", "Ethan",
		                  "Evelyn", "Alexander", "Abigail", "Henry", "Emily", "Jacob", "Ella", "Michael", "Elizabeth", "Daniel", "Camila", "Logan", "Luna", "Jackson", "Sofia", "Sebastian", "Avery", "Jack",
		                  "Mila", "Aiden", "Aria", "Owen", "Scarlett", "Samuel", "Penelope", "Matthew", "Layla", "Joseph", "Chloe", "Levi", "Victoria", "Mateo", "Madison", "David", "Eleanor", "John", "Grace",
		                  "Wyatt", "Nora", "Carter", "Riley", "Julian", "Zoey", "Luke", "Hannah", "Grayson", "Hazel", "Isaac", "Lily", "Jayden", "Ellie"};
		return names[(int)(Math.random()*names.length)];
	}

}
