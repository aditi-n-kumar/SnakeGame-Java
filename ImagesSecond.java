package SnakeGame;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;



public class ImagesSecond {
	
	BufferedImage spongebobSquarepantsCongratulations; {
		try {
			
			File file2 = new File("src/images/spongebobSquarepantsCongratulations.gif");
			FileInputStream fis2 = new FileInputStream(file2);
			spongebobSquarepantsCongratulations = ImageIO.read(fis2);
			
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}