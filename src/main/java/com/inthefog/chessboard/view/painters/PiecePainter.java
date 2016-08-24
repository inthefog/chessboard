package com.inthefog.chessboard.view.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 * Base class for pieces vector painter. 
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public abstract class PiecePainter {

	private int width = 0;
	private int height = 0;
	private Polygon polygon = null;
	private Color pieceColor = Color.WHITE;
	private Color outlineColor = Color.BLACK;
	
	/**
	 * 
	 * @param pieceColor
	 * @param outlineColor
	 * @return
	 */
	public PiecePainter setColors(Color pieceColor, Color outlineColor) {
		this.pieceColor = pieceColor;
		this.outlineColor = outlineColor;
		return this;
	}

	/**
	 *
	 * @param g
	 * @param rect
	 */
	public void paint(Graphics g, Rectangle rect) {

		if (polygon == null || rect.width != width || rect.height != height) {
			this.polygon = createPolygon(rect.width, rect.height);
			this.width = rect.width;
			this.height = rect.height;
		}
		
		g.translate(rect.x, rect.y);
		g.setColor(pieceColor);
		g.fillPolygon(polygon);
		g.setColor(outlineColor);
		g.drawPolygon(polygon);
		drawOutline(g, rect.width, rect.height);
		g.translate(-rect.x, -rect.y);
	}
	
	/**
	 * 
	 * @param width
	 * @param height
	 * @return
	 */
	protected abstract Polygon createPolygon(int width, int height);
	
	/**
	 * 
	 * @param g
	 * @param width
	 * @param height
	 */
	protected abstract void drawOutline(Graphics g, int width, int height);
	
}
