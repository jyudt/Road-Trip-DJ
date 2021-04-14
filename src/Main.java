import java.util.ArrayList;

public class Main {
	static ArrayList<Card> allCards;
	static ArrayList<Card> normCards;

	public static void main(String[] args) {		
		ArrayList<Card> mainDeck = new ArrayList<Card>();
		initializeCards();
		//don't do this for the real game
		//deck of every card
		
		for(Card c:normCards) {
			if(c.getType().equals("HipHop"))
				mainDeck.add((Card)c.clone());
		}
		
		for(int i=0;i<mainDeck.size();i++) {
			//System.out.println(i+ " "+mainDeck.get(i));
		}
		Ride ride = new Ride(allCards, mainDeck,20, 3);
		ride.beginRide();
		//gameGUI myGui = new gameGUI();
	}
	
	
	private static void initializeCards(){
		normCards =  new ArrayList<Card>();
		//rock
		normCards.add(new Rock("Black Hole Sun",1));
		normCards.add(new Rock("Sweet Emotion",0));
		normCards.add(new Rock("Back in Black",1));
		normCards.add(new Rock("Bohemian Rhapsody",2));
		normCards.add(new Rock("We Will Rock You",1));
		normCards.add(new Rock("Livin\' On A Prayer",1));
		
		//electronic
		normCards.add(new Electronic("Lean On",1));
		normCards.add(new Electronic("Helicopter",0));
		normCards.add(new Electronic("Turn Down For What",1));
		normCards.add(new Electronic("Digital Love",1));
		normCards.add(new Electronic("Around The World",1));
		normCards.add(new Electronic("Closer",1));
		
		//hip-hop
		normCards.add(new HipHop("Fight The Power",1));
		normCards.add(new HipHop("Keep Ya Head Up",0));
		normCards.add(new HipHop("C.R.E.A.M",1));
		normCards.add(new HipHop("Lose Yourself",1));
		normCards.add(new HipHop("It Was A Good Day",1));
		normCards.add(new HipHop("Still D.R.E.",1));
		normCards.add(new HipHop("Gangsta's Paradise",3));

		//pop
		normCards.add(new Pop("Thriller",1));
		normCards.add(new Pop("Shake It Off",0));
		normCards.add(new Pop("I Want It That Way",1));
		normCards.add(new Pop("Don't Stop Believing",1));
		normCards.add(new Pop("Hollaback Girl",1));
		normCards.add(new Pop("Uptown Funk",1));
		
		
		
		allCards =  new ArrayList<Card>();
		//rock-gen
		allCards.add(new Rock("We Are The Champions",3));
		
		//electronic-gen
		allCards.add(new Electronic("One More Time",1));
		allCards.add(new Electronic("Get Lucky",1));
		
		//hiphop-gen
		allCards.add(new HipHop("Nothin\' But A G Thang",0));
		
		//pop-gen
		allCards.add(new Pop("Everybody",1));
		allCards.add(new Pop("No Place",1));
		allCards.add(new Pop("Any Way You Want It",0));
		allCards.add(new Rock("Don't Speak",1));
		allCards.add(new Pop("Locked Out of Heaven",0));


		
		
		/**
		allCards.add(new Rock("Black Hole Sun",1));
		allCards.add(new Pop("I Want It That Way",1));
		allCards.add(new Rock("Bohemian Rhapsody",1));
		allCards.add(new Electronic("Around the World",1));
		allCards.add(new Electronic("Get Lucky",1));
		allCards.add(new Electronic("Digital Love",1));
		allCards.add(new Electronic("Monophobia",1));
		*/
	}
	
	public static Card getCard(String search) {
		if(allCards==null) {
			initializeCards();
		}
		for(Card c:allCards) {
			if(c.getName().equals(search)) {
				return (Card) c.clone();
			}
		}
		return null;
	}
	
	public static Card getRandNormCard() {
		if(normCards==null) {
			initializeCards();
		}
		int index = (int)(Math.random()*normCards.size());
		return (Card) normCards.get(index).clone();
	}
}
