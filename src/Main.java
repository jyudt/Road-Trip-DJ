import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<Card> allCards;
	static ArrayList<Card> normCards;

	public static void main(String[] args) {		
		ArrayList<Card> mainDeck = new ArrayList<Card>();
		initializeCards();
		//don't do this for the real game
		//deck of every card
		
		for(Card c:normCards) {
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
		normCards.add(new Rock("Black Hole Sun",1));
		
		normCards.add(new Pop("Thriller",1));
		normCards.add(new Pop("Shake It Off",0));
		normCards.add(new Pop("I Want It That Way",1));
		normCards.add(new Pop("Don't Stop Believing",1));
		normCards.add(new Pop("Hollaback Girl",1));
		normCards.add(new Pop("Uptown Funk",1));

		allCards =  new ArrayList<Card>();
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
}
