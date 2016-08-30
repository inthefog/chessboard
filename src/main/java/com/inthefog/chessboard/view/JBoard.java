package com.inthefog.chessboard.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;

import com.inthefog.chessboard.view.helpers.ClipboardReadWrite;
import com.inthefog.chessboard.view.helpers.JBoardActions;
import org.apache.commons.io.IOUtils;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessGame;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.ChessPosition;
import com.inthefog.chessboard.model.ChessPositionDelta;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;
import com.inthefog.chessboard.view.painters.BoardPainter;

/**
 * Chess-board with full visual capabilities.
 */

@SuppressWarnings("serial")
public class JBoard extends JComponent implements ComponentListener {
	
	private JPiece[][] board = new JPiece[8][8];
	private BoardPainter boardPainter = new BoardPainter();
	private ChessGame game = new ChessGame(null);
	private boolean flipped = false;
	private boolean autoPromotionEnabled = false;
	private byte[] moveSound = null;
	private JBoardActions actions = null;

	/**
	 * 
	 */
	public JBoard() {
		super();
		this.addComponentListener(this);
		actions = new JBoardActions(this);

		try {
			moveSound = IOUtils.toByteArray(getClass().getClassLoader().getResourceAsStream("chessmove.wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void clearBoard() {
		this.removeAll();
		board = new JPiece[8][8];
		validate();
		repaint();
	}
	
	/**
	 * 
	 * @param startPosition
	 */
	public void setGame(ChessPosition startPosition) {
		game = new ChessGame(startPosition);
		setPosition(startPosition);
	}

	/**
	 * 
	 * @param position
	 */
	private void setPosition(ChessPosition position) {
		clearBoard();	
		if (position != null) {
			for (int rank=0; rank<8; rank++) {
				for (int file=0; file<8; file++) {
					ChessCoords posCoords = new ChessCoords(rank, file);
					ChessPiece piece = position.getPiece(posCoords);
					if (piece != null) {
						JPiece jpiece = new JPiece(piece, posCoords, this);
						addPiece(jpiece);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param piece
	 * @param posCoords
	 */
	private void movePiece(JPiece piece, ChessCoords posCoords) {
	
		ChessCoords oldPosCoords = piece.getPosCoords();
		if (oldPosCoords != null) {
			board[oldPosCoords.getRank()][oldPosCoords.getFile()] = null;
		}
		
		piece.setPosCoords(posCoords);
		ChessCoords squareCoords = getSquareCoords(posCoords);
		piece.setLocation(getSquareLocation(squareCoords));
		board[posCoords.getRank()][posCoords.getFile()] = piece;
	}
	
	/**
	 * 
	 * @param oldPosCoords
	 * @param newPosCoords
	 */
	private void movePiece(ChessCoords oldPosCoords, ChessCoords newPosCoords) {
		movePiece(board[oldPosCoords.getRank()][oldPosCoords.getFile()], newPosCoords);
	}
	
	/**
	 * 
	 * @param piece
	 */
	private void addPiece(JPiece piece) {
		this.add(piece);
		piece.setSize(getSquareSize());
		movePiece(piece, piece.getPosCoords());
	}
	
	/**
	 * 
	 * @param piece
	 */
	private void disposePiece(JPiece piece) {
		this.remove(piece);
		ChessCoords posCoords = piece.getPosCoords();
		board[posCoords.getRank()][posCoords.getFile()] = null;
	}
	
	/**
	 * 
	 * @param posCoords
	 */
	private void disposePiece(ChessCoords posCoords) {
		JPiece piece = board[posCoords.getRank()][posCoords.getFile()];
		disposePiece(piece);
	}
	
	/**
	 * 
	 */
	public void flip() {
		flipped = !flipped;
		setPosition(game.get());
	}
	
	/**
	 * 
	 */
	public void moveToStart() {
		setPosition(game.moveToStart().get());
		playMoveSound();
	}
	
	/**
	 * 
	 */
	public void moveToEnd() {
		setPosition(game.moveToEnd().get());
		playMoveSound();
	}
	
	/**
	 * 
	 */
	public void moveForward() {
		if (game.hasNext()) {
			setPosition(game.moveForward().get());
			playMoveSound();
		}
		else {
			//Make sound
		}
	}
	
	/**
	 * 
	 */
	public void moveBackward() {
		if (game.hasPrevious()) {
			setPosition(game.moveBackward().get());
			playMoveSound();
		}
		else {
			//Make sound
		}
	}

    /**
     *
     */
	public void copyGameToClipboard() {
	    String gameText = game.toMovesPgn();
        ClipboardReadWrite.writeText(gameText);

    }
	
	/**
	 * 
	 * @param enabled
	 */
	public void setAutoPromotion(boolean enabled) {
		this.autoPromotionEnabled = enabled;
	}
	
	/**
	 * 
	 * @return
	 */
	private Dimension getSquareSize() {
		Rectangle boardRect = getBoardRect();
		return new Dimension(boardRect.width/8, boardRect.height/8);
	}

	/**
	 * 
	 * @param squareCoords
	 * @return
	 */
	private Point getSquareLocation(ChessCoords squareCoords) {
		Dimension squareSize = getSquareSize();
        Rectangle boardRect = getBoardRect();
		return new Point(boardRect.x + squareCoords.getFile()*squareSize.width,
            boardRect.y + squareCoords.getRank()*squareSize.height);
	}
	
	/**
	 * 
	 * @param squareLocation
	 * @return
	 */
	private ChessCoords getSquareCoords(Point squareLocation) {
		Dimension squareSize = getSquareSize();
        Rectangle boardRect = getBoardRect();
		int rank = (int)((squareLocation.y - boardRect.y) / squareSize.getHeight());
		int file = (int)((squareLocation.x - boardRect.x) / squareSize.getWidth());
		rank = Math.max(Math.min(rank, 7), 0);
		file = Math.max(Math.min(file, 7), 0);
		return new ChessCoords(rank, file);
	}
	
	
	/**
	 * Transforms position coordinates to board coordinates.
	 * @param posCoords
	 * @return
	 */
	private ChessCoords getSquareCoords(ChessCoords posCoords) {
		
		if (flipped) {
			return new ChessCoords(posCoords.getRank(), 7-posCoords.getFile());
		}
		else {
			return new ChessCoords(7-posCoords.getRank(), posCoords.getFile());
		}
	}
	
	/**
	 * 
	 * @param squareCoords
	 * @return
	 */
	private ChessCoords getPosCoords(ChessCoords squareCoords) {
		return getSquareCoords(squareCoords); //reverse transform is same.
	}
	
	
	/**
	 * 
	 * @return
	 */
	private Rectangle getBoardRect() {
	    
		Insets insets = getInsets();

	    int x = insets.left;
	    int y = insets.top;
	    int width = getWidth() - insets.right;
	    int height = getHeight() - insets.bottom;
	    
	    //If component size doesn't divide well by 8, there is out of board area.
	    width -= width % 8;
	    height -= height % 8;
        int size = Math.min(width, height);

        if (width > size) {
            x += (width - size) / 2;
        }
        if (height > size) {
            y += (height - size) / 2;
        }
	    
	    return new Rectangle(x, y, size, size);
	}
	
	/**
	 * 
	 */
	private void playMoveSound() 
	{
	    try
	    {
	        final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));

	        clip.addLineListener(new LineListener()
	        {
	            @Override
	            public void update(LineEvent event)
	            {
	                if (event.getType() == LineEvent.Type.STOP)
	                    clip.close();
	            }
	        });

	        clip.open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(moveSound)));
	        clip.start();
	    }
	    catch (Exception e) {
	        e.printStackTrace(System.err);
	    }
	}
	
	/**
	 * 
	 * @param piece
	 * @param mouseLocation
	 */
	protected void reportPieceMovement(JPiece piece, Point mouseLocation) {
		setComponentZOrder(piece, 0); //Moving piece is always on front
	}
	
	/**
	 * 
	 * @param piece
	 */
	protected void reportPieceMovementDone(JPiece piece, Point mouseLocation) {
		ChessCoords oldPosCoords = piece.getPosCoords();
		ChessCoords oldSquareCoords = getSquareCoords(oldPosCoords);
		ChessCoords newSquareCoords = getSquareCoords(mouseLocation);
		ChessCoords newPosCoords = getPosCoords(newSquareCoords);
		
		boolean isValidMove = true;
		
		//Validating piece was not being taken out the board.
		if (newSquareCoords.getFile() < 0 || newSquareCoords.getRank() < 0 || newSquareCoords.getFile() > 7 || newSquareCoords.getRank() > 7) {
			isValidMove = false;
		}
		
		ChessMove chessMove = new ChessMove(oldPosCoords, newPosCoords);
			
		//Promotion choice
		ChessPiece chessPiece = game.get().getPiece(oldPosCoords);
		if (chessPiece != null && chessPiece.getType() == PieceType.PAWN) {
			PieceColor pieceColor = chessPiece.getColor();
			int dstRank = newPosCoords.getRank();
			if ((pieceColor == PieceColor.WHITE && dstRank == 7) || (pieceColor == PieceColor.BLACK && dstRank == 0)) {
				//if (autoPromotionEnabled) {
					chessMove.setPromotion(PieceType.QUEEN);
				//} 
			}
		}
		
		ChessPosition newPosition = game.makeMove(chessMove);
		
		if (newPosition != null) {
			piece.setLocation(getSquareLocation(newSquareCoords));
			piece.setPosCoords(newPosCoords);
			
			//Doing changes on board according to delta
			ChessPositionDelta positionDelta = newPosition.getDelta();
			for (ChessPiece disposedPiece : positionDelta.disposedPieces) {
				disposePiece(disposedPiece.getLocation());
			}
			
			for (ChessPiece addedPiece : positionDelta.addedPieces) {
				addPiece(new JPiece(addedPiece, addedPiece.getLocation(), this));
			}
			
			for (ChessPiece movedPiece : positionDelta.movedPieces) {
				movePiece(movedPiece.getPreviousLocation(), movedPiece.getLocation());
			}
			
			repaint();
			playMoveSound();
		}
		else {
			piece.setLocation(getSquareLocation(oldSquareCoords));
		}
	}
	
	/**
	 * 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Rectangle boardRect = getBoardRect();
		g.clipRect(boardRect.x, boardRect.y, boardRect.width, boardRect.height);
		boardPainter.paint(g, boardRect, true);	
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * When board is being resized, resize and move all pieces on it accordingly.
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		for (Component piece : getComponents()) {
			ChessCoords posCoords = ((JPiece)piece).getPosCoords();
			ChessCoords squareCoords = getSquareCoords(posCoords);
			piece.setSize(getSquareSize());
			piece.setLocation(getSquareLocation(squareCoords));
		}
	}
}
