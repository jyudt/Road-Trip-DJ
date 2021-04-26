import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class gameGUI{
	public JFrame f;
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
	
	private final Color rockClr = (Color.decode("#e05f5f"));
	private final Color popClr = (Color.decode("#b6b9f7"));
	private final Color ElectronicClr = (Color.decode("#baf4a8"));
	private final Color hipHopClr = (Color.decode("#eefb8e"));
	private final Color ClassicalClr = (Color.decode("#a8ebf4"));
	
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
	private JLabel lastPlayed;
	
	private Ride ride;
	
	private double scale;
	
	public gameGUI(Ride r) {
		f = Main.getFrame();
		f.setVisible(false);
		f.getContentPane().removeAll();
		f.getContentPane().revalidate();
		ride = r;
		riders=r.getRiders();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		scale = 1/Main.getScalingFactor();
		f.setSize((int)(1920/scale),(int)(1080/scale));
		f.setResizable(false);
		
		f.setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		
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
		hand.setAlignmentX(Component.CENTER_ALIGNMENT);
		hand.setMaximumSize(HAND_SIZE_DIM);
		hand.setPreferredSize(HAND_SIZE_DIM);
		
		timerP = new JPanel();
		timerP.setLayout(new BoxLayout(timerP, BoxLayout.X_AXIS));
		timerP.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		deckImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		deckImage.setToolTipText("Deck Size");
		JLabel discardImage = new JLabel(new ImageIcon(discardImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		discardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		discardImage.setToolTipText("Discard Size");
		JLabel exhaustImage = new JLabel(new ImageIcon(exhaustImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		exhaustImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		exhaustImage.setToolTipText("Exhaust Size");
		JLabel timeImage = new JLabel(new ImageIcon(timeImageFile.getScaledInstance(TIME_IMG_SIZE_DIM.width,TIME_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		timeImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		dupeImg = new JLabel(new ImageIcon(dupeImageFile.getScaledInstance(DECK_IMG_SIZE_DIM.width,DECK_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		dupeImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		dupeImg.setToolTipText("Copies Remaining");
		refundImg = new JLabel(new ImageIcon(refundImageFile.getScaledInstance(TIME_IMG_SIZE_DIM.width,TIME_IMG_SIZE_DIM.height, Image.SCALE_FAST)));
		refundImg.setAlignmentX(Component.CENTER_ALIGNMENT);
		refundImg.setToolTipText("Refunds Remaining");

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
		
		lastPlayed = new JLabel ("Last Played: None");
		lastPlayed.setFont(new Font(lastPlayed.getFont().getName(), Font.BOLD, (int) (30*scale)));
		lastPlayed.setBackground(Color.white);
		lastPlayed.setOpaque(true);
		lastPlayed.setBorder(new EmptyBorder(10,10,10,10));

		
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
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
		riderP.setAlignmentX(Component.CENTER_ALIGNMENT);
		riderP.setMaximumSize(RIDERS_SIZE_DIM);
		riderP.setPreferredSize(RIDERS_SIZE_DIM);
		
		manaP = new JPanel();
		manaP.setLayout(new BoxLayout(manaP, BoxLayout.X_AXIS));
		manaP.setAlignmentX(Component.CENTER_ALIGNMENT);
		manaP.setMaximumSize(MANA_SIZE_DIM);
		manaP.setPreferredSize(MANA_SIZE_DIM);
		
		manaP.add(timeImage);
		manaP.add(Box.createHorizontalStrut((int) (10/scale)));
		manaP.add(mana);
		manaP.add(Box.createHorizontalGlue());
		manaP.add(lastPlayed);
		manaP.add(Box.createHorizontalGlue());
		manaP.add(endTurn);
		
		f.add(Box.createVerticalStrut((int) (10/scale)));
		f.add(timerP);
		f.add(Box.createVerticalStrut((int) (5/scale)));
		f.add(riderP);
		f.add(Box.createVerticalGlue());
		f.add(manaP);
		f.add(Box.createVerticalGlue());
		f.add(hand);
		
		
		
		drawHand();
		drawRiders();

		
		hand.setBackground(Color.decode("#f7f6bc"));
		riderP.setBackground(Color.decode("#c4c4c4"));
		
		f.repaint();
		f.setVisible(true);
		f.repaint();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		if(width==1920) {
			f.setExtendedState(f.getExtendedState() | Frame.MAXIMIZED_BOTH);
		}

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
		refundL.setText(Integer.toString(ref));
		if(ref!=0) {
			enableRefund();
		}
		int dup = ride.playTwice;
		dupeL.setText(Integer.toString(dup));
		if(dup!=0) {
			enableDupe();
		}
		if(ride.refundRegen>0)
			enableRefund();
		if(ride.playTwiceRegen>0)
			enableDupe();
		refundL.updateUI();
		dupeL.updateUI();
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
	
	public void setLastPlayed(Card c) {
		String type;
		Color clr = null;
		if(c==null) {
			type="None";
		} else {
			type = c.getType();
			clr = getColor(c);
		}
		
		lastPlayed.setText("Last Played: "+type);
		lastPlayed.setBackground(clr);
		lastPlayed.updateUI();
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
		rider.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel riderImage = new JLabel(new ImageIcon(myImage.getScaledInstance(RIDER_IMG_SIZE_DIM.width, RIDER_IMG_SIZE_DIM.height, Image.SCALE_DEFAULT)));
		riderImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel name = new JLabel(r.getName());
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
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

		
		JLabel likes = new JLabel(" "+r.getLikesString().substring(0,1)+" ");
		likes.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (30*scale)));
		likes.setBackground(getColor(r.getLikes()));
		likes.setOpaque(true);
		likes.setToolTipText("Likes: "+r.getLikesString());
		likesP.add(Box.createHorizontalGlue());
		likesP.add(likes);
		likesP.add(Box.createHorizontalGlue());
		
		for(String s:r.getTraits()) {
			JLabel tr = new JLabel(s);
			tr.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (25*scale)));
			tr.setOpaque(true);
			tr.setToolTipText(Rider.getTraitDesc(s));
			likesP.add(Box.createHorizontalStrut((int) (10/scale)));
			likesP.add(tr);
		}
		likesP.add(Box.createHorizontalGlue());

		
		if(r.getDislikes()!=null) {
			JLabel dislikes = new JLabel(r.getDislikesString().substring(0,1));
			dislikes.setFont(new Font(likes.getFont().getName(), Font.PLAIN, (int) (30*scale)));
			dislikes.setForeground(Color.red);
			dislikes.setToolTipText("Dislikes: "+r.getDislikesString());
			likesP.add(Box.createHorizontalStrut((int) (10/scale)));
			likesP.add(dislikes);
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
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, (int) (18*scale)));
		JLabel cardImage = new JLabel(new ImageIcon(myPicture.getScaledInstance(CARD_IMG_SIZE_DIM.width,CARD_IMG_SIZE_DIM.height, Image.SCALE_DEFAULT)));
		cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		textP.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		text.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		Color labelC = getColor(c);
		cardP.setBackground(labelC);
		topP.setBackground(labelC);
		
		
		cardP.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				System.out.println("Pressed card "+index);
				sendInput(index);
			}
		});
		
		cardP.setToolTipText(c.getTooltip());
		text.setToolTipText(c.getTooltip());

		
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
		JOptionPane.showMessageDialog(f, msg + " sick of your music.  Game Over!");
		return 0;
	}
	
	public int gameWin() {
		JOptionPane.showMessageDialog(f,"Congratulations!  You've reached your destination with everyone still happy.  You win!");
		f.dispose();
		return 0;
	}
	
	private Dimension scaleDim(Dimension d, double s) {
		d.setSize(d.getWidth()*s,d.getHeight()*s);
		return d;
	}
	
	private Color getColor(Card c) {
		Color labelC = null;
		switch(c.getType()) {
		case("Rock"):
			labelC = rockClr;
			break;
		case("Pop"):
			labelC = popClr;
			break;
		case("Electronic"):
			labelC = ElectronicClr;
			break;
		case("HipHop"):
			labelC = hipHopClr;
			break;
		case("Classical"):
			labelC = ClassicalClr;
			break;
		}
		return labelC;
	}
	

}
