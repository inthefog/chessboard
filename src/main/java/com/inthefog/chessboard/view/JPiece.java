package com.inthefog.chessboard.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;
import com.inthefog.chessboard.view.painters.BishopPainter;
import com.inthefog.chessboard.view.painters.KingPainter;
import com.inthefog.chessboard.view.painters.KnightPainter;
import com.inthefog.chessboard.view.painters.PawnPainter;
import com.inthefog.chessboard.view.painters.PiecePainter;
import com.inthefog.chessboard.view.painters.QueenPainter;
import com.inthefog.chessboard.view.painters.RookPainter;

@SuppressWarnings("serial")
public class JPiece extends JComponent implements MouseListener, MouseMotionListener {

	private ChessCoords posCoords = null;
	private PiecePainter piecePainter = null;
	private Point mousePressedPoint = null;
	private JBoard board = null;

	
	/**
	 * 
	 * @param piece
	 */
	public JPiece(ChessPiece piece) {
		super();
		this.piecePainter = getPainter(piece);
	
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/**
	 * 
	 * @param piece
	 * @param posCoords
	 * @param board
	 */
	public JPiece(ChessPiece piece, ChessCoords posCoords, JBoard board) {
		this(piece);
		this.posCoords = posCoords;
		this.board = board;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessCoords getPosCoords() {
		return posCoords;
	}
	
	/**
	 * 
	 * @param posCoords
	 */
	public void setPosCoords(ChessCoords posCoords) {
		this.posCoords = posCoords;
	}

	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		piecePainter.paint(g, new Rectangle(0, 0, getWidth(), getHeight()));
	}
	
	/**
	 * 
	 * @param posCoords
	 */
	/*public void move(ChessCoords posCoords) {
		this.posCoords = posCoords;
		if (board != null) {
			board.movePiece(this, posCoords);
		}
	}*/
	
	/**
	 * 
	 * @param piece
	 * @return
	 */
	private PiecePainter getPainter(ChessPiece piece) {

		if (piece == null) {
			return null;
		}
		
		PieceType pieceType = piece.getType();
		PiecePainter piecePainter = null;
		
		if (pieceType == PieceType.PAWN) {
			piecePainter = new PawnPainter();
		}
		else if (pieceType == PieceType.KNIGHT) {
			piecePainter = new KnightPainter();
		}
		else if (pieceType == PieceType.BISHOP) {
			piecePainter = new BishopPainter();
		}
		else if (pieceType == PieceType.ROOK) {
			piecePainter = new RookPainter();
		}
		else if (pieceType == PieceType.QUEEN) {
			piecePainter = new QueenPainter();
		}
		else if (pieceType == PieceType.KING) {
			piecePainter = new KingPainter();
		}
		else {
			return null;
		}
		
		Color pieceColor = (piece.getColor() == PieceColor.WHITE) ? Color.WHITE : Color.BLACK;
		Color outlineColor = (pieceColor == Color.WHITE) ? Color.BLACK : Color.WHITE;
		
		return piecePainter.setColors(pieceColor, outlineColor);
	}

	/**
	 * 
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if (board != null) {
			board.reportPieceMovement(this, new Point(e.getX() + getLocation().x, e.getY() + getLocation().y));
		}
		
		setLocation(e.getX() - mousePressedPoint.x + getLocation().x, e.getY() - mousePressedPoint.y + getLocation().y) ;
	}

	/**
	 * 
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		mousePressedPoint = e.getPoint();
	}
	
	/**
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (board != null) {
			board.reportPieceMovementDone(this, new Point(e.getX() + getLocation().x, e.getY() + getLocation().y));
		}
	}

	/**
	 * 
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	public void dispose() {
		board.remove(this);
		board.repaint();
	}
}
