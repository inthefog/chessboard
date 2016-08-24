package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Knight painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class KnightPainter extends PiecePainter {

	@Override
	protected Polygon createPolygon(int width, int height) {
		Polygon knightPolygon = new Polygon();

	    knightPolygon.addPoint(29*width/108,10*height/108);
	    knightPolygon.addPoint(28*width/108,14*height/108);
	    knightPolygon.addPoint(29*width/108,17*height/108);
	    knightPolygon.addPoint(29*width/108,21*height/108);
	    knightPolygon.addPoint(28*width/108,24*height/108);
	    knightPolygon.addPoint(25*width/108,28*height/108);
	    knightPolygon.addPoint(23*width/108,32*height/108);
	    knightPolygon.addPoint(20*width/108,44*height/108);
	    knightPolygon.addPoint(17*width/108,48*height/108);
	    knightPolygon.addPoint(14*width/108,52*height/108);
	    knightPolygon.addPoint(12*width/108,56*height/108);
	    knightPolygon.addPoint(10*width/108,60*height/108);
	    knightPolygon.addPoint(9*width/108,64*height/108);
	    knightPolygon.addPoint(9*width/108,68*height/108);
	    knightPolygon.addPoint(10*width/108,71*height/108);
	    knightPolygon.addPoint(12*width/108,73*height/108);
	    knightPolygon.addPoint(15*width/108,74*height/108);
	    knightPolygon.addPoint(18*width/108,75*height/108);
	    knightPolygon.addPoint(22*width/108,75*height/108);
	    knightPolygon.addPoint(23*width/108,78*height/108);
	    knightPolygon.addPoint(26*width/108,78*height/108);
	    knightPolygon.addPoint(28*width/108,76*height/108);
	    knightPolygon.addPoint(30*width/108,72*height/108);
	    knightPolygon.addPoint(38*width/108,64*height/108);
	    knightPolygon.addPoint(40*width/108,64*height/108);
	    knightPolygon.addPoint(43*width/108,61*height/108);
	    knightPolygon.addPoint(45*width/108,61*height/108);
	    knightPolygon.addPoint(48*width/108,58*height/108);
	    knightPolygon.addPoint(50*width/108,58*height/108);
	    knightPolygon.addPoint(53*width/108,56*height/108);
	    knightPolygon.addPoint(55*width/108,54*height/108);
	    knightPolygon.addPoint(56*width/108,57*height/108);
	    knightPolygon.addPoint(55*width/108,61*height/108);
	    knightPolygon.addPoint(54*width/108,65*height/108);
	    knightPolygon.addPoint(51*width/108,69*height/108);
	    knightPolygon.addPoint(48*width/108,73*height/108);
	    knightPolygon.addPoint(44*width/108,77*height/108);
	    knightPolygon.addPoint(41*width/108,81*height/108);
	    knightPolygon.addPoint(37*width/108,85*height/108);
	    knightPolygon.addPoint(35*width/108,89*height/108);
	    knightPolygon.addPoint(34*width/108,93*height/108);
	    knightPolygon.addPoint(33*width/108,97*height/108);
	    knightPolygon.addPoint(35*width/108,99*height/108);
	    knightPolygon.addPoint(95*width/108,99*height/108);
	    knightPolygon.addPoint(98*width/108,97*height/108);
	    knightPolygon.addPoint(98*width/108,89*height/108);
	    knightPolygon.addPoint(97*width/108,85*height/108);
	    knightPolygon.addPoint(97*width/108,77*height/108);
	    knightPolygon.addPoint(96*width/108,73*height/108);
	    knightPolygon.addPoint(96*width/108,69*height/108);
	    knightPolygon.addPoint(94*width/108,61*height/108);
	    knightPolygon.addPoint(92*width/108,53*height/108);
	    knightPolygon.addPoint(90*width/108,49*height/108);
	    knightPolygon.addPoint(89*width/108,45*height/108);
	    knightPolygon.addPoint(87*width/108,41*height/108);
	    knightPolygon.addPoint(84*width/108,37*height/108);
	    knightPolygon.addPoint(81*width/108,33*height/108);
	    knightPolygon.addPoint(74*width/108,25*height/108);
	    knightPolygon.addPoint(70*width/108,23*height/108);
	    knightPolygon.addPoint(66*width/108,21*height/108);
	    knightPolygon.addPoint(62*width/108,20*height/108);
	    knightPolygon.addPoint(54*width/108,18*height/108);
	    knightPolygon.addPoint(49*width/108,10*height/108);
	    knightPolygon.addPoint(46*width/108,13*height/108);
	    knightPolygon.addPoint(44*width/108,17*height/108);
	    knightPolygon.addPoint(41*width/108,20*height/108);
	    knightPolygon.addPoint(37*width/108,16*height/108);

	    return knightPolygon;
	}

	@Override
	protected void drawOutline(Graphics g, int width, int height) {
	    g.drawLine(29*width/108,42*height/108,34*width/108,35*height/108);
	    g.drawLine(34*width/108,35*height/108,38*width/108,34*height/108);
	    g.drawLine(38*width/108,34*height/108,35*width/108,39*height/108);
	    g.drawLine(34*width/108,39*height/108,29*width/108,42*height/108);
	    g.drawLine(41*width/108,20*height/108,34*width/108,26*height/108);
	    g.drawLine(22*width/108,75*height/108,25*width/108,68*height/108);
	}

}
