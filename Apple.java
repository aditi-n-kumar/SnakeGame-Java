package SnakeGame;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


import utilities.GDV5;

public class Apple extends Rectangle{
	
	public boolean isVis = true;
	
	public Apple(int x, int y ){
		super(x, y, 20, 20);
	}
	
	
	 public void drawApple(Graphics2D pb) {
		 //apple needs to be gone to be true
			pb.setColor(new Color(135, 48, 29));
			pb.fill(this); 
		}
	 


	public Apple() {
		// TODO Auto-generated constructor stub
	}
	
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

}




