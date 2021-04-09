import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<Card> allCards;

	public static void main(String[] args) {		
		ArrayList<Card> mainDeck = new ArrayList<Card>();
		mainDeck.add(getCard("Black Hole Sun"));
		mainDeck.add(getCard("WAP"));
		mainDeck.add(getCard("Bohemian Rhapsody"));
		mainDeck.add(getCard("Around the World"));
		mainDeck.add(getCard("Get Lucky"));
		mainDeck.add(getCard("Digital Love"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		mainDeck.add(getCard("Monophobia"));
		for(int i=0;i<mainDeck.size();i++) {
			//System.out.println(i+ " "+mainDeck.get(i));
		}
		Ride ride = new Ride(allCards, mainDeck,20, 3);
		ride.beginRide();
		//gameGUI myGui = new gameGUI();
	}
	
	
	private static void initializeCards(){
		allCards =  new ArrayList<Card>();
		allCards.add(new Rock("Black Hole Sun",1));
		allCards.add(new Pop("WAP",1));
		allCards.add(new Rock("Bohemian Rhapsody",1));
		allCards.add(new Electronic("Around the World",1));
		allCards.add(new Electronic("Get Lucky",1));
		allCards.add(new Electronic("Digital Love",1));
		allCards.add(new Electronic("Monophobia",1));
	}
	
	private static Card getCard(String search) {
		if(allCards==null) {
			initializeCards();
		}
		for(Card c:allCards) {
			if(c.getName().equals(search)) {
				return c;
			}
		}
		return null;
	}

}
