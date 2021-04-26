import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	static ArrayList<Card> allCards;
	static ArrayList<Card> normCards;
	private static ArrayList<Card> mainDeck = new ArrayList<Card>();
	public static JFrame mainFrame;
	public static int inp;

	public static void main(String[] args) {	
		mainFrame = new JFrame("Road Trip DJ");
		
		InputStream is = Main.class.getResourceAsStream("img/bensound-downtown.wav");
		InputStream bis = new BufferedInputStream(is);
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(bis);
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			mainFrame.setTitle("oops1 "+e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			mainFrame.setTitle("oops2 "+e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip = null;
		if(audioInputStream!=null) {
			try {
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				mainFrame.setTitle("oops3  "+e.toString());

				e.printStackTrace();
			}
		}
		

		
		inp = 0;	
		TitleScreen ts = new TitleScreen(mainFrame);
		
		if(clip!=null) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		while(inp==0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		startGame(inp);
	}
	
	public static void startGame(int i) {

		System.out.println("test");
		initializeCards();
		mainDeck = buildDefaultDeck();
		Ride ride = null;
		switch(i) {
		case(1):
			ride = new Ride(mainFrame, allCards, mainDeck,10, 3,i);
			break;
		case(2):
			ride = new Ride(mainFrame, allCards, mainDeck,20, 4,i);
			break;
		case(3):
			ride = new Ride(mainFrame, allCards, mainDeck,30, 5,i);
			break;

		}
		if(ride!=null)
			ride.beginRide();
	}
	
	
	private static void initializeCards(){
		normCards =  new ArrayList<Card>();
		//rock
		normCards.add(new Rock("Black Hole Sun",1));
		normCards.add(new Rock("Sweet Emotion",0));
		normCards.add(new Rock("Back in Black",1));
		normCards.add(new Rock("Bohemian Rhapsody",2));
		normCards.add(new Rock("We Will Rock You",1));
		normCards.add(new Rock("Living On A Prayer",1));
		
		//electronic
		normCards.add(new Electronic("Lean On",1));
		normCards.add(new Electronic("Wake Me Up",0));
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
		
		//classical
		normCards.add(new Classical("Für Elise",1));
		normCards.add(new Classical("Toccata and Fugue",0));
		normCards.add(new Classical("Symphony No.5",3));
		normCards.add(new Classical("The Four Seasons",3));
		normCards.add(new Classical("William Tell Overture",2));
		normCards.add(new Classical("Blue Danube",5));
		normCards.add(new Classical("1812 Overture",4));
		
		
		allCards =  new ArrayList<Card>();
		for(Card c:normCards) {
			allCards.add((Card) c.clone());
		}
		
		//rock-gen
		allCards.add(new Rock("We Are The Champions",3));
		
		//electronic-gen
		allCards.add(new Electronic("One More Time",1));
		allCards.add(new Electronic("Get Lucky",1));
		
		//hiphop-gen
		allCards.add(new HipHop("Nothin' But A G Thang",0));
		
		//pop-gen
		allCards.add(new Pop("Everybody",1));
		allCards.add(new Pop("No Place",1));
		allCards.add(new Pop("Any Way You Want It",0));
		allCards.add(new Rock("Don't Speak",1));
		allCards.add(new Pop("Locked Out of Heaven",0));
		
		//classical-gen
		allCards.add(new Classical("Spring",3));
		allCards.add(new Classical("Summer",3));
		allCards.add(new Classical("Autumn",3));
		allCards.add(new Classical("Winter",3));
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
	
	private static ArrayList<Card> buildDefaultDeck() {
		int main =(int) (Math.random()*5);
		int sub=(int) (Math.random()*5);
		int[] nums = {main,sub};
		String choice = "Your deck is: ";
		ArrayList<Card> myDeck = new ArrayList<Card>();
		int rng;
		for(int n:nums) {
			switch(n) {
			case(0):
				choice+="rock";
				//rock
				for(int i=0;i<7;i++)
					myDeck.add(Main.getCard("Black Hole Sun"));
				for(int i=0;i<3;i++)
					myDeck.add(Main.getCard("Sweet Emotion"));
				rng = 0;
				for(int i=0;i<7;i++) {
					rng = (int)(Math.random()*100);
					if(rng<50) {
						continue;
					} else if(rng<65) {
						myDeck.add(Main.getCard("Back in Black"));
					} else if(rng<80) {
						myDeck.add(Main.getCard("Bohemian Rhapsody"));
					} else if(rng<95) {
						myDeck.add(Main.getCard("Living On A Prayer"));
					} else {
						myDeck.add(Main.getCard("We Will Rock You"));
					}
				}
				break;
			case(1):
				choice+="electronic";
				//electronic
				for(int i=0;i<7;i++)
					myDeck.add(Main.getCard("Lean On"));
				for(int i=0;i<3;i++)
					myDeck.add(Main.getCard("Wake Me Up"));
				rng = 0;
				for(int i=0;i<7;i++) {
					rng = (int)(Math.random()*100);
					if(rng<48) {
						continue;
					} else if(rng<61) {
						myDeck.add(Main.getCard("Turn Down For What"));
					} else if(rng<74) {
						myDeck.add(Main.getCard("Digital Love"));
					} else if(rng<87) {
						myDeck.add(Main.getCard("Around The World"));
					} else {
						myDeck.add(Main.getCard("Closer"));
					}
				}
				break;
			case(2):
				choice+="hip-hop";
				//hip-hop
				for(int i=0;i<7;i++)
					myDeck.add(Main.getCard("Fight The Power"));
				for(int i=0;i<3;i++)
					myDeck.add(Main.getCard("Keep Ya Head Up"));
				rng = 0;
				for(int i=0;i<7;i++) {
					rng = (int)(Math.random()*100);
					if(rng<49) {
						continue;
					} else if(rng<61) {
						myDeck.add(Main.getCard("C.R.E.A.M"));
					} else if(rng<73) {
						myDeck.add(Main.getCard("Lose Yourself"));
					} else if(rng<85) {
						myDeck.add(Main.getCard("It Was A Good Day"));
					} else if(rng<95) {
						myDeck.add(Main.getCard("Still D.R.E."));
					} else {
						myDeck.add(Main.getCard("Gangsta's Paradise"));
					}
				}
				break;
			case(3):
				choice+="pop";
				//pop
				for(int i=0;i<7;i++)
					myDeck.add(Main.getCard("Thriller"));
				for(int i=0;i<3;i++)
					myDeck.add(Main.getCard("Shake It Off"));
				rng = 0;
				for(int i=0;i<7;i++) {
					rng = (int)(Math.random()*100);
					if(rng<51) {
						continue;
					} else if(rng<64) {
						myDeck.add(Main.getCard("I Want It That Way"));
					} else if(rng<77) {
						myDeck.add(Main.getCard("Don't Stop Believing"));
					} else if(rng<90) {
						myDeck.add(Main.getCard("Uptown Funk"));
					} else {
						myDeck.add(Main.getCard("Hollaback Girl"));
					}
				}
				break;
			case(4):
				choice+="classical";
				//classical
				for(int i=0;i<7;i++)
					myDeck.add(Main.getCard("Für Elise"));
				for(int i=0;i<3;i++)
					myDeck.add(Main.getCard("Toccata and Fugue"));
				rng = 0;
				for(int i=0;i<7;i++) {
					rng = (int)(Math.random()*100);
					if(rng<50) {
						continue;
					} else if(rng<64) {
						myDeck.add(Main.getCard("Symphony No.5"));
					} else if(rng<78) {
						myDeck.add(Main.getCard("William Tell Overture"));
					} else if(rng<83) {
						myDeck.add(Main.getCard("Blue Danube"));
					} else if(rng<90) {
						myDeck.add(Main.getCard("1812 Overture"));
					} else {
						myDeck.add(Main.getCard("The Four Seasons"));
					}
				}
				break;
			}
			choice+= " and ";
		}
		if(nums[0]!=0 && nums[1]!=0) {
			for(int i=0;i<3;i++)
				myDeck.add(Main.getCard("Black Hole Sun"));
		}
		if(nums[0]!=1 && nums[1]!=1) {
			for(int i=0;i<3;i++)
				myDeck.add(Main.getCard("Lean On"));
		}
		if(nums[0]!=2 && nums[1]!=2) {
			for(int i=0;i<3;i++)
				myDeck.add(Main.getCard("Fight The Power"));
		}
		if(nums[0]!=3 && nums[1]!=3) {
			for(int i=0;i<3;i++)
				myDeck.add(Main.getCard("Thriller"));
		}
		if(nums[0]!=4 && nums[1]!=4) {
			for(int i=0;i<3;i++)
				myDeck.add(Main.getCard("Für Elise"));
		}
		
		choice = choice.substring(0,choice.length()-4);
		System.out.println(choice);
		return myDeck;
	}
	
	public static double getScalingFactor() {
		GraphicsConfiguration g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		AffineTransform trf = g.getDefaultTransform();
		double x = (trf.getScaleX());
		double y = (trf.getScaleY());
		if(x==y) {
			return x;
		}
		return 1;
	}
	
	public static JFrame getFrame() {
		return mainFrame;
	}
}
