import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		ArrayList<Card> mainDeck = new ArrayList<Card>();
		Card BHS = new Rock("Black Hole Sun",1);
		Card WAP = new Pop("WAP",1);
		Card TFinD = new Classical("Toccata and Fugue in D minor",1);
		Card BohRhap = new Rock("Bohemian Rhapsody",1);
		mainDeck.add(BHS);
		mainDeck.add(WAP);
		mainDeck.add(TFinD);
		mainDeck.add(BohRhap);
		Collections.shuffle(mainDeck);
		for(int i=0;i<mainDeck.size();i++) {
			System.out.println(i+ " "+mainDeck.get(i));
		}
	}

}
