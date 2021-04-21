import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
	private final Dimension HAND_SIZE_DIM = new Dimension(1880	, 320); 
	private final Dimension TIMER_SIZE_DIM = new Dimension(1850, 100); 
	private final Dimension RIDERS_SIZE_DIM = new Dimension(1850, 500); 
	private final Dimension MANA_SIZE_DIM = new Dimension(1850, 100);
	private final Dimension BTN_SIZE_DIM = new Dimension(200, 90); 
	private final Dimension RIDER_SIZE_DIM = new Dimension(300, 600); 
	private final Dimension HEALTH_SIZE_DIM = new Dimension(300, 30); 
	private final Dimension DECK_IMG_SIZE_DIM = new Dimension(90, 90); 
	private final Dimension TIME_IMG_SIZE_DIM = new Dimension(90, 90); 
	private final Dimension RIDER_IMG_SIZE_DIM = new Dimension(180, 380); 
	private final Dimension CARD_IMG_SIZE_DIM = new Dimension(150, 120); 


	
	private ArrayList<Rider> riders = new ArrayList<Rider>();
	
	private BufferedImage happy;
	private BufferedImage neut;
	private BufferedImage sad;

	
	private JPanel hand;
	private ArrayList<Card> handList = new ArrayList<Card>();
	
	private JPanel timerP;
	private JLabel timer;
	
	private JLabel dupeImg;
	private JLabel refundImg;
	private JLabel dupeL;
	private JLabel refundL;
	
	private JLabel deckSize;
	private JLabel discardSize;
	private JLabel exhaustSize;
	
	private JPanel riderP;

	private JPanel manaP;
	private JLabel mana;
	private JButton endTurn;
	
	private Ride ride;
	
	private double scale;
	
	public gameGUI(Ride r) {
		super("Road Trip DJ");
		ride = r;
		riders=r.getRiders();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		scale = 1/Main.getScalingFactor();
		setSize((int)(1920/scale),(int)(1080/scale));
		setResizable(false);
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		scaleDim(CARD_DIM, scale);
		scaleDim(CARD_TEXT_DIM, scale);
		scaleDim(TIMER_SIZE_DIM, scale);
		scaleDim(RIDERS_SIZE_DIM, scale);
		scaleDim(MANA_SIZE_DIM, scale);
		scaleDim(BTN_SIZE_DIM, scale);
		scaleDim(RIDER_SIZE_DIM, scale);
		scaleDim(HEALTH_SIZE_DIM, scale);		
		scaleDim(DECK_IMG_SIZE_DIM, scale);		
		scaleDim(TIME_IMG_SIZE_DIM, scale);		
		scaleDim(RIDER_IMG_SIZE_DIM, scale);		
		scaleDim(CARD_IMG_SIZE_DIM, scale);		
				
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
		BufferedImage timeImageFile = null;
		BufferedImage dupeImageFile = null;
		BufferedImage refundImageFile = null;

		
		try {
			deckImageFile = ImageIO.read(getClass().getResource("img/deck.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find deck image");
		}
		try {
			discardImageFile = ImageIO.read(getClass().getResource("img/discard.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find discard image");
		}
		try {
			exhaustImageFile = ImageIO.read(getClass().getResource("img/exile.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find exhaust image");
		}
		try {
			timeImageFile = ImageIO.read(getClass().getResource("img/stopwatch.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find time image");
		}
		try {
			happy = ImageIO.read(getClass().getResource("img/riderHappy.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find happy image");
		}
		try {
			neut = ImageIO.read(getClass().getResource("img/riderNeut.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find neutral image");
		}
		try {
			sad = ImageIO.read(getClass().getResource("img/riderSad.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find sad image");
		}
		try {
			dupeImageFile = ImageIO.read(getClass().getResource("img/cardDupe.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find dupe image");
		}
		try {
			refundImageFile = ImageIO.read(getClass().getResource("img/rewind.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find rewind image");
		}
		
		JLabel deckImage = new JLabel(new ImageIcon(deckImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		deckImage.setAlignmentX(CENTER_ALIGNMENT);
		JLabel discardImage = new JLabel(new ImageIcon(discardImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		discardImage.setAlignmentX(CENTER_ALIGNMENT);
		JLabel exhaustImage = new JLabel(new ImageIcon(exhaustImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		exhaustImage.setAlignmentX(CENTER_ALIGNMENT);
		JLabel timeImage = new JLabel(new ImageIcon(timeImageFile.getScaledInstance(TIME_IMG_SIZE_DIM.width,TIME_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		timeImage.setAlignmentX(CENTER_ALIGNMENT);
		dupeImg = new JLabel(new ImageIcon(dupeImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		dupeImg.setAlignmentX(CENTER_ALIGNMENT);
		refundImg = new JLabel(new ImageIcon(refundImageFile.getScaledInstance(TIME_IMG_SIZE_DIM.width,TIME_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		refundImg.setAlignmentX(CENTER_ALIGNMENT);
		
		deckSize = new JLabel("0");
		deckSize.setFont(new Font(deckSize.getFont().getName(), Font.BOLD, (int) (30*scale)));
		discardSize = new JLabel("0");
		discardSize.setFont(new Font(discardSize.getFont().getName(), Font.BOLD, (int) (30*scale)));
		exhaustSize = new JLabel("0");
		exhaustSize.setFont(new Font(exhaustSize.getFont().getName(), Font.BOLD, (int) (30*scale)));
		timer = new JLabel("0");
		timer.setFont(new Font(timer.getFont().getName(), Font.BOLD, (int) (50*scale)));
		mana = new JLabel("0");
		mana.setFont(new Font(timer.getFont().getName(), Font.BOLD, (int) (50*scale)));
		dupeL = new JLabel("0");
		dupeL.setFont(new Font(dupeL.getFont().getName(), Font.BOLD, (int) (30*scale)));
		refundL = new JLabel("0");
		refundL.setFont(new Font(refundL.getFont().getName(), Font.BOLD, (int) (30*scale)));
		
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("button press");
	            sendInput(-1); 
			}  
		});
		endTurn.setMaximumSize(BTN_SIZE_DIM);
		endTurn.setPreferredSize(BTN_SIZE_DIM);
		
		timerP.add(Box.createHorizontalStrut((int) (100/scale)));
		timerP.add(deckImage);
		timerP.add(Box.createHorizontalStrut((int) (30/scale)));
		timerP.add(deckSize);
		timerP.add(Box.createHorizontalStrut((int) (50/scale)));
		timerP.add(dupeImg);
		timerP.add(Box.createHorizontalStrut((int) (30/scale)));
		timerP.add(dupeL);
		timerP.add(Box.createHorizontalGlue());
		timerP.add(timer);
		timerP.add(Box.createHorizontalGlue());
		timerP.add(refundImg);
		timerP.add(Box.createHorizontalStrut((int) (30/scale)));
		timerP.add(refundL);
		timerP.add(Box.createHorizontalStrut((int) (50/scale)));
		timerP.add(discardImage);
		timerP.add(Box.createHorizontalStrut((int) (30/scale)));
		timerP.add(discardSize);
		timerP.add(Box.createHorizontalStrut((int) (50/scale)));
		timerP.add(exhaustImage);
		timerP.add(Box.createHorizontalStrut((int) (30/scale)));
		timerP.add(exhaustSize);

		refundL.setVisible(false);
		refundImg.setVisible(false);
		dupeL.setVisible(false);
		dupeImg.setVisible(false);

		
		riderP = new JPanel();
		riderP.setLayout(new BoxLayout(riderP, BoxLayout.X_AXIS));
		riderP.setAlignmentX(CENTER_ALIGNMENT);
		riderP.setMaximumSize(RIDERS_SIZE_DIM);
		riderP.setPreferredSize(RIDERS_SIZE_DIM);
		
		manaP = new JPanel();
		manaP.setLayout(new BoxLayout(manaP, BoxLayout.X_AXIS));
		manaP.setAlignmentX(CENTER_ALIGNMENT);
		manaP.setMaximumSize(MANA_SIZE_DIM);
		manaP.setPreferredSize(MANA_SIZE_DIM);
		
		manaP.add(timeImage);
		manaP.add(Box.createHorizontalStrut((int) (10/scale)));
		manaP.add(mana);
		manaP.add(Box.createHorizontalGlue());
		manaP.add(endTurn);
		
		add(Box.createVerticalStrut((int) (10/scale)));
		add(timerP);
		add(Box.createVerticalStrut((int) (5/scale)));
		add(riderP);
		add(Box.createVerticalGlue());
		add(manaP);
		add(Box.createVerticalGlue());
		add(hand);
		
		
		
		drawHand();
		drawRiders();

		
		hand.setBackground(Color.decode("#f7f6bc"));
		riderP.setBackground(Color.decode("#c4c4c4"));
		
		setVisible(true);
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

	}
	
	public void sendInput(int i) {
		ride.passInput(i);
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
	public void updateBuffs() {
		int ref = ride.refund;
		if(ref!=0) {
			refundL.setText(Integer.toString(ref));
			enableRefund();
		}
		int dup = ride.playTwice;
		if(dup!=0) {
			dupeL.setText(Integer.toString(dup));
			enableDupe();
		}
		if(ride.refundRegen>0)
			enableRefund();
		if(ride.playTwiceRegen>0)
			enableDupe();
	}
	private void enableRefund() {
		if(refundL.isVisible()&&refundImg.isVisible())
			return;
		refundL.setVisible(true);
		refundImg.setVisible(true);
	}
	private void enableDupe() {
		if(dupeImg.isVisible()&&dupeL.isVisible())
			return;
		dupeL.setVisible(true);
		dupeImg.setVisible(true);
	}
	
	public void drawRiders() {
		riderP.removeAll();
		riderP.add(Box.createHorizontalGlue());
		for(int i=0;i<riders.size();i++) {
			JPanel rdr = drawRider(riders.get(i));
			rdr.setMaximumSize(RIDER_SIZE_DIM);
			riderP.add(rdr);
			if(i<riders.size())
				riderP.add(Box.createHorizontalStrut((int) (10/scale)));
		}
		riderP.add(Box.createHorizontalGlue());
		riderP.updateUI();
		
		
	}
	
	private JPanel drawRider(Rider r) {
		int happiness = r.getHappiness();
		BufferedImage myImage = null;
		if(happiness>60) {
			myImage = happy;
		} else if(happiness>30) {
			myImage = neut;
		} else {
			myImage = sad;
		}
		
		JPanel rider = new JPanel();
		rider.setLayout(new BoxLayout(rider, BoxLayout.Y_AXIS));
		rider.setPreferredSize(RIDER_SIZE_DIM);
		rider.setMaximumSize(RIDER_SIZE_DIM);
		rider.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel riderImage = new JLabel(new ImageIcon(myImage.getScaledInstance(RIDER_IMG_SIZE_DIM.width, RIDER_IMG_SIZE_DIM.height, Image.SCALE_DEFAULT)));
		riderImage.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel name = new JLabel(r.getName());
		name.setAlignmentX(CENTER_ALIGNMENT);
		name.setFont(new Font(name.getFont().getName(), Font.PLAIN, (int) (20*scale)));

		
		JPanel healthP = new JPanel();
		healthP.setLayout(new BoxLayout(healthP, BoxLayout.X_AXIS));
		healthP.setPreferredSize(HEALTH_SIZE_DIM);
		healthP.setMaximumSize(HEALTH_SIZE_DIM);
		
		JProgressBar healthBar = new JProgressBar(0,100);
		healthBar.setValue(happiness);
		
		JLabel healthLab = new JLabel("H: "+Integer.toString(happiness));
		
		healthP.add(Box.createHorizontalStrut((int) (20/scale)));
		healthP.add(healthBar);
		healthP.add(Box.createHorizontalStrut((int) (5/scale)));
		healthP.add(healthLab);
		healthP.add(Box.createHorizontalStrut((int) (10/scale)));
		
		JPanel likesP = new JPanel();
		likesP.setLayout(new BoxLayout(likesP, BoxLayout.X_AXIS));
		likesP.setPreferredSize(HEALTH_SIZE_DIM);
		likesP.setMaximumSize(HEALTH_SIZE_DIM);
		
		JLabel likes = new JLabel(r.getLikesString().substring(0,1));
		likes.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (30*scale)));
		likes.setForeground(Color.green.darker());
		likesP.add(Box.createHorizontalGlue());
		likesP.add(likes);
		
		if(r.getDislikes()!=null) {
			JLabel dislikes = new JLabel(r.getDislikesString().substring(0,1));
			dislikes.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (30*scale)));
			dislikes.setForeground(Color.red);
			likesP.add(Box.createHorizontalStrut((int) (10/scale)));
			likesP.add(dislikes);
		}
		
		for(String s:r.getTraits()) {
			JLabel tr = new JLabel(s);
			tr.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (25*scale)));
			likesP.add(Box.createHorizontalStrut((int) (10/scale)));
			likesP.add(tr);
		}
		likesP.add(Box.createHorizontalGlue());
		
		rider.add(name);
		rider.add(Box.createVerticalStrut((int) (5/scale)));
		rider.add(riderImage);
		rider.add(Box.createVerticalStrut((int) (5/scale)));
		rider.add(healthP);
		rider.add(Box.createVerticalStrut((int) (5/scale)));
		rider.add(likesP);
		
		rider.setOpaque(false);
		
		return rider;
	}
	
	public void drawHand() {
		hand.removeAll();
		hand.add(Box.createHorizontalGlue());
		for(int i=0;i<handList.size();i++) {
			JPanel card = drawCard(handList.get(i),i);
			card.setMaximumSize(CARD_DIM);
			hand.add(card);
			if(i<handList.size()-1)
				hand.add(Box.createHorizontalStrut((int) (10/scale)));
		}
		hand.add(Box.createHorizontalGlue());
		hand.updateUI();
	}
	
	public void passHand(ArrayList<Card> inp) {
		handList = inp;
		drawHand();
	}
	
	public void passTime(int time) {
		mana.setText(Integer.toString(time));
	}
	
	private JPanel drawCard(Card c, int index) {
		BufferedImage myPicture = null;
		int imageIndex = -1;
		
	
		for(int i=0;i<cardImageNames.size();i++) {
			if(cardImageNames.get(i).equals(c.getName())) {
				imageIndex = i;
			}
		}
		if(imageIndex==-1) {
			URL toRead = getClass().getResource("img/"+c.getName()+".png");
			try {
				myPicture = ImageIO.read(toRead);
			} catch (Exception e) {
				System.out.println("error finding image");
				toRead = getClass().getResource("img/test.png");
				try {
					myPicture = ImageIO.read(toRead);
				} catch (IOException e1) {
					System.out.println("error finding test image");
					e1.printStackTrace();
					return null;
				}
				e.printStackTrace();
			}
			cardImages.add(myPicture);
			cardImageNames.add(c.getName());
		} else {
			myPicture = cardImages.get(imageIndex);
		}
		
		JPanel cardP = new JPanel();
		cardP.setLayout(new BoxLayout(cardP, BoxLayout.Y_AXIS));
		cardP.setBorder(new EmptyBorder((int) (10/scale), (int) (10/scale), (int) (10/scale), (int) (10/scale)));
		JPanel topP = new JPanel();
		topP.setLayout(new BoxLayout(topP, BoxLayout.X_AXIS));
		JLabel title = new JLabel(c.getName());
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, (int) (18*scale)));
		JLabel cardImage = new JLabel(new ImageIcon(myPicture.getScaledInstance(CARD_IMG_SIZE_DIM.width,CARD_IMG_SIZE_DIM.height, Image.SCALE_DEFAULT)));
		cardImage.setAlignmentX(CENTER_ALIGNMENT);
		JPanel textP = new JPanel();
		JLabel type = new JLabel(" "+c.getType().substring(0,1)+ " ");
		JLabel manaCost = new JLabel(" "+Integer.toString(c.getCost())+" ");
		manaCost.setFont(new Font(manaCost.getFont().getName(), Font.PLAIN, (int) (20*scale)));
		manaCost.setBackground(Color.white);
		manaCost.setOpaque(true);

		
		textP.setMaximumSize(CARD_TEXT_DIM);
		textP.setLayout(new FlowLayout());
		textP.setPreferredSize(CARD_TEXT_DIM);
		textP.setBorder(BorderFactory.createLineBorder(Color.black));
		textP.setAlignmentX(CENTER_ALIGNMENT);
		JTextArea text = new JTextArea(5,10);
		text.setText(c.getText());
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setOpaque(false);
		text.setEditable(false);
		text.setFocusable(false);
		if(c.getText().length()>60) {
			text.setFont(new Font(text.getFont().getName(), Font.PLAIN, (int) (12*scale)));
		} else {
			text.setFont(new Font(text.getFont().getName(), Font.PLAIN, (int) (17*scale)));
		}
		text.setAlignmentX(CENTER_ALIGNMENT);
		textP.add(text);
		
		topP.add(type);
		topP.add(Box.createHorizontalGlue());
		topP.add(title);
		topP.add(Box.createHorizontalGlue());
		topP.add(manaCost);
		
		cardP.setSize(CARD_DIM);
		cardP.setBorder(BorderFactory.createLineBorder(Color.black));
		textP.setMaximumSize(CARD_DIM);
		textP.setPreferredSize(CARD_DIM);
		cardP.add(topP);
		cardP.add(Box.createVerticalStrut((int) (10/scale)));
		cardP.add(cardImage);
		cardP.add(Box.createVerticalGlue());
		cardP.add(textP);
		cardP.add(Box.createVerticalGlue());
		Color labelC = null;
		switch(c.getType()) {
		case("Rock"):
			labelC = (Color.decode("#e05f5f"));
			break;
		case("Pop"):
			labelC = (Color.decode("#b6b9f7"));
			break;
		case("Electronic"):
			labelC = (Color.decode("#baf4a8"));
			break;
		case("HipHop"):
			labelC = (Color.decode("#eefb8e"));
			break;
		case("Classical"):
			labelC = (Color.decode("#a8ebf4"));
			break;
		}
		cardP.setBackground(labelC);
		topP.setBackground(labelC);
		
		
		cardP.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				System.out.println("Pressed card "+index);
				sendInput(index);
			}
		});
		
		return cardP;
	}
	
	public int gameOver(ArrayList<Rider> rs) {
		String msg = "";
		if(rs.size()==0) {
			return -1;
		} else if (rs.size()==1) {
			msg+=rs.get(0).getName()+ " is";
		} else {
			for(Rider rdr:rs) {
				msg+=rdr.getName()+ " and ";
			}
			msg = msg.substring(0,msg.length()-4) + "are";
		}
		JOptionPane.showMessageDialog(this, msg + " sick of your music.  Game Over!");
		return 0;
	}
	
	public int gameWin() {
		JOptionPane.showMessageDialog(this,"Congratulations!  You've reached your destination with everyone still happy.  You win!");
		dispose();
		return 0;
	}
	
	private Dimension scaleDim(Dimension d, double s) {
		d.setSize(d.getWidth()*s,d.getHeight()*s);
		return d;
	}
	

}
