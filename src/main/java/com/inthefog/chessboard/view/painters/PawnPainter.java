package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Pawn painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class PawnPainter extends PiecePainter {

	@Override
	protected Polygon createPolygon(int width, int height) {
	    
		Polygon pawnPolygon = new Polygon();

	    pawnPolygon.addPoint(50*width/108,15*height/108);
	    pawnPolygon.addPoint(46*width/108,17*height/108);
	    pawnPolygon.addPoint(43*width/108,21*height/108);
	    pawnPolygon.addPoint(42*width/108,25*height/108);
	    pawnPolygon.addPoint(42*width/108,29*height/108);
	    pawnPolygon.addPoint(45*width/108,33*height/108);
	    pawnPolygon.addPoint(40*width/108,35*height/108);
	    pawnPolygon.addPoint(36*width/108,39*height/108);
	    pawnPolygon.addPoint(35*width/108,43*height/108);
	    pawnPolygon.addPoint(34*width/108,47*height/108);
	    pawnPolygon.addPoint(34*width/108,51*height/108);
	    pawnPolygon.addPoint(35*width/108,54*height/108);
	    pawnPolygon.addPoint(36*width/108,57*height/108);
	    pawnPolygon.addPoint(38*width/108,59*height/108);
	    pawnPolygon.addPoint(40*width/108,61*height/108);
	    pawnPolygon.addPoint(34*width/108,65*height/108);
	    pawnPolygon.addPoint(29*width/108,70*height/108);
	    pawnPolygon.addPoint(27*width/108,74*height/108);
	    pawnPolygon.addPoint(23*width/108,82*height/108);
	    pawnPolygon.addPoint(22*width/108,86*height/108);
	    pawnPolygon.addPoint(21*width/108,90*height/108);
	    pawnPolygon.addPoint(21*width/108,94*height/108);
	    pawnPolygon.addPoint(22*width/108,97*height/108);
	    pawnPolygon.addPoint(25*width/108,98*height/108);
	    pawnPolygon.addPoint(81*width/108,98*height/108);
	    pawnPolygon.addPoint(85*width/108,97*height/108);
	    pawnPolygon.addPoint(85*width/108,93*height/108);
	    pawnPolygon.addPoint(84*width/108,85*height/108);
	    pawnPolygon.addPoint(83*width/108,81*height/108);
	    pawnPolygon.addPoint(80*width/108,74*height/108);
	    pawnPolygon.addPoint(77*width/108,69*height/108);
	    pawnPolygon.addPoint(72*width/108,64*height/108);
	    pawnPolygon.addPoint(67*width/108,61*height/108);
	    pawnPolygon.addPoint(69*width/108,59*height/108);
	    pawnPolygon.addPoint(71*width/108,55*height/108);
	    pawnPolygon.addPoint(72*width/108,51*height/108);
	    pawnPolygon.addPoint(72*width/108,47*height/108);
	    pawnPolygon.addPoint(71*width/108,43*height/108);
	    pawnPolygon.addPoint(69*width/108,39*height/108);
	    pawnPolygon.addPoint(65*width/108,35*height/108);
	    pawnPolygon.addPoint(61*width/108,33*height/108);
	    pawnPolygon.addPoint(64*width/108,29*height/108);
	    pawnPolygon.addPoint(64*width/108,25*height/108);
	    pawnPolygon.addPoint(62*width/108,21*height/108);
	    pawnPolygon.addPoint(59*width/108,17*height/108);
	    pawnPolygon.addPoint(55*width/108,15*height/108);

	    return pawnPolygon;
	}

	@Override
	protected void drawOutline(Graphics g, int width, int height) {
		//No outline for pawn
	}

}
