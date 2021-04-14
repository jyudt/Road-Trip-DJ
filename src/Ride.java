import java.util.ArrayList;
import java.util.Collections;


public class Ride {
	ArrayList<Card> allCards;
	ArrayList<Card> mainDeck;
	ArrayList<Card> deck;
	ArrayList<Card> hand = new ArrayList<Card>();
	ArrayList<Card> discard = new ArrayList<Card>();
	ArrayList<Card> exhaust = new ArrayList<Card>();
	ArrayList<Rider> riders = new ArrayList<Rider>();
	gameGUI gui;
	int maxMana = 3;
	int currentMana;
	final int RIDE_DURATION;
	int remainingTurns = -1;
	int turnStartCards = 5;
	final int MAX_HAND_SIZE = 9;
	private int input;
	public int playTwice = 0;
	public int playTwiceRegen = 0;
	public int refund = 0;
	public int refundRegen = 0;

	public Ride(ArrayList<Card> allCards, ArrayList<Card> mainDeck, int turns, int riders) {
		this.allCards = allCards;
		this.mainDeck = mainDeck;
		this.RIDE_DURATION = turns;
		this.remainingTurns = turns;
		for(int i=0;i<riders;i++) {
			this.riders.add(new Rider());
		}
	}
	
	public void beginRide() {
		initializeDeck();
		gui = new gameGUI(this);
		updateGui();
		
		for(;remainingTurns>0;remainingTurns--) {
			playerTurn();
			riderTurn();
			checkForLoss();
		}
		System.out.println("Congratulations!  You've reached your destination with everyone still happy.  You win!");
	}
	
	private void playerTurn(){
		gui.setTimer(remainingTurns);
		drawCard(turnStartCards);
		currentMana = maxMana;
		playTwice = playTwiceRegen;
		refund = refundRegen;
		System.out.println("Turns Remaining: "+remainingTurns);
		System.out.println("Your Turn.");
		input = -2;
		while(input!=-1) {
			gui.passTime(currentMana);
			input = -2;
			System.out.println("You have "+currentMana+" mana.");
			System.out.println("~~~~~~~~~~~~~~~~~~");
			System.out.println("Riders:");
			for(Rider r:riders) {
				String strOut = r.getName()+" || H:"+r.getHappiness()+" || Likes: "+r.getLikesString();
				if(r.getDislikes()!=null)
					strOut+=" || Dislikes: "+r.getDislikesString();
				System.out.println(strOut);
			}
			System.out.println("~~~~~~~~~~~~~~~~~~");
			System.out.println("Your hand:");
			for(int i=0;i<hand.size();i++) {
				System.out.println(i+ " "+hand.get(i));
			}
			/**
			while(input<-1 || input>hand.size()-1) {
				System.out.println("Pick a card to play (0-"+(hand.size()-1)+", or -1 to end turn)");
				input = Integer.parseInt(inpScan.nextLine());
			}
			*/
			while(input<-1 || input>hand.size()-1) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(input==-1) {
				System.out.println("Turn end.");
				System.out.println();
				discardHand();
				playTwice=0;
				return;
			}
			Card played =  hand.get(input);
			if(currentMana<played.getCost()) {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("!!!!");
				System.out.println("You don't have enough mana.");
				System.out.println();
				continue;
			} else {
				currentMana -= played.getCost();
				if(refund>0) {
					currentMana+=played.getCost();
					refund--;
				}
			}
			hand.remove(input);

			System.out.println("You played: "+played.getName());
			System.out.println();

			played.playCard(this);
			for(Rider r:riders) {
				r.reactToCard(played);
			}
			if(playTwice>0) {
				played.playCard(this);
				for(Rider r:riders) {
					r.reactToCard(played);
				}
				playTwice--;
			}
			if(played.exhausts()) {
				exhaust.add(played);
			} else {
				discard.add(played);
			}
			updateGui();
		}
		
	}
	
	public void passInput(int i) {
		input=i;
	}
	
	public void riderTurn() {
		for(Rider r:riders) {
			r.takeTurn();
		}
	}
	
	private void checkForLoss() {
		ArrayList<Rider> madRiders = new ArrayList<Rider>();
		for(Rider r:riders) {
			if(r.getHappiness()<=0) {
				madRiders.add(r);
			}
		}
		if(madRiders.size()==0)
			return;
		if(madRiders.size()==1)
			System.out.println(madRiders.get(0).getName()+ " is sick of your music.  Game over!");
		if(madRiders.size()>1) {
			String out = madRiders.remove(0).getName();
			for(Rider r:madRiders) {
				out+= " and "+r.getName();
			}
			out+= " are sick of your music.  Game over!";
			System.out.println(out);
		}
		System.exit(0);
	}
	
	private void drawCard() {
		if(deck.size()==0) {
			if(discard.size()==0) {
				return;
			}
			while(discard.size()>0) {
				deck.add(discard.remove(0));
			}
		Collections.shuffle(deck);
		}
		Card toDraw = deck.remove(0);
		if(hand.size()<MAX_HAND_SIZE) {
			hand.add(toDraw);
		} else {
			System.out.println("Your hand is full.  Discarding "+ toDraw.getName()+ ".");
			discard.add(toDraw);
		}
		updateGui();
	}
	
	public void discardHand() {
		while(hand.size()>0) {
			discard.add(hand.remove(0));
		}
		updateHand();
	}
	
	public Card discardCard(int i) {
		if(hand.size()==0) {
			return null;
		}
		if(i==-1) {
			i=(int)(Math.random()*hand.size());
		}
		return hand.remove(i);
	}
	
	public void drawCard(int n) {
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
	
	public void updateHand() {
		gui.passHand(hand);
	}
	
	public void updateGui() {
		gui.deckSize(deck.size());
		gui.discardSize(discard.size());
		gui.exhaustSize(exhaust.size());
		gui.passHand(hand);
		gui.setTimer(remainingTurns);
		gui.passTime(currentMana);
		gui.drawRiders();
	}
	
	public ArrayList<Rider> getRiders() {
		return riders;
	}
	
	public void addToHand(Card c) {
		hand.add(c);
		updateHand();
	}
	
	public void exhCardInHand(int i) {
		exhaust.add(hand.remove(i));
		updateGui();
	}

}
