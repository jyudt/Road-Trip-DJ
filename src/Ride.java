import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Ride {
	ArrayList<Card> allCards;
	ArrayList<Card> mainDeck;
	ArrayList<Card> deck;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	int maxMana = 3;
	int currentMana;

	public Ride(ArrayList<Card> allCards, ArrayList<Card> mainDeck) {
		this.allCards = allCards;
		this.mainDeck = mainDeck;
	}
	
	public void beginRide() {
		initializeDeck();
		drawCard(5);
		playerTurn();
		
	}
	
	private void playerTurn(){
		currentMana = maxMana;
		System.out.println("Your Turn.");
		System.out.println("Your hand:");
		for(int i=0;i<hand.size();i++) {
			System.out.println(i+ " "+hand.get(i));
		}
	}
	
	private void drawCard() {
		if(deck.size()==0) {
			while(discard.size()>0) {
				Card c = discard.remove(0);
				deck.add(c);
			}
		Collections.shuffle(deck);
		}
		Card toDraw = deck.remove(0);
		if(hand.size()<10) {
			hand.add(toDraw);
		} else {
			System.out.println("Your hand is full.  Discarding "+ toDraw.getName()+ ".");
			discard.add(toDraw);
		}
	}
	
	private void drawCard(int n) {
		for(int i=0;i<n;i++) {
			drawCard();
		}
	}
	
	private void initializeDeck() {
		deck = new ArrayList<Card>();
		for(Card c:mainDeck) {
			Card clone = (Card) c.clone();
			deck.add(clone);
		}
		Collections.shuffle(deck);
	}

}
