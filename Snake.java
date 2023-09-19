package SnakeGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import utilities.GDV5;

public class Snake extends Rectangle {
	ArrayList<Tile> body;
	double speed = 2.5;
	int direction = 1;
	int boxSize;
	
	public Snake(int size) {
		body = new ArrayList<Tile>();
		body.add(new Tile(GDV5.getMaxWindowX()/2, GDV5.getMaxWindowX()/2, 0));
		body.get(0).setDirection(1);
		addBody(size - 1, body.get(0).getDirection());
		
		
	}
	
	
	
	public boolean snakeIntersectApple(Apple apple) {
		if (body.get(0).intersects(apple)) {
			return true;
		}
		return false;
	}
	

	
	
	public void setHeadDirection() {
		//update reversal
		// if 1, then cannot go to 2
		if (GDV5.KeysPressed[KeyEvent.VK_LEFT] && (direction == 2 || direction == 4)) direction = 1; //left
		else if (GDV5.KeysPressed[KeyEvent.VK_UP] && (direction == 1 || direction == 3)) direction = 2; //up
		else if (GDV5.KeysPressed[KeyEvent.VK_RIGHT] && (direction == 2 || direction == 4)) direction = 3; //right
		else if (GDV5.KeysPressed[KeyEvent.VK_DOWN] && (direction == 1 || direction == 3)) direction = 4; //down
	}
	
	public void addBody() {
		int x = (int)body.get(body.size()-1).getX();
		int y = (int)body.get(body.size()-1).getY();
		int boxSize = body.get(body.size()-1).getDimension();
		int direct = body.get(body.size()-1).getDirection();
		if (direct == 1) x += boxSize; //left
		else if (direct == 2) y += boxSize; // up
		else if (direct == 3) x -= boxSize; //right
		else if (direct == 4) y -= boxSize; //down
		body.add(new Tile(x, y, direct));	 
	}
	
	
	
	public String checkSnakeCollideItself() {
		String gameState;
		int headX = (int)body.get(0).getX();
		int headY = (int)body.get(0).getY();
		for (int i = 1; i < body.size(); i ++) {
			if ((headX == body.get(i).getX()) && (headY == body.get(i).getY())) {
				return gameState = "OVER";
			}
		}
		return gameState = "RUNNING"; }
	
	
	public void addBody(int size, int direction) {
		int x = (int)body.get(0).getX();
		int y = (int)body.get(0).getY();
		for (int i = 0; i < size; i++) {
			body.add(new Tile(x + body.get(0).getDimension(), y, direction));
			x += body.get(0).getDimension(); } 
	}
	
	public void updateBodyDirection(Tile[] board) {	
		for (int i = body.size()-1; i > 0; i--) {
			body.get(i).setDirection(body.get(i-1).getDirection());
		}
		body.get(0).setDirection(direction);
	}
	
	public void draw(Graphics2D pb) {
		for (Tile t: body) {
			pb.setColor(Color.blue);
			t.fill(pb);
		}
	}
	
	public void move() {
		for (Tile t: body) {
			t.move();
		}
	}
		
}//end of class