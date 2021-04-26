import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class TitleScreen {
	private double scale;
	//private JFrame frame = new JFrame("Road Trip DJ");
	public JFrame frame;
	
	public TitleScreen(JFrame f) {
		frame = f;
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		scale = 1/Main.getScalingFactor();
		frame.setSize((int)(1920/scale),(int)(1080/scale));
		frame.setResizable(false);
		
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		//setAlignmentX(CENTER_ALIGNMENT);


		JLabel title = new JLabel("Road Trip DJ");
		title.setFont(new Font(title.getFont().getName(), Font.BOLD, (int) (80*scale)));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		BufferedImage carImgFile = null;

		
		try {
			carImgFile = ImageIO.read(getClass().getResource("img/titlecar.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error: can't find car image");
		}
		JLabel carImg = new JLabel(new ImageIcon(carImgFile.getScaledInstance((int) (600*scale),(int) (400*scale), Image.SCALE_FAST)));
		carImg.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JButton easy = new JButton("Easy");
		easy.setFont(new Font(easy.getFont().getName(), Font.BOLD, (int) (60*scale)));
		easy.setAlignmentX(Component.CENTER_ALIGNMENT);
		easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	            Main.inp=1;
				return;
			}  
		});
		JButton medium = new JButton("Medium");
		medium.setFont(new Font(medium.getFont().getName(), Font.BOLD, (int) (60*scale)));
		medium.setAlignmentX(Component.CENTER_ALIGNMENT);
		medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	            Main.inp=2; 
	            return;
			}  
		});
		JButton hard = new JButton("Hard");
		hard.setFont(new Font(hard.getFont().getName(), Font.BOLD, (int) (60*scale)));
		hard.setAlignmentX(Component.CENTER_ALIGNMENT);
		hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
	            Main.inp=3;
	            return;
			}  
		});
		

		frame.add(Box.createVerticalStrut((int) (30*scale)));
		frame.add(title);
		frame.add(Box.createVerticalStrut((int) (30*scale)));
		frame.add(carImg);
		frame.add(Box.createVerticalStrut((int) (80*scale)));
		frame.add(easy);
		frame.add(Box.createVerticalStrut((int) (20*scale)));
		frame.add(medium);
		frame.add(Box.createVerticalStrut((int) (20*scale)));
		frame.add(hard);
		frame.add(Box.createVerticalGlue());
		
		
		frame.setVisible(true);
		//if(Main.clip!=null)
			//Main.clip.start();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		if(width==1920) {
			frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
		}
		
	}
	
	

}
