package com.inthefog.chessboard.view.painters;

import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Bishop painter.
 * Vector graphics code is based solely on from projects EBoard (by Felipe Bergo) and Jin (by Alexander Maryanovsky).
 * @author pasha
 *
 */
public class BishopPainter extends PiecePainter {

	@Override
	protected Polygon createPolygon(int width, int height) {
		
		Polygon bishopPolygon = new Polygon();

	    bishopPolygon.addPoint(53*width/108,7*height/108);
	    bishopPolygon.addPoint(48*width/108,10*height/108);
	    bishopPolygon.addPoint(47*width/108,14*height/108);
	    bishopPolygon.addPoint(48*width/108,17*height/108);
	    bishopPolygon.addPoint(50*width/108,19*height/108);
	    bishopPolygon.addPoint(47*width/108,22*height/108);
	    bishopPolygon.addPoint(43*width/108,24*height/108);
	    bishopPolygon.addPoint(39*width/108,27*height/108);
	    bishopPolygon.addPoint(35*width/108,30*height/108);
	    bishopPolygon.addPoint(31*width/108,34*height/108);
	    bishopPolygon.addPoint(29*width/108,38*height/108);
	    bishopPolygon.addPoint(27*width/108,42*height/108);
	    bishopPolygon.addPoint(27*width/108,46*height/108);
	    bishopPolygon.addPoint(27*width/108,50*height/108);
	    bishopPolygon.addPoint(28*width/108,53*height/108);
	    bishopPolygon.addPoint(29*width/108,56*height/108);
	    bishopPolygon.addPoint(31*width/108,58*height/108);
	    bishopPolygon.addPoint(32*width/108,61*height/108);
	    bishopPolygon.addPoint(34*width/108,63*height/108);
	    bishopPolygon.addPoint(36*width/108,65*height/108);
	    bishopPolygon.addPoint(35*width/108,69*height/108);
	    bishopPolygon.addPoint(33*width/108,74*height/108);
	    bishopPolygon.addPoint(32*width/108,77*height/108);
	    bishopPolygon.addPoint(32*width/108,80*height/108);
	    bishopPolygon.addPoint(35*width/108,81*height/108);
	    bishopPolygon.addPoint(38*width/108,82*height/108);
	    bishopPolygon.addPoint(42*width/108,82*height/108);
	    bishopPolygon.addPoint(45*width/108,83*height/108);
	    bishopPolygon.addPoint(48*width/108,83*height/108);
	    bishopPolygon.addPoint(47*width/108,86*height/108);
	    bishopPolygon.addPoint(40*width/108,88*height/108);
	    bishopPolygon.addPoint(20*width/108,88*height/108);
	    bishopPolygon.addPoint(12*width/108,90*height/108);
	    bishopPolygon.addPoint(8*width/108,92*height/108);
	    bishopPolygon.addPoint(8*width/108,94*height/108);
	    bishopPolygon.addPoint(10*width/108,96*height/108);
	    bishopPolygon.addPoint(11*width/108,99*height/108);
	    bishopPolygon.addPoint(13*width/108,100*height/108);
	    bishopPolygon.addPoint(15*width/108,100*height/108);
	    bishopPolygon.addPoint(18*width/108,99*height/108);
	    bishopPolygon.addPoint(22*width/108,98*height/108);
	    bishopPolygon.addPoint(30*width/108,98*height/108);
	    bishopPolygon.addPoint(33*width/108,99*height/108);
	    bishopPolygon.addPoint(41*width/108,99*height/108);
	    bishopPolygon.addPoint(44*width/108,98*height/108);
	    bishopPolygon.addPoint(47*width/108,96*height/108);
	    bishopPolygon.addPoint(49*width/108,96*height/108);
	    bishopPolygon.addPoint(52*width/108,94*height/108);
	    bishopPolygon.addPoint(54*width/108,94*height/108);
	    bishopPolygon.addPoint(57*width/108,95*height/108);
	    bishopPolygon.addPoint(59*width/108,97*height/108);
	    bishopPolygon.addPoint(65*width/108,99*height/108);
	    bishopPolygon.addPoint(73*width/108,99*height/108);
	    bishopPolygon.addPoint(76*width/108,98*height/108);
	    bishopPolygon.addPoint(88*width/108,98*height/108);
	    bishopPolygon.addPoint(91*width/108,99*height/108);
	    bishopPolygon.addPoint(93*width/108,101*height/108);
	    bishopPolygon.addPoint(95*width/108,99*height/108);
	    bishopPolygon.addPoint(97*width/108,95*height/108);
	    bishopPolygon.addPoint(96*width/108,91*height/108);
	    bishopPolygon.addPoint(92*width/108,89*height/108);
	    bishopPolygon.addPoint(88*width/108,88*height/108);
	    bishopPolygon.addPoint(68*width/108,88*height/108);
	    bishopPolygon.addPoint(60*width/108,86*height/108);
	    bishopPolygon.addPoint(59*width/108,83*height/108);
	    bishopPolygon.addPoint(62*width/108,83*height/108);
	    bishopPolygon.addPoint(66*width/108,82*height/108);
	    bishopPolygon.addPoint(69*width/108,82*height/108);
	    bishopPolygon.addPoint(73*width/108,81*height/108);
	    bishopPolygon.addPoint(75*width/108,79*height/108);
	    bishopPolygon.addPoint(73*width/108,74*height/108);
	    bishopPolygon.addPoint(71*width/108,69*height/108);
	    bishopPolygon.addPoint(69*width/108,65*height/108);
	    bishopPolygon.addPoint(71*width/108,63*height/108);
	    bishopPolygon.addPoint(73*width/108,61*height/108);
	    bishopPolygon.addPoint(75*width/108,59*height/108);
	    bishopPolygon.addPoint(77*width/108,55*height/108);
	    bishopPolygon.addPoint(78*width/108,51*height/108);
	    bishopPolygon.addPoint(79*width/108,47*height/108);
	    bishopPolygon.addPoint(79*width/108,43*height/108);
	    bishopPolygon.addPoint(77*width/108,39*height/108);
	    bishopPolygon.addPoint(75*width/108,35*height/108);
	    bishopPolygon.addPoint(71*width/108,31*height/108);
	    bishopPolygon.addPoint(67*width/108,27*height/108);
	    bishopPolygon.addPoint(63*width/108,24*height/108);
	    bishopPolygon.addPoint(59*width/108,22*height/108);
	    bishopPolygon.addPoint(56*width/108,19*height/108);
	    bishopPolygon.addPoint(58*width/108,17*height/108);
	    bishopPolygon.addPoint(59*width/108,13*height/108);
	    bishopPolygon.addPoint(57*width/108,9*height/108);

	    return bishopPolygon;
	}

	@Override
	protected void drawOutline(Graphics g, int width, int height) {
	    g.drawLine(53*width/108,35*height/108,53*width/108,54*height/108);
	    g.drawLine(44*width/108,44*height/108,62*width/108,44*height/108);
	    g.drawLine(48*width/108,83*height/108,59*width/108,83*height/108);
	    g.drawLine(36*width/108,65*height/108,53*width/108,63*height/108);
	    g.drawLine(53*width/108,63*height/108,69*width/108,65*height/108);
	    g.drawLine(33*width/108,74*height/108,53*width/108,72*height/108);
	    g.drawLine(53*width/108,72*height/108,73*width/108,74*height/108);
	}

}
