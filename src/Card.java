import java.util.ArrayList;

public abstract class Card {
	protected String name;
	protected int cost;
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public String toString() {
		ArrayList<Object> outs = new ArrayList<Object>();
		outs.add(this.getClass().getName());
		outs.add(name);
		outs.add(cost);
		
		String outStr = "";
		for(Object o:outs) {
			outStr+=o + " ";
		}
		return outStr;
	}
	
	public void playCard(Ride r) {
		switch(name) {
		case("Black Hole Sun"):
			return;
		
		
		case("Thriller"):
			return;
		case("Shake It Off"):
			return;
		case("I Want It That Way"):
			r.addToHand(Main.getCard("Everybody"));
			r.addToHand(Main.getCard("No Place"));
			return;
		case("Don't Stop Believing"):
			int pos = (int) (Math.random()*r.deck.size());
			r.deck.add(pos,Main.getCard("Any Way You Want It"));
			r.updateGui();
			return;
		case("Hollaback Girl"):
			r.hand.add(Main.getCard("Don't Speak"));
			r.updateHand();
			return;
		case("Uptown Funk"):
			r.deck.add(Main.getCard("Locked Out of Heaven"));
			r.updateGui();
			return;
		
		
		case("Everybody"):
			r.hand.get((int) ((Math.random()*r.hand.size()))).cost=0;
			r.updateHand();
			return;
		case("No Place"):
			r.exhCardInHand((int) ((Math.random()*r.hand.size())));
			return;
		case("Any Way You Want It"):
			r.drawCard(1);
			return;
		case("Don't Speak"):
			r.drawCard(1);
			return;
		case("Locked Out of Heaven"):
			r.drawCard(2);
			return;

			
			
		}
	}
	
	public Object clone() {
		return null;
	}
	
	public String getText() {
		switch(name) {
		case("Black Hole Sun"):
			return "";
		
		
		case("Thriller"):
			return "";
		case("Shake It Off"):
			return "";
		case("I Want It That Way"):
			return "Create an \"Everybody\" and a \"No Place\" in hand.";
		case("Don't Stop Believing"):
			return "Create an “Any Way You Want It” in your deck.";
		case("Hollaback Girl"):
			return "Create a “Don’t Speak” (Rock) in hand.";
		case("Uptown Funk"):
			return "Create a “Locked Out of Heaven” on the bottom of your deck.";
		
		
		
		
		case("Everybody"):
			return "Set the cost of a random card in your hand to 0.  Exhaust.";
		case("No Place"):
			return "Exhaust a random card in your hand.  Exhaust.";
		case("Any Way You Want It"):
			return "Draw a card.  Exhaust.";
		case("Don't Speak"):
			return "Draw a card.";
		case("Locked Out of Heaven"):
			return "Draw 2 cards.  Exhaust.";
		
		}
		
		return "missing card text";
	}
	
	public boolean exhausts() {
		switch(name) {
		case("Any Way You Want It"):
		case("Locked Out of Heaven"):
		case("Everybody"):
		case("No Place"):
			return true;
		}
		
		return false;
	}
	
	public String getType() {
		return this.getClass().getName();
	}
}
