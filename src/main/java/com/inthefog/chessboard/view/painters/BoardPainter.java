package com.inthefog.chessboard.view.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Paints plain board with two preselected colors for light and dark squares
 * @author pasha
 *
 */
public class BoardPainter {
	
	private final Color lightSquareColor = new Color(250, 230, 190);
	private final Color darkSquareColor = new Color(135, 117, 81); 
	
	/**
	 * 
	 * @param g
	 * @param rect
	 */
	public void paint(Graphics g, Rectangle rect, boolean flipped) {
		int squareWidth = rect.width/8;
		int squareHeight = rect.height/8;
		
	    for (int rank=0; rank<=8; rank++) {
	    	for (int file=0; file<8; file++) {
		        
	    		if ((rank+file) %2 == 0) {
		          g.setColor(lightSquareColor);
		        }
		        else {
		          g.setColor(darkSquareColor); 
		        }
		        
		        g.fillRect(rect.x+file*squareWidth, rect.y+rank*squareHeight, squareWidth, squareHeight);
	    	}
	    }
	}
}
