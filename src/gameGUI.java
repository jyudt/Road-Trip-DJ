import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
@SuppressWarnings("serial")
public class gameGUI extends JFrame {
	JFrame f;
	ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
	ArrayList<String> cardImageNames = new ArrayList<String>();
	private final Dimension CARD_DIM =new Dimension(180,300); 
	private final Dimension CARD_TEXT_DIM =new Dimension(165,130); 
	private final Dimension HAND_SIZE_DIM = new Dimension(1850, 320); 
	private final Dimension TIMER_SIZE_DIM = new Dimension(1850, 100); 
	private final Dimension RIDER_SIZE_DIM = new Dimension(1850, 500); 
	private final Dimension MANA_SIZE_DIM = new Dimension(1850, 100); 

	

	
	private JPanel hand;
	private ArrayList<Card> handList = new ArrayList<Card>();
	
	private JPanel timerP;
	private JLabel timer;
	
	private JLabel deckSize;
	private JLabel discardSize;
	private JLabel exhaustSize;
	
	private JPanel riderP;

	private JPanel manaP;
	
	public gameGUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1920,1080); 
		setResizable(false);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
				
		hand = new JPanel();
		hand.setLayout(new BoxLayout(hand, BoxLayout.X_AXIS));
		hand.setAlignmentX(CENTER_ALIGNMENT);
		hand.setMaximumSize(HAND_SIZE_DIM);
		hand.setPreferredSize(HAND_SIZE_DIM);
		
		timerP = new JPanel();
		timerP.setLayout(new BoxLayout(timerP, BoxLayout.X_AXIS));
		timerP.setAlignmentX(CENTER_ALIGNMENT);
		timerP.setMaximumSize(TIMER_SIZE_DIM);
		timerP.setPreferredSize(TIMER_SIZE_DIM);
		
		BufferedImage deckImageFile = null;
		BufferedImage discardImageFile = null;
		BufferedImage exhaustImageFile = null;
		try {
			deckImageFile = ImageIO.read(new File("./img/deck.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find deck image");
		}
		try {
			discardImageFile = ImageIO.read(new File("./img/discard.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find discard image");
		}
		try {
			exhaustImageFile = ImageIO.read(new File("./img/exile.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find exhaust image");
		}
		
		JLabel deckImage = new JLabel(new ImageIcon(deckImageFile.getScaledInstance(90, 90, Image.SCALE_FAST)));
		deckImage.setAlignmentX(CENTER_ALIGNMENT);
		JLabel discardImage = new JLabel(new ImageIcon(discardImageFile.getScaledInstance(90, 90, Image.SCALE_FAST)));
		discardImage.setAlignmentX(CENTER_ALIGNMENT);
		JLabel exhaustImage = new JLabel(new ImageIcon(exhaustImageFile.getScaledInstance(90, 90, Image.SCALE_FAST)));
		exhaustImage.setAlignmentX(CENTER_ALIGNMENT);
		deckSize = new JLabel("0");
		deckSize.setFont(new Font(deckSize.getFont().getName(), Font.BOLD, 30));
		discardSize = new JLabel("0");
		discardSize.setFont(new Font(discardSize.getFont().getName(), Font.BOLD, 30));
		exhaustSize = new JLabel("0");
		exhaustSize.setFont(new Font(exhaustSize.getFont().getName(), Font.BOLD, 30));
		timer = new JLabel("0");
		timer.setFont(new Font(timer.getFont().getName(), Font.BOLD, 50));
		
		timerP.add(Box.createHorizontalStrut(100));
		timerP.add(deckImage);
		timerP.add(Box.createHorizontalStrut(30));
		timerP.add(deckSize);
		timerP.add(Box.createHorizontalGlue());
		timerP.add(timer);
		timerP.add(Box.createHorizontalGlue());
		timerP.add(discardImage);
		timerP.add(Box.createHorizontalStrut(30));
		timerP.add(discardSize);
		timerP.add(Box.createHorizontalStrut(30));
		timerP.add(exhaustImage);
		timerP.add(Box.createHorizontalStrut(30));
		timerP.add(exhaustSize);
		
		riderP = new JPanel();
		riderP.setLayout(new BoxLayout(riderP, BoxLayout.X_AXIS));
		riderP.setAlignmentX(CENTER_ALIGNMENT);
		riderP.setMaximumSize(RIDER_SIZE_DIM);
		riderP.setPreferredSize(RIDER_SIZE_DIM);
		
		riderP.setBackground(Color.red);
		
		manaP = new JPanel();
		manaP.setLayout(new BoxLayout(manaP, BoxLayout.X_AXIS));
		manaP.setAlignmentX(CENTER_ALIGNMENT);
		manaP.setMaximumSize(MANA_SIZE_DIM);
		manaP.setPreferredSize(MANA_SIZE_DIM);
		
		manaP.setBackground(Color.ORANGE);
		
		add(Box.createVerticalStrut(10));
		add(timerP);
		add(Box.createVerticalStrut(5));
		add(riderP);
		add(Box.createVerticalGlue());
		add(manaP);
		add(Box.createVerticalGlue());
		add(hand);
		drawHand();

		
		setVisible(true);

	}
	
	public void setTimer(int i) {
		timer.setText(Integer.toString(i));
	}
	
	public void deckSize(int i) {
		deckSize.setText(Integer.toString(i));
	}
	public void discardSize(int i) {
		discardSize.setText(Integer.toString(i));
	}
	public void exhaustSize(int i) {
		exhaustSize.setText(Integer.toString(i));
	}
	
	public void drawHand() {
		hand.removeAll();
		hand.add(Box.createHorizontalGlue());
		for(int i=0;i<handList.size();i++) {
			JPanel card = drawCard(handList.get(i));
			card.setMaximumSize(CARD_DIM);
			hand.add(card);
			if(i<handList.size()-1)
				hand.add(Box.createHorizontalStrut(10));
		}
		hand.add(Box.createHorizontalGlue());
		hand.updateUI();
		System.out.println(handList.toString());
	}
	
	public void passHand(ArrayList<Card> inp) {
		handList = inp;
		drawHand();
	}
	
	private JPanel drawCard(Card c) {
		BufferedImage myPicture = null;
		int imageIndex = -1;
		for(int i=0;i<cardImageNames.size();i++) {
			if(cardImageNames.get(i).equals(c.getName())) {
				imageIndex = i;
			}
		}
		if(imageIndex==-1) {
			File toRead = new File("./img/"+c.getName()+".png");
			if(!toRead.exists()) {
				System.out.println("error finding image file");
				toRead = new File("./img/test.png");
			}
			try {
				System.out.println(toRead.getPath());
				myPicture = ImageIO.read(toRead);
			} catch (Exception e) {
				System.out.println("error finding image");
				e.printStackTrace();
			}
			if(toRead.exists()) {
				cardImages.add(myPicture);
				cardImageNames.add(c.getName());
			}
		} else {
			myPicture = cardImages.get(imageIndex);
		}
		
		JPanel cardP = new JPanel();
		cardP.setLayout(new BoxLayout(cardP, BoxLayout.Y_AXIS));
		cardP.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel title = new JLabel(c.getName());
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 18));
		JLabel cardImage = new JLabel(new ImageIcon(myPicture.getScaledInstance(150, 120, Image.SCALE_FAST)));
		cardImage.setAlignmentX(CENTER_ALIGNMENT);
		JPanel textP = new JPanel();
		
		textP.setMaximumSize(CARD_TEXT_DIM);
		textP.setPreferredSize(CARD_TEXT_DIM);
		textP.setBorder(BorderFactory.createLineBorder(Color.black));
		textP.setAlignmentX(CENTER_ALIGNMENT);
		JLabel text = new JLabel(c.getText());
		text.setAlignmentX(CENTER_ALIGNMENT);
		textP.add(text);
		
		cardP.setSize(CARD_DIM);
		textP.setMaximumSize(CARD_DIM);
		textP.setPreferredSize(CARD_DIM);
		cardP.add(title);
		cardP.add(Box.createVerticalStrut(10));
		cardP.add(cardImage);
		cardP.add(Box.createVerticalGlue());
		cardP.add(textP);
		cardP.add(Box.createVerticalGlue());
		cardP.setBackground(Color.red);
		return cardP;
	}

}
