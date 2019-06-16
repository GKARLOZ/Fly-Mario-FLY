import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
//import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePlay extends JPanel implements KeyListener, ActionListener {
	
	

	//private ImageIcon picOne;

	private int [] snakeXlength = new int [100];
	private int [] snakeYlength = new int [100];

	private boolean left  = false;
	private boolean right = false;
	private boolean up    = false;
	private boolean down  = false;

	private int lengthOfSnake = 3;
	private int moves         = 0;
	
	
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon leftmouth;
	
	private ImageIcon backGround;
	private ImageIcon backGroundTwo;
	
	private Timer timer; // speed of snake
	private int delay = 100;
	
	private ImageIcon snakeimage;
	
	private int [] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,
			325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,
			700,725,750,775,800,825,850 };
	
	private int [] enemyYpos = { 75,100,125,150,175,200,225,250,275,300,325,350,
			375,400,425,450,475,500,525,550,575,600,625};
	
	private ImageIcon enemyOne;
	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int scores = 0;
	
 
	public SnakePlay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay, this);
		timer.start();
		 	
	}
	
	public void paint (Graphics g ) {
		
		if(moves == 0) {
			
			snakeXlength[2] = 50;
			snakeXlength[1] = 50;
			snakeXlength[0] = 50;
			
			snakeYlength[2]= 110;
		    snakeYlength[1]= 60;
     		snakeYlength[0]= 10;
		}
				
		//draw border gor gameplay
		g.setColor(Color.WHITE);
		g.drawRect(24,74,851,577);
		
		//background
		backGround = new ImageIcon("bk2.jpg");
		backGround.paintIcon(this,g,0,0);
		backGroundTwo = new ImageIcon("pic1a.png");
		backGroundTwo.paintIcon(this,g,250,150);
		
		//mario when not moving 
		if(moves == 0) {
			
			g.setColor(Color.white);
			g.setFont(new Font("arial", Font.BOLD, 70));
			g.drawString("Press Enter to Start ", 100, 340);
			
		   rightmouth = new ImageIcon("MarioUp.png");
           rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);
		}
		// mario when moving has different image
		for(int a = 0; a < lengthOfSnake;a++) {
		
		if(a == 0 && right) {
			rightmouth = new ImageIcon("MarioRight.png");
			rightmouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
			
		}if(a == 0 && left) {
			leftmouth = new ImageIcon("MarioLeft.png");
			leftmouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
			
		}if(a == 0 && down) {
			downmouth = new ImageIcon("MarioDown.png");
			downmouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
			
		}if(a == 0 && up) {
			upmouth = new ImageIcon("MarioUp.png");
			upmouth.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
			
		}
		if(a!=0) {
			snakeimage = new ImageIcon("shell.png");
			snakeimage.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
			
		}
		
		}
			
		enemyOne =  new ImageIcon("Bowser.png");
		enemyOne.paintIcon(this,g, enemyXpos[xpos],enemyYpos[ypos]);
		
		// this if argument is the soul of the game. this is how the snake gets bigger
		if((enemyXpos[xpos] == snakeXlength[0] && enemyYpos[ypos] == snakeYlength[0]))
		{
			scores+=5;
			lengthOfSnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
			
		for(int b = 1; b < lengthOfSnake; b++)
		{
			if (snakeXlength[b] == snakeXlength[0] && snakeYlength[b] == snakeYlength[0]) {
				
				right = false;
				left = false;
				up = false; 
				down = false;
				
				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over ", 300, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press the space bar to restart ", 300, 340);
						
		}
		}
		
        int one =1;
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.PLAIN,20));
		g.drawString("Score: "+ scores, 780, 30);
		// draw length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.PLAIN,20));
		g.drawString("Turtle Shells: "+ (lengthOfSnake - one), 750, 50);
		
		g.dispose();
        repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(moves == 0) {
			
			 
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			moves++;
			up = true;
			down = false;
			repaint();
			
		}
		
		}
		else {
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			moves = 0;
			scores = 0;
			lengthOfSnake = 3;
			repaint();
			
		}
		
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			moves++;
			right= true;
			
			
			if(!left) {
				right = true;
				
			}else {
				
			right = false;
			left = true;
			
			}
			
			up=false;
			down= false;
			
		}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				moves++;
				left= true;
				
				
				if(!right) {
					left= true;
					
				}else {
					
				left = false;
				right = true;
				
				}
				
				up=false;
				down= false;
			}
				if(e.getKeyCode()== KeyEvent.VK_UP)
				{
					moves++;
					right= true;
					
					
					if(!down) {
						up = true;
						
					}else {
						
					up= false;
					down = true;
					
					}
					
					left=false;
					right= false;
				
		}
				if(e.getKeyCode()== KeyEvent.VK_DOWN)
				{
					moves++;
					down= true;
					
					
					if(!up) {
						down = true;
						
					}else {
						
					up = false;
					down = true;
					
					}
					
				    left=false;
					right= false;	
					
	}
		}// end of else
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(right) {
			for(int r = lengthOfSnake-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			
				
			}for(int r = lengthOfSnake; r>=0; r--) {
			
				
				if(r==0) {
					snakeXlength[r] = snakeXlength[r]+25;
				}
				else {
					snakeXlength[r]= snakeXlength[r-1];
					
				}
				if(snakeXlength[r] > 850) {
					snakeXlength[r] = 25;
				}
			
			}
			
			repaint();
			
		}if(left) {
			
			
			for(int r = lengthOfSnake-1; r>=0; r--) {
				snakeYlength[r+1] = snakeYlength[r];
			
				
			}for(int r = lengthOfSnake; r>=0; r--) {
			
				
				if(r==0) {
					snakeXlength[r] = snakeXlength[r]-25;
				}
				else {
					snakeXlength[r]= snakeXlength[r-1];
					
				}
				if(snakeXlength[r] < 25) {
					snakeXlength[r] = 850;
				}
			
			}
			
			repaint();
			
		}if(down) {
			
			for(int r = lengthOfSnake-1; r>=0; r--) {
				snakeXlength[r+1] = snakeXlength[r];
			
				
			}for(int r = lengthOfSnake; r>=0; r--) {
			
				
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]+25;
				}
				else {
					snakeYlength[r]= snakeYlength[r-1];
			
				}
				if(snakeYlength[r] > 625) {
					snakeYlength[r] = 10;
				}
			
			}
			
			repaint();
			
			
			
			
		}if(up) {
			
			
			for(int r = lengthOfSnake-1; r>=0; r--) {
				snakeXlength[r+1] = snakeXlength[r];
			
				
			}for(int r = lengthOfSnake; r>=0; r--) {
			
				
				if(r==0) {
					snakeYlength[r] = snakeYlength[r]-25;
				}
				else {
					snakeYlength[r]= snakeYlength[r-1];
			
				}
				if(snakeYlength[r] < 10) {
					snakeYlength[r] = 625;
				}
			
			}
			
			repaint();
			
			
			
			
			
		}
	}//end of actionPerformed
	
	
}//end of class
