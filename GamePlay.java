package demogame;

import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class GamePlay extends JPanel  implements KeyListener, ActionListener{
	 	private boolean play = false;
	    private int score = 0;
	    private int totalbricks = 21;
	    private Timer Timer;
	    private int delay = 1;
	    private int playerX = 350;
	    private int ballposX = 120;
	    private int ballposY = 350;
	    private int ballXdir = -1;
	    private int ballYdir = -2;
	    private MapGenerator map;
	    
	    public GamePlay() {
	    	 addKeyListener(this);
	         setFocusable(true);
	         setFocusTraversalKeysEnabled(true);
	         
	         Timer = new Timer(delay, this);
	         Timer.start();
	         
	         map=new MapGenerator(3,7);
	    	
	    }
	        
	    public void paint(Graphics g) {
	         g.setColor(Color.black);
	         g.fillRect(1, 1, 692, 592);

	           

	         g.setColor(Color.yellow);
	         g.fillRect(0, 0, 3, 592);
	         g.fillRect(0, 0, 692, 3);
	         g.fillRect(691, 0, 3, 592);

	         g.setColor(Color.white);
	         g.setFont(new Font("serif", Font.BOLD, 25));
	         g.drawString("" + score, 590, 30);

	         g.setColor(Color.yellow);
	         g.fillRect(playerX, 550, 100, 8);

	            //ball
	         g.setColor(Color.GREEN);
	         g.fillOval(ballposX, ballposY, 20, 20);
	         
	         //bricks
	         map.draw((Graphics2D)g);
	         
	         //gameover
	         
	         if(ballposY>=570){
	        	 play=false;
	        	 ballXdir=0;
	        	 ballYdir=0;
	        	 
	        	 g.setColor(Color.green);
	        	 g.setFont(new Font("serif",Font.BOLD,30));
	        	 g.drawString("GameOver!!,Score :"+score ,200,300);
	        	 
	        	 g.setFont(new Font("serif",Font.BOLD,25));
	        	 g.drawString("Press Enter To Restart!!",230,350);
	         }
	         if(totalbricks<=0){
	        	 play=false;
	        	 ballXdir=0;
	        	 ballYdir=0;
	        	 
	        	 g.setColor(Color.green);
	        	 g.setFont(new Font("serif",Font.BOLD,30));
	        	 g.drawString("YOU WON!!,Score :"+score ,200,300);
	        	 
	        	 g.setFont(new Font("serif",Font.BOLD,25));
	        	 g.drawString("Press Enter To Restart!!",230,350);
	         }
	    }

		
		private void moveRight(){
			play=true;
			playerX+=20;
		}
		
		private void moveLeft(){
			play=true;
			playerX-=20;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	            if (playerX >= 600) {
	                playerX = 600;
	            } else {
	                moveRight();
	            }
	        }
	        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
	            if (playerX < 0) {
	                playerX = 0;
	            } else 
	                moveLeft();
	        } 
	        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	            if (!play) {
	                score = 0;
	                totalbricks=21;
	                ballposX=120;
	                ballposY=350;
	                ballXdir=-1;
	                ballYdir=-2;
	                playerX=320;
	                
	                map=new MapGenerator(3,7);
	            } 
	        } 
	            
	            repaint();
	        

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if (play) {
                if (ballposX<=0){
                	ballXdir=-ballXdir;
                }
                if (ballposX>=670) {
                	ballXdir=-ballXdir;
		            
		        }
		            if (ballposY<=0) {
		            	ballYdir=-ballYdir;
		            }
		            Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
		            Rectangle paddleRect=new Rectangle(playerX,550,100,8);
		            
		            if(ballRect.intersects(paddleRect)){
		            	ballYdir=-ballYdir;
		            }
		            A:
		            for (int i = 0; i < map.map.length; i++) {
		                for (int j = 0; j < map.map[0].length; j++) {
		                    if (map.map[i][j] > 0) {
		                        int brickX = j * map.brickWidth + 80;
		                        int brickY = i * map.brickHeight + 50;
		                        int bricksWidth = map.brickWidth;
		                        int bricksHeight = map.brickHeight;

		                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);
		                        Rectangle ballrect = new Rectangle(ballposX, ballposY, 20, 20);
		                        Rectangle brickrect = rect;

		                        if (ballrect.intersects(brickrect)) {
		                            map.setBricksValue(0, i, j);
		                            totalbricks--;
		                            score += 5;
		                            if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + bricksWidth) {
		                                ballXdir = -ballXdir;
		                            } else {
		                                ballYdir = -ballYdir;
		                            }
		                            break A;
		                        }
		                    }


		                }
		            }
		            
		            
		            
		            	
		            ballposX+=ballXdir;
		            ballposY+=ballYdir;
		            
		        }
		        
		        repaint();
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		

}
	          

	   
