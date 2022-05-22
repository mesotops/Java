/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.*;
import java.io.*; 
import javax.sound.sampled.*; 
/**
 *
 * @author 山歌
 */
public class Game extends JFrame implements KeyListener,ActionListener {
	final int SCREEN_WIDTH = 800;
	final int SCREEN_HEIGHT = 800;
	final int RECT_WIDTH = 60;
	final int RECT_HEIGHT = 20;
	final double DELAY_MS = 10;
        
        int score=0;
	int dx = 1;
	int xPos[] = {200,260,320,380};
        int xPos1[] = {200,260,320,380};
	int yPos[] = {0,0,0,0};
        int yPos1[] = {700,700,700,700};
	Timer timer;
         private Image iBuffer;
        private Graphics gBuffer;

	public Game() {
        setTitle("遊戲");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);
        timer = new Timer((int) DELAY_MS, this);
        timer.setInitialDelay(2000);
        timer.start();

        AePlayWave apw=new AePlayWave("song.wav"); 
        apw.start(); 
	}
        
        public class AePlayWave extends Thread { 
        private String filename; 
        public AePlayWave(String wavfile) { 
        filename = wavfile; 
        } 
        public void run() { 
        File soundFile = new File(filename); 
        AudioInputStream audioInputStream = null; 
        try { 
        audioInputStream = AudioSystem.getAudioInputStream(soundFile); 
        } catch (Exception e1) { 
        e1.printStackTrace(); 
        return; 
        } 
        AudioFormat format = audioInputStream.getFormat(); 
        SourceDataLine auline = null; 
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format); 
        try { 
        auline = (SourceDataLine) AudioSystem.getLine(info); 
        auline.open(format); 
        } catch (Exception e) { 
        e.printStackTrace(); 
        return; 
        } 
        auline.start(); 
        int nBytesRead = 0; 
        byte[] abData = new byte[512]; 
        try { 
        while (nBytesRead != -1) { 
        nBytesRead = audioInputStream.read(abData, 0, abData.length); 
        if (nBytesRead >= 0) 
        auline.write(abData, 0, nBytesRead); 
        } 
        } catch (IOException e) { 
        e.printStackTrace(); 
        return; 
        } finally { 
        auline.drain(); 
        auline.close(); 
        } 
        } 
        } 
     public void paint(Graphics g) { 
        if(iBuffer==null)
	{
		iBuffer=createImage(this.getSize().width,this.getSize().height);
		gBuffer=iBuffer.getGraphics();
	}
	gBuffer.setColor(getBackground());
	gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
        gBuffer.setColor(Color.RED);
        gBuffer.fillRect(xPos[0], yPos[0], RECT_WIDTH, RECT_HEIGHT);
        gBuffer.setColor(Color.GREEN);
        gBuffer.fillRect(xPos[1], yPos[1], RECT_WIDTH, RECT_HEIGHT);
        gBuffer.setColor(Color.BLUE);
        gBuffer.fillRect(xPos[2], yPos[2], RECT_WIDTH, RECT_HEIGHT);
        gBuffer.setColor(Color.YELLOW);
        gBuffer.fillRect(xPos[3], yPos[3], RECT_WIDTH, RECT_HEIGHT);
        gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(xPos1[0], yPos1[0], RECT_WIDTH, RECT_HEIGHT);
     	gBuffer.setColor(Color.BLACK);
    	gBuffer.drawString("Q",xPos1[0]+25,yPos1[0]+15);
    	gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(xPos1[1], yPos1[1], RECT_WIDTH, RECT_HEIGHT);
     	gBuffer.setColor(Color.BLACK);
    	gBuffer.drawString("W",xPos1[1]+25,yPos1[1]+15);
        gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(xPos1[2], yPos1[2], RECT_WIDTH, RECT_HEIGHT);
     	gBuffer.setColor(Color.BLACK);
    	gBuffer.drawString("E",xPos1[2]+25,yPos1[2]+15);
        gBuffer.setColor(Color.WHITE);
        gBuffer.fillRect(xPos1[3], yPos1[3], RECT_WIDTH, RECT_HEIGHT);
     	gBuffer.setColor(Color.BLACK);
    	gBuffer.drawString("R",xPos1[3]+25,yPos1[3]+15);
 
	g.drawImage(iBuffer,0,0,this);


    }  
        @Override
    public void update(Graphics g) { 
        paint(g);
    } 

 
   
    

	public static void main(String[] args) {
		new Game().show();
	}
        
        @Override
         public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
                    if( key == KeyEvent.VK_Q &&yPos[0]>660&&yPos[0]<740){
                            yPos[0] = 0;
                            score+=1;
                            
                    }
                    if( key == KeyEvent.VK_W &&yPos[1]>660&&yPos[1]<740){
                            yPos[1] = 0;
                    }
                    if( key == KeyEvent.VK_E &&yPos[2]>660&&yPos[2]<740){
                            yPos[2] = 0;
                    }
                    if( key == KeyEvent.VK_R &&yPos[3]>660&&yPos[3]<740){
                            yPos[3] = 0;
                    }
                   
                    repaint();
 }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
    	yPos[0] += 1;
        yPos[1] += 2;
        yPos[2] += 3;
        yPos[3] += 4;
    	if( yPos[0] >= SCREEN_HEIGHT ) yPos[0] = 0;
    	if( yPos[1] >= SCREEN_HEIGHT ) yPos[1]= 0;
        if( yPos[2] >= SCREEN_HEIGHT ) yPos[2] = 0;
    	if( yPos[3] >= SCREEN_HEIGHT ) yPos[3]= 0;
        this.repaint();
	}
       

        @Override
        public void keyReleased(KeyEvent e) {
	}

        @Override
        public void keyTyped(KeyEvent e) {
	}
        
}
