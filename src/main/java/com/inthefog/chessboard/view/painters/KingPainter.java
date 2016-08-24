package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * King painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class KingPainter extends PiecePainter {


	@Override
	protected Polygon createPolygon(int width, int height) {
		
		Polygon kingPolygon = new Polygon();

	    kingPolygon.addPoint(50*width/108,8*height/108);
	    kingPolygon.addPoint(51*width/108,13*height/108);
	    kingPolygon.addPoint(46*width/108,12*height/108);
	    kingPolygon.addPoint(46*width/108,17*height/108);
	    kingPolygon.addPoint(51*width/108,16*height/108);
	    kingPolygon.addPoint(51*width/108,23*height/108);
	    kingPolygon.addPoint(49*width/108,26*height/108);
	    kingPolygon.addPoint(46*width/108,29*height/108);
	    kingPolygon.addPoint(44*width/108,37*height/108);
	    kingPolygon.addPoint(43*width/108,41*height/108);
	    kingPolygon.addPoint(37*width/108,37*height/108);
	    kingPolygon.addPoint(31*width/108,35*height/108);
	    kingPolygon.addPoint(27*width/108,35*height/108);
	    kingPolygon.addPoint(23*width/108,35*height/108);
	    kingPolygon.addPoint(19*width/108,36*height/108);
	    kingPolygon.addPoint(15*width/108,38*height/108);
	    kingPolygon.addPoint(11*width/108,41*height/108);
	    kingPolygon.addPoint(9*width/108,45*height/108);
	    kingPolygon.addPoint(7*width/108,49*height/108);
	    kingPolygon.addPoint(7*width/108,53*height/108);
	    kingPolygon.addPoint(8*width/108,56*height/108);
	    kingPolygon.addPoint(9*width/108,59*height/108);
	    kingPolygon.addPoint(11*width/108,61*height/108);
	    kingPolygon.addPoint(12*width/108,64*height/108);
	    kingPolygon.addPoint(18*width/108,70*height/108);
	    kingPolygon.addPoint(21*width/108,71*height/108);
	    kingPolygon.addPoint(22*width/108,74*height/108);
	    kingPolygon.addPoint(22*width/108,82*height/108);
	    kingPolygon.addPoint(23*width/108,85*height/108);
	    kingPolygon.addPoint(23*width/108,93*height/108);
	    kingPolygon.addPoint(38*width/108,98*height/108);
	    kingPolygon.addPoint(41*width/108,98*height/108);
	    kingPolygon.addPoint(45*width/108,99*height/108);
	    kingPolygon.addPoint(57*width/108,99*height/108);
	    kingPolygon.addPoint(61*width/108,98*height/108);
	    kingPolygon.addPoint(64*width/108,98*height/108);
	    kingPolygon.addPoint(68*width/108,97*height/108);
	    kingPolygon.addPoint(83*width/108,92*height/108);
	    kingPolygon.addPoint(83*width/108,80*height/108);
	    kingPolygon.addPoint(84*width/108,76*height/108);
	    kingPolygon.addPoint(84*width/108,72*height/108);
	    kingPolygon.addPoint(87*width/108,70*height/108);
	    kingPolygon.addPoint(95*width/108,62*height/108);
	    kingPolygon.addPoint(97*width/108,60*height/108);
	    kingPolygon.addPoint(98*width/108,56*height/108);
	    kingPolygon.addPoint(99*width/108,52*height/108);
	    kingPolygon.addPoint(98*width/108,48*height/108);
	    kingPolygon.addPoint(96*width/108,44*height/108);
	    kingPolygon.addPoint(93*width/108,40*height/108);
	    kingPolygon.addPoint(89*width/108,37*height/108);
	    kingPolygon.addPoint(85*width/108,36*height/108);
	    kingPolygon.addPoint(81*width/108,35*height/108);
	    kingPolygon.addPoint(77*width/108,35*height/108);
	    kingPolygon.addPoint(73*width/108,36*height/108);
	    kingPolygon.addPoint(67*width/108,39*height/108);
	    kingPolygon.addPoint(62*width/108,41*height/108);
	    kingPolygon.addPoint(61*width/108,37*height/108);
	    kingPolygon.addPoint(59*width/108,29*height/108);
	    kingPolygon.addPoint(56*width/108,26*height/108);
	    kingPolygon.addPoint(54*width/108,23*height/108);
	    kingPolygon.addPoint(54*width/108,16*height/108);
	    kingPolygon.addPoint(59*width/108,17*height/108);
	    kingPolygon.addPoint(59*width/108,12*height/108);
	    kingPolygon.addPoint(54*width/108,13*height/108);
	    kingPolygon.addPoint(55*width/108,8*height/108);

	    return kingPolygon;
	}


	@Override
	protected void drawOutline(Graphics g, int width, int height) {
	    g.drawLine(44*width/108,40*height/108,53*width/108,58*height/108);
	    g.drawLine(53*width/108,58*height/108,53*width/108,68*height/108);
	    g.drawLine(62*width/108,40*height/108,53*width/108,58*height/108);
	    g.drawLine(23*width/108,73*height/108,53*width/108,68*height/108);
	    g.drawLine(53*width/108,68*height/108,83*width/108,73*height/108);
	    g.drawLine(24*width/108,92*height/108,53*width/108,86*height/108);
	    g.drawLine(53*width/108,86*height/108,82*width/108,92*height/108);
	}
}
