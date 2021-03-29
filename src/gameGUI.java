import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
public class gameGUI extends JFrame {
	JFrame f;
	ArrayList<BufferedImage> cardImages = new ArrayList<BufferedImage>();
	ArrayList<String> cardImageNames = new ArrayList<String>();
	private final Dimension CARD_DIM =new Dimension(180,300); 
	private final Dimension CARD_TEXT_DIM =new Dimension(165,130); 
	
	private JPanel hand;
	private ArrayList<Card> handList = new ArrayList<Card>();
	
	public gameGUI() {
		//testing
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));
		handList.add(new Rock("test",1));

		
		//end testing
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1920,1080); 
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
				
		hand = new JPanel();
		hand.setLayout(new BoxLayout(hand, BoxLayout.X_AXIS));
		hand.setAlignmentX(CENTER_ALIGNMENT);
		Dimension handDim =new Dimension(1850, 320); 
		hand.setMaximumSize(handDim);
		hand.setPreferredSize(handDim);
		
		add(Box.createVerticalGlue());
		add(hand);
		drawHand();
		
		
		setVisible(true);  
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
			try {
				System.out.println("\"./img/"+c.getName()+".png\"");
				myPicture = ImageIO.read(new File("./img/"+c.getName()+".png"));
			} catch (IOException e) {
				System.out.println("error finding image");
				e.printStackTrace();
			}
			cardImages.add(myPicture);
			cardImageNames.add(c.getName());
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
