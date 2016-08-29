package com.inthefog.chessboard.model;

import com.inthefog.chessboard.model.pieces.Bishop;
import com.inthefog.chessboard.model.pieces.King;
import com.inthefog.chessboard.model.pieces.Knight;
import com.inthefog.chessboard.model.pieces.Pawn;
import com.inthefog.chessboard.model.pieces.Queen;
import com.inthefog.chessboard.model.pieces.Rook;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

import java.util.Collection;

public abstract class ChessPiece {
	
	protected PieceType type = PieceType.PAWN;
	protected PieceColor color = PieceColor.WHITE;
	protected ChessCoords previous_loc = null;
	protected ChessCoords loc = null;
	
	/**
	 * Factory
	 * @param type
	 * @param color
	 * @param loc
	 * @return
	 */
	public static ChessPiece create(PieceType type, PieceColor color, ChessCoords loc) {
		switch (type) {
			case PAWN:
				return new Pawn(color, loc);
			case KNIGHT:
				return new Knight(color, loc);
			case BISHOP:
				return new Bishop(color, loc);
			case ROOK:
				return new Rook(color, loc);
			case QUEEN:
				return new Queen(color, loc);
			case KING:
				return new King(color, loc);
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @param color
	 */
	protected ChessPiece(PieceType type, PieceColor color) {
		this.type = type;
		this.color = color;
	}
	
	/**
	 * 
	 * @param type
	 * @param color
	 * @param loc
	 */
	protected ChessPiece(PieceType type, PieceColor color, ChessCoords loc) {
		this.type = type;
		this.color = color;
		this.loc = loc;
	}
	
	/**
	 * 
	 * @return
	 */
	public PieceType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	public PieceColor getColor() {
		return color;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessCoords getLocation() {
		return loc;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessCoords getPreviousLocation() {
		return previous_loc;
	}
	
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public ChessPiece setLocation(ChessCoords loc) {
		this.loc = (loc == null) ? null : new ChessCoords(loc);
		return this;
	}
	
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public ChessPiece setPreviousLocation(ChessCoords loc) {
		this.previous_loc = loc;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public PieceColor getOppositeColor() {
		return (color == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
	}
	
	/**
	 * 
	 * @return
	 */
	public String toFen() {
		
		String fen = null;
		
		switch (type) {
			case PAWN:
				fen="p";
				break;
			case KNIGHT:
				fen="n";
				break;
			case BISHOP:
				fen="b";
				break;
			case ROOK:
				fen="r";
				break;
			case QUEEN:
				fen="q";
				break;
			case KING:
				fen="k";
				break;
		}
		
		if (color == PieceColor.WHITE)
			fen = fen.toUpperCase();
		
		return fen;
	}
	
	/**
	 * 
	 * @param dst
	 * @return
	 */
	protected abstract boolean isValidMove(ChessCoords dst);
	
	/**
	 * 
	 * @param dst
	 * @return
	 */
	protected abstract boolean isValidTake(ChessCoords dst);

    /**
     *
     * @return
     */
	protected abstract Collection<ChessMove> getAllMoves();
}
