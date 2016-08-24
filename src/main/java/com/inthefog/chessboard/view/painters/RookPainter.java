package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Rook painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class RookPainter extends PiecePainter {

	@Override
	protected Polygon createPolygon(int width, int height) {
		
		Polygon rookPolygon = new Polygon();

	    rookPolygon.addPoint(35*width/108,16*height/108);
	    rookPolygon.addPoint(21*width/108,16*height/108);
	    rookPolygon.addPoint(21*width/108,31*height/108);
	    rookPolygon.addPoint(29*width/108,39*height/108);
	    rookPolygon.addPoint(29*width/108,71*height/108);
	    rookPolygon.addPoint(27*width/108,75*height/108);
	    rookPolygon.addPoint(23*width/108,79*height/108);
	    rookPolygon.addPoint(22*width/108,83*height/108);
	    rookPolygon.addPoint(22*width/108,90*height/108);
	    rookPolygon.addPoint(17*width/108,90*height/108);
	    rookPolygon.addPoint(15*width/108,93*height/108);
	    rookPolygon.addPoint(15*width/108,97*height/108);
	    rookPolygon.addPoint(17*width/108,99*height/108);
	    rookPolygon.addPoint(89*width/108,99*height/108);
	    rookPolygon.addPoint(91*width/108,97*height/108);
	    rookPolygon.addPoint(91*width/108,93*height/108);
	    rookPolygon.addPoint(89*width/108,90*height/108);
	    rookPolygon.addPoint(84*width/108,90*height/108);
	    rookPolygon.addPoint(84*width/108,83*height/108);
	    rookPolygon.addPoint(83*width/108,79*height/108);
	    rookPolygon.addPoint(79*width/108,75*height/108);
	    rookPolygon.addPoint(77*width/108,71*height/108);
	    rookPolygon.addPoint(77*width/108,39*height/108);
	    rookPolygon.addPoint(85*width/108,31*height/108);
	    rookPolygon.addPoint(85*width/108,16*height/108);
	    rookPolygon.addPoint(71*width/108,16*height/108);
	    rookPolygon.addPoint(71*width/108,22*height/108);
	    rookPolygon.addPoint(60*width/108,22*height/108);
	    rookPolygon.addPoint(60*width/108,16*height/108);
	    rookPolygon.addPoint(46*width/108,16*height/108);
	    rookPolygon.addPoint(46*width/108,22*height/108);
	    rookPolygon.addPoint(35*width/108,22*height/108);

	    return rookPolygon;
	}

	@Override
	protected void drawOutline(Graphics g, int width, int height) {
		g.drawLine(21*width/108,31*height/108,85*width/108,31*height/108);
	    g.drawLine(23*width/108,79*height/108,83*width/108,79*height/108);
	    g.drawLine(27*width/108,75*height/108,79*width/108,75*height/108);
	    g.drawLine(29*width/108,39*height/108,77*width/108,39*height/108);
	    g.drawLine(22*width/108,90*height/108,84*width/108,90*height/108);
	}

}
