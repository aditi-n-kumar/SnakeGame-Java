package SnakeGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Pong.Images;
import Pong.ImagesSecond;
import Pong.ImagesThird;

import java.util.*;

import utilities.GDV5;
import utilities.SoundDriverHo;

public class Game extends GDV5{
	
	public static Tile[] board;

	public int appleX = 100;
	public int appleY = 200;
	
	private Snake s1 = new Snake(5);
	private int count = 0;
	public Apple apple = new Apple(appleX, appleY);
	
	private SoundDriverHo sound1;
	private String[] filenames = new String[1];
	
	Images image = new Images();
	ImagesSecond image2 = new ImagesSecond();
	ImagesThird image3 = new ImagesThird();
	
	public Game() {
		filenames[0] = "snakeEatsAppleNoise.wav";
		sound1 = new SoundDriverHo(filenames, this);
	}
	
	
	String gameState = "SPLASH";
	//String gameState = "ENDED";
	int playerPoints = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game();
		g.makeBoard();
		g.start();
	}
	
	public void keysPushed() {
		if (KeysPressed[KeyEvent.VK_ENTER]) {
			gameState = "RUNNING"; }
		
		if (KeysPressed[KeyEvent.VK_ESCAPE]) {
			gameState = "ENDED"; }
		
		if (KeysPressed[KeyEvent.VK_SPACE]) {
			gameState = "SPLASH";
			s1 = new Snake(5);
			playerPoints = 0;
			appleX = 100;
			appleY = 200;
			apple = new Apple(appleX, appleY);  }
	}

	
	public void checkWallCollision() {
		if ((s1.body.get(0).getX() < 0) || (s1.body.get(0).getX() > GDV5.getMaxWindowX()) || (s1.body.get(0).getY() < 0) || (s1.body.get(0).getY() > GDV5.getMaxWindowY())) {
			gameState = "OVER"; }
	}
	
	public void checkSnakeOverlap() {
		int headX = (int) s1.body.get(0).getX();
		int headY = (int) s1.body.get(0).getY();
		for (int i = 1; i < s1.body.size(); i ++) {
			if ((headX == s1.body.get(i).getX()) && (headY == s1.body.get(i).getY())) {
				gameState = "OVER"; }
		}
	}
	
	public void moveApple() {
		//false is the apple is not on snake
		
		if (s1.snakeIntersectApple(apple)) {
			playerPoints++;
			sound1.play(0);
			s1.addBody();
			appleX = (int)(Math.random() * 39) * 20;
			appleY = (int)(Math.random() * 29) * 20;
			
		
			while (true) {
				appleX = (int)(Math.random() * 39) * 20;
				appleY = (int)(Math.random() * 29) * 20;
				boolean appleOnSnake = false;
				
				for (int i = 0; i < s1.body.size(); i++) {
					if (s1.body.get(i).getX() == appleX && s1.body.get(i).getY() == appleY) {
						appleOnSnake = true;
						break; }
				}
				if (!appleOnSnake) {
					break; }
			}
			apple.move(appleX, appleY); } 
	}
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		keysPushed();
		count++;
		s1.setHeadDirection();
		checkWallCollision();
		checkSnakeOverlap();
		s1.checkSnakeCollideItself();
		moveApple();
		if (count % 25 == 0) {
			s1.updateBodyDirection(board);
			s1.move(); }
		if (gameState == "OVER") {
			s1 = new Snake(5);
			playerPoints = 0;
			appleX = 100;
			appleY = 200;
			apple = new Apple(appleX, appleY);
		}
	/*
			if (s1.snakeIntersectApple(apple)) {
				s1.addBody();
				appleX = (int)(Math.random() * 39) * 20;
				appleY = (int)(Math.random() * 29) * 20;
				sound1.play(0);
				apple.move(appleX, appleY); } } */
	}
	
	@Override
	public void draw(Graphics2D win) {
		// TODO Auto-generated method stub
		
		/*
		apple.drawApple(win);
		drawBoard(win);
		s1.draw(win);
		apple.drawApple(win);
		*/
		
		if (gameState == "SPLASH") {
			win.setColor(new Color(99, 50, 110));
			win.setFont(new Font("Arial", Font.ITALIC, 30));
			win.drawString("Welcome to Snake!", 260, 60); 
			win.drawString("Press ENTER to start", 250, 90);
			win.drawString("If you want to end the game, press ESC", 150, 120); 
			win.drawString("Click LEFT, RIGHT, UP, and DOWN", 150, 150);
			win.drawString("to move the snake.", 260, 180);

			win.drawImage(image.getSpongebobRainbow(), 150, 250, 500, 300, this); }
		
		else if (gameState == "RUNNING") {
			win.setFont(new Font("Arial", Font.BOLD, 15));
			win.setColor(Color.BLACK);
			win.drawString("Points: " + playerPoints, 698, 55);
			apple.drawApple(win);
			drawBoard(win);
			s1.draw(win);
			apple.drawApple(win); }
		
		else if (gameState == "WIN") {
			win.setColor(Color.PINK);
			win.setFont(new Font("Arial", Font.ITALIC, 25));
			win.drawString("YOU WON!", 320, 150);
			win.drawString("Press SPACE to RESTART", 245, 176);
			win.drawImage(image2.getSpongebobSquarepantsCongratulations(), 150, 250, 500, 300, this); }
		
		else if (gameState == "OVER") {
			win.setColor(new Color(245, 171, 0));
			win.setFont(new Font("Arial", Font.ITALIC, 25));
			win.drawString("GAME OVER", 320, 250);
			win.drawString("Press SPACE to RESTART", 260, 276);  }

		else if (gameState == "ENDED") {
			win.setColor(new Color(4, 42, 43));
			win.setFont(new Font("Arial", Font.ITALIC, 25));
			win.drawString("Awwww...you ended the game?", 240, 150);
			win.drawString("Press SPACE to RESTART", 260, 185);
			win.drawImage(image3.getSpongebobConfused(), 185, 200, 450, 300, this); }
		
		
	} 
	
	void drawBoard(Graphics2D pb) {
		for (Tile t: board) {
			t.draw(pb);
		}	
	}
	
	void makeBoard() {
		int width = GDV5.getMaxWindowX();
		int height = GDV5.getMaxWindowY();
		int x = 0; 
		int y = 0;
		board = new Tile[1200];
		for (int i = 0; i < board.length; i++) {
			board[i] = new Tile(x, y, 0);
			x += board[i].getDimension();
			if (i != 0 && i % 40 == 0) {
				x = 0;
				y += board[i].getDimension();
			}
		}
	}
	
	

}// end of class

