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
		int index;
		Card c;
		switch(name) {
		//rock
		case("Black Hole Sun"):
			return;
		case("Sweet Emotion"):
			return;
		case("Back in Black"):
			index = (int)(Math.random()*r.discard.size());
			r.addToHand(r.discard.remove(index));
			return;
		case("Bohemian Rhapsody"):
			r.playTwice++;
			return;
		case("We Will Rock You"):
			r.addToHand(Main.getCard("We Are The Champions"));
			return;
		case("Livin\' On A Prayer"):
			if(r.RIDE_DURATION/2>=r.remainingTurns)
				r.playTwice+=2;
			return;
			
		//electronic
		case("Lean On"):
			return;
		case("Helicopter"):
			return;
		case("Turn Down For What"):
			r.currentMana+=2;
			index = (int)(Math.random()*r.hand.size());
			r.discard.add(r.hand.remove(index));
			return;
		case("Digital Love"):
			r.addToHand(Main.getCard("One More Time"));
			return;
		case("Around The World"):
			index = (int)(Math.random()*r.deck.size());
			r.deck.add(index,Main.getCard("Get Lucky"));
			return;
		case("Closer"):
			r.currentMana++;
			r.drawCard(1);
			return;
			
		//hip-hop
		case("Fight The Power"):
			return;
		case("Keep Ya Head Up"):
			return;
		case("C.R.E.A.M"):
			r.drawCard(3);
			r.discardCard(-1);
			return;
		case("Lose Yourself"):
			r.discardHand();
			r.drawCard(4);
			return;
		case("It Was A Good Day"):
			r.drawCard(2);
			return;
		case("Still D.R.E."):
			r.addToHand(Main.getCard("Nothin\' But A G Thang"));
			return;
		case("Gangsta's Paradise"):
			while(r.hand.size()<r.MAX_HAND_SIZE && r.discard.size()>0) {
				r.addToHand(r.discard.remove(r.discard.size()-1));
			}
			return;
		
		//pop
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
			return;
		case("Hollaback Girl"):
			r.hand.add(Main.getCard("Don't Speak"));
			return;
		case("Uptown Funk"):
			r.deck.add(Main.getCard("Locked Out of Heaven"));
			return;
			
		//classical
		case("Für Elise"):
			return;
		case("Toccata and Fugue"):
			return;
		case("Symphony No.5"):
			r.maxMana++;
			return;
		case("The Four Seasons"):
			double sep = r.deck.size()/4;
			r.deck.add(0,Main.getCard("Spring"));
			r.deck.add((int)sep,Main.getCard("Summer"));
			r.deck.add((int)(sep*2),Main.getCard("Autumn"));
			r.deck.add(Main.getCard("Winter"));
			return;
		case("William Tell Overture"):
			r.turnStartCards++;
			return;
		case("Blue Danube"):
			r.maxMana+=2;
			return;
		case("1812 Overture"):
			r.playTwiceRegen++;
			return;
		
		
		//rock-gen
		case("We Are The Champions"):
			r.remainingTurns--;
			return;
			
			
		//electronic-gen
		case("One More Time"):
			if(r.discard.size()==0)
				return;
			c = r.discard.remove(r.discard.size()-1);
			c.cost=0;
			r.addToHand(c);
			return;
		case("Get Lucky"):
			c=Main.getRandNormCard();
			c.cost=0;
			r.addToHand(c);
			return;

		//hiphop-gen
		case("Nothin\' But A G Thang"):
			r.drawCard(2);
			r.discardCard(-1);
			return;
		
		//pop-gen
		case("Everybody"):
			r.hand.get((int) ((Math.random()*r.hand.size()))).cost=0;
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

		//classical-gen
		case("Spring"):
			r.refundRegen++;
			return;
		case("Summer"):
			r.turnStartCards+=2;
			return;
		case("Autumn"):
			index=0;
			while(r.exhaust.size()>0 && index<3) {
				r.deck.add(r.exhaust.remove(0));
				index++;
			}
			return;
			
		case("Winter"):
			for(Card h:r.hand)
				h.cost=0;
			return;
			
		}
	}
	
	public Object clone() {
		return null;
	}
	
	public String getText() {
		switch(name) {
		//rock
		case("Black Hole Sun"):
			return "";
		case("Sweet Emotion"):
			return "";
		case("Back in Black"):
			return "Return a random card from your discard pile to your hand.";
		case("Bohemian Rhapsody"):
			return "The next card you play this turn is played twice.";
		case("We Will Rock You"):
			return "Add a \"We Are the Champions\" to your hand.  Exhaust.";
		case("Livin\' On A Prayer"):
			return "If your trip is halfway over, the next two cards you play this turn are played twice.";
		
		//electronic
		case("Lean On"):
			return "";
		case("Helicopter"):
			return "";
		case("Turn Down For What"):
			return "Add 2 Time.  Discard a random card in hand.";
		case("Digital Love"):
			return "Create a \"One More Time\" in hand.  Exhaust.";
		case("Around The World"):
			return "Create a \"Get Lucky\" (Pop) in deck.";
		case("Closer"):
			return "Add a Time.  Draw a card.";
		
		//hip-hop
		case("Fight The Power"):
			return "";
		case("Keep Ya Head Up"):
			return "";
		case("C.R.E.A.M"):
			return "Draw 3 cards, then discard a random card.";
		case("Lose Yourself"):
			return "Discard your hand, then draw 4 cards.";
		case("It Was A Good Day"):
			return "Draw 2 cards.";
		case("Still D.R.E."):
			return "Add a \"Nothin' But A G Thang\" to your hand.";
		case("Gangsta's Paradise"):
			return "Draw all cards from your discard pile.  Exhaust.";
		
		//pop
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
		
		//classical
		case("Für Elise"):
			return "";
		case("Toccata and Fugue"):
			return "";
		case("Symphony No.5"):
			return "Gain 1 extra Time at the start of each turn.  Exhaust.";
		case("The Four Seasons"):
			return "Add the Four Seasons to your deck.  Exhaust.";
		case("William Tell Overture"):
			return "Draw an extra card each turn.  Exhaust";
		case("Blue Danube"):
			return "Gain 2 extra Time at the start of each turn.  Exhaust.";
		case("1812 Overture"):
			return "Double the first card you play each turn.  Exhaust.";
		
		
		
		//rock-gen
		case("We Are The Champions"):
			return "Advance the turn counter by 1.  Exhaust.";
		
		//electronic-gen
		case("One More Time"):
			return "Return the top card of your discard to your hand.  It costs 0.  Exhaust.";
		case("Get Lucky"):
			return "Add a random card to your hand.  It costs 0.  Exhaust.";
					
		//hiphop-gen
		case("Nothin\' But A G Thang"):
			return "Draw 2 cards, then discard a random card.  Exhaust.";
		
		//pop-gen
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
		
		//classical-gen
		case("Spring"):
			return "Each turn, refund the Time for the first card played.  Exhaust.";
		case("Summer"):
			return "Draw 2 extra cards at the start of your turn.  Exhaust.";
		case("Autumn"):
			return "Return 3 exhausted cards to your deck.  Exhaust.";
		case("Winter"):
			return "Set the cost of all cards in hand to 0.  Exhaust.";
		
		}
		
		return "missing card text";
	}
	
	public boolean exhausts() {
		switch(name) {
		case("Symphony No.5"):
		case("The Four Seasons"):
		case("William Tell Overture"):
		case("Blue Danube"):
		case("1812 Overture"):
		case("Spring"):
		case("Summer"):
		case("Autumn"):
		case("Winter"):
		case("Gangsta's Paradise"):
		case("Nothin\' But A G Thang"):
		case("Digital Love"):
		case("One More Time"):
		case("Get Lucky"):
		case("We Will Rock You"):
		case("We Are The Champions"):
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
