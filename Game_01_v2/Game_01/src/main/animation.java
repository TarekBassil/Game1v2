package main;

import static java.awt.Font.BOLD;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class animation extends JPanel {
	
	public int x, y;
	public float vely;
	public String s;
	private Timer t;
	public Boolean b;
	public int r;
	public Color col = Color.orange;
	public int lvl;
	public int ans;
	public float a;
	
	public animation(int Lvl, float Acc) {
		y = 0;
		x = (int)(Math.round(Math.random()*200));
		vely = 1.2f;
		b = true;
		lvl = Lvl;
		a = Acc;
		String temp = equation(lvl);
		s = temp.split("=")[0];
		r = Integer.parseInt(temp.split("=")[1]);
		ans = 0;
	}
	
	public void run() {
		t = new Timer(50, new MoveListener());
		t.start();
	}
	
	public void setans(int a) {
		this.ans = a;
		System.out.println("You answered: " + ans);
		if (ans != r) {
			this.incorrect();
		}
		else {
			vely = 0;
			this.correct();
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Ellipse2D.Double c = new Ellipse2D.Double(x, y, 90, 90);
		g2.setColor(col);
		g2.fill(c);
		
	}
	
	public void paint(Graphics gp) {
			
			super.paint(gp);
			
			Graphics2D g2d = (Graphics2D) gp;
			g2d.setColor(Color.white);
			if (lvl == 1) {
				g2d.setFont(new Font("BOLD", BOLD, 35));
				g2d.drawString(s, x+10, y+50);
			}
			else if (lvl ==2) {
				g2d.setFont(new Font("BOLD", BOLD, 25));
				g2d.drawString(s, x+12, y+53);
			}
			else {
				g2d.setFont(new Font("BOLD", BOLD, 15));
				g2d.drawString(s, x+8, y+50);
			}
			
			repaint();
		}
	
	private class MoveListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (y >= 0 && y < 600 && vely > 0) {
				y = Math.round(y + vely);
				vely = vely*a;
			}
			else {
				vely = 0;
				t.stop();
				b = false;
			}
			repaint();
		
		}
	}
	
	private String equation(int lvl) {
		
		if (lvl == 1) {
			int a = (int)Math.round(Math.random()*9);
			int b = (int)Math.round(Math.random()*9);
			if (Math.random()>= 0.5) {
				r = a + b;
				String s = a + "+" + b + "=" + r;
				System.out.println(s);
				return s;
			}
			else {
				r = a-b;
				String s = a + "-" + b + "=" + r;
				System.out.println(s);
				return s;
			}
		}
		else if (lvl == 2) {
			int a = (int)Math.round(Math.random()*99);
			int b = (int)Math.round(Math.random()*99);
			if (Math.random()>= 0.5) {
				r = a + b;
				String s = a + "+" + b + "=" + r;
				System.out.println(s);
				return s;
			}
			else {
				r = a-b;
				String s = a + "-" + b + "=" + r;
				System.out.println(s);
				return s;
			}
		}
		else {
			int a = (int)Math.round(Math.random()*99);
			int b = (int)Math.round(Math.random()*99);
			int c = (int)Math.round(Math.random()*99);
			int r1 = 0;
			String s1;
			String s;
			if (Math.random() <= 0.25) {
				r1 = a + b;
				s1 = "(" + a + "+" + b + ")";
			}
			else if (Math.random() <= 0.5) {
				r1 = a - b;
				s1 = "(" + a + "-" + b + ")";
			}
			else if (Math.random() <= 0.75) {
				r1 = a * b;
				s1 = "(" + a + "*" + b + ")";
			}
			else {
				a = a - (a % b);
				r1 = a / b;
				s1 = "(" + a + "/" + b + ")";
			}
			if (Math.random()>= 0.5) {
				if (Math.random() <= 0.25) {
					r = r1 + c;
					s = s1 + "+" + c + "=" + r;
				}
				else if (Math.random() <= 0.5) {
					r = r1 - c;
					s = s1 + "-" + c + "=" + r;
				}
				else if (Math.random() <= 0.75) {
					r = r1 * c;
					s = s1 + "*" + c + "=" + r;
				}
				else {
					while (r1 % c != 0) {
						c = c - 1;
					}
					r = r1  / c;
					s = s1 + "/" + c  + "=" + r;
				}
			}
			else {
				if (Math.random() <= 0.25) {
					r = c + r1;
					s = c + "+" + s1 + "=" + r;
				}
				else if (Math.random() <= 0.5) {
					r = c - r1;
					s = c + "-" + s1 + "=" + r;
				}
				else if (Math.random() <= 0.75) {
					r = c * r1;
					s = c + "*" + s1 + "=" + r;
				}
				else {
					r = (c - ( c % r1)) / r1;
					s = c + "/" + s1  + "=" + r;
				}
			}
			return s;
		}
		
	}
	
	public void correct() {
		System.out.println("Correct answer!");
		vely = 0;
		col = Color.green;
		
//		repaint();
	}
	
	public void incorrect() {
		System.out.println("incorrect answer!");
		col = Color.red;
		
//		repaint();
	}
}