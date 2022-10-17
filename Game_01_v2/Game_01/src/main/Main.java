package main;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


@SuppressWarnings("serial")
public class Main extends javax.swing.JFrame {
	
	public static long t;
	public static int ans;
	public static int res;
	public static int lvl = 1;
	public static float speed = 1.01f;
	public static int score = 0;
	
	public static void main(String[] args) throws IOException {
		
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setBackground(new Color(176, 224, 230));
		frame.setLayout(null);
		
		try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Background4.jpg")))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 800);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel1.setBounds(40, 600, 400, 91);
		frame.add(panel1);

		JTextField ansField = new JTextField();
		ansField.setBorder(new LineBorder(Color.BLUE, 3, true));
		ansField.setBounds(157, 700, 96, 36);
		panel1.add(ansField);
		frame.setVisible(true);
		
		for (int i = 0; i < 5; i++) {
			animation A1 = new animation(lvl,speed);
			A1.setBounds(50, 50 , 400, 500);
//			A1.setBackground(Color.cyan);
			A1.setOpaque(false);
			
			ansField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						ans = Integer.parseInt(ansField.getText());
						ansField.setText(null);
					}
					catch (Exception ex) {
						System.out.println("?");
					}
					A1.setans(ans);
				}
			});
			ansField.setColumns(10);
			
			A1.run();
			frame.add(A1);
			frame.setVisible(true);
			
			while (A1.b) {
				t++;
//				System.out.print(".");
			}
			
			frame.remove(A1);
			score = score + Math.round((604 - A1.y)/6.04f);
			ansField.setText(null);
			
		}
		
		System.out.println("Done");
		frame.setVisible(false);
		JLabel Congrats = new JLabel("Congratulation!");
		Congrats.setFont(new Font("Jokerman", Font.PLAIN, 20));
		Congrats.setBounds(150, 100, 175, 64);
		JLabel Score = new JLabel("Score: " + String.valueOf(score));
		Score.setBounds(200, 130, 175, 64);
		frame.add(Congrats);
		frame.add(Score);
		frame.setVisible(true);

	}
		
}