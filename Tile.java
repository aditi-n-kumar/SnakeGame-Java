package SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import utilities.GDV5;

public class Tile extends Rectangle{

	private int direction;
	String directionString;
	private Color col;
	private int dimension = 20;
	private Color color = new Color(99, 50, 110);
	
	
	public Tile(int x, int y, int direction) {
		super(x, y, 0, 0);
		this.setSize(dimension, dimension);
		this.direction = direction;
		col = Color.white;
	}
	
	public void draw(Graphics2D pb) {
		pb.setColor(col);
		pb.draw(this);
	}
	
	public void fill(Graphics2D pb) {
		pb.setColor(color);
		pb.fill(this);
	}
	
	public void move() {
		if (direction == 1) this.x -= this.dimension; //left
		if (direction == 2) this.y -= this.dimension; //up
		if (direction == 3) this.x += this.dimension; //right
		if (direction == 4) this.y += this.dimension; //down
		
	}

	
	/*
	public void updateDirection(Tile[] board) {
		for (int i = 0; i < board.length; i++) {
			if (this.getX() == board[i].getX() && this.getY() == board[i].getY()); {
				board[i].setDirection(this.getDirection());
			}
		}
	}
	*/
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection (int direction){
		this.direction = direction;
	}
	 
	public Color getCol() {
		return col;
	}
	
	public void setColor(Color col) {
		this.col = col;
	}
	
	public Color getCol2() {
		return color;
	}
	
	public void setColor2(Color col) {
		this.color = col;
	}
	

	public int getDimension() {
		return dimension;
	}
	
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

}//end of class
