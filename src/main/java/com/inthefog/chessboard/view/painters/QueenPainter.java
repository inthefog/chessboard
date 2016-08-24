package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Queen painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class QueenPainter extends PiecePainter {

	@Override
	protected Polygon createPolygon(int width, int height) {
		
		Polygon queenPolygon = new Polygon();

	    queenPolygon.addPoint(51*width/108,7*height/108);
	    queenPolygon.addPoint(48*width/108,10*height/108);
	    queenPolygon.addPoint(47*width/108,14*height/108);
	    queenPolygon.addPoint(49*width/108,16*height/108);
	    queenPolygon.addPoint(51*width/108,18*height/108);
	    queenPolygon.addPoint(43*width/108,54*height/108);
	    queenPolygon.addPoint(31*width/108,22*height/108);
	    queenPolygon.addPoint(34*width/108,18*height/108);
	    queenPolygon.addPoint(34*width/108,14*height/108);
	    queenPolygon.addPoint(30*width/108,11*height/108);
	    queenPolygon.addPoint(26*width/108,12*height/108);
	    queenPolygon.addPoint(23*width/108,15*height/108);
	    queenPolygon.addPoint(24*width/108,18*height/108);
	    queenPolygon.addPoint(25*width/108,21*height/108);
	    queenPolygon.addPoint(28*width/108,22*height/108);
	    queenPolygon.addPoint(27*width/108,57*height/108);
	    queenPolygon.addPoint(12*width/108,33*height/108);
	    queenPolygon.addPoint(13*width/108,29*height/108);
	    queenPolygon.addPoint(14*width/108,25*height/108);
	    queenPolygon.addPoint(11*width/108,21*height/108);
	    queenPolygon.addPoint(7*width/108,20*height/108);
	    queenPolygon.addPoint(3*width/108,22*height/108);
	    queenPolygon.addPoint(2*width/108,26*height/108);
	    queenPolygon.addPoint(3*width/108,29*height/108);
	    queenPolygon.addPoint(5*width/108,31*height/108);
	    queenPolygon.addPoint(8*width/108,32*height/108);
	    queenPolygon.addPoint(10*width/108,34*height/108);
	    queenPolygon.addPoint(15*width/108,65*height/108);
	    queenPolygon.addPoint(21*width/108,71*height/108);
	    queenPolygon.addPoint(25*width/108,83*height/108);
	    queenPolygon.addPoint(22*width/108,88*height/108);
	    queenPolygon.addPoint(19*width/108,95*height/108);
	    queenPolygon.addPoint(28*width/108,98*height/108);
	    queenPolygon.addPoint(32*width/108,98*height/108);
	    queenPolygon.addPoint(35*width/108,99*height/108);
	    queenPolygon.addPoint(39*width/108,99*height/108);
	    queenPolygon.addPoint(42*width/108,100*height/108);
	    queenPolygon.addPoint(62*width/108,100*height/108);
	    queenPolygon.addPoint(65*width/108,99*height/108);
	    queenPolygon.addPoint(69*width/108,99*height/108);
	    queenPolygon.addPoint(73*width/108,98*height/108);
	    queenPolygon.addPoint(79*width/108,98*height/108);
	    queenPolygon.addPoint(83*width/108,97*height/108);
	    queenPolygon.addPoint(87*width/108,95*height/108);
	    queenPolygon.addPoint(84*width/108,88*height/108);
	    queenPolygon.addPoint(81*width/108,83*height/108);
	    queenPolygon.addPoint(83*width/108,71*height/108);
	    queenPolygon.addPoint(89*width/108,65*height/108);
	    queenPolygon.addPoint(96*width/108,33*height/108);
	    queenPolygon.addPoint(98*width/108,32*height/108);
	    queenPolygon.addPoint(101*width/108,31*height/108);
	    queenPolygon.addPoint(103*width/108,29*height/108);
	    queenPolygon.addPoint(104*width/108,25*height/108);
	    queenPolygon.addPoint(101*width/108,21*height/108);
	    queenPolygon.addPoint(97*width/108,20*height/108);
	    queenPolygon.addPoint(93*width/108,22*height/108);
	    queenPolygon.addPoint(92*width/108,26*height/108);
	    queenPolygon.addPoint(93*width/108,29*height/108);
	    queenPolygon.addPoint(94*width/108,32*height/108);
	    queenPolygon.addPoint(78*width/108,56*height/108);
	    queenPolygon.addPoint(78*width/108,23*height/108);
	    queenPolygon.addPoint(80*width/108,22*height/108);
	    queenPolygon.addPoint(82*width/108,18*height/108);
	    queenPolygon.addPoint(82*width/108,14*height/108);
	    queenPolygon.addPoint(78*width/108,11*height/108);
	    queenPolygon.addPoint(74*width/108,12*height/108);
	    queenPolygon.addPoint(71*width/108,15*height/108);
	    queenPolygon.addPoint(72*width/108,18*height/108);
	    queenPolygon.addPoint(73*width/108,21*height/108);
	    queenPolygon.addPoint(75*width/108,23*height/108);
	    queenPolygon.addPoint(62*width/108,54*height/108);
	    queenPolygon.addPoint(54*width/108,18*height/108);
	    queenPolygon.addPoint(57*width/108,17*height/108);
	    queenPolygon.addPoint(59*width/108,13*height/108);
	    queenPolygon.addPoint(57*width/108,9*height/108);
	    queenPolygon.addPoint(53*width/108,7*height/108);

	    return queenPolygon;
	}

	@Override
	protected void drawOutline(Graphics g, int width, int height) {
	    g.drawLine(19*width/108,95*height/108,53*width/108,88*height/108);
	    g.drawLine(53*width/108,88*height/108,87*width/108,95*height/108);
	    g.drawLine(25*width/108,83*height/108,53*width/108,76*height/108);
	    g.drawLine(53*width/108,76*height/108,81*width/108,83*height/108);
	}

}
