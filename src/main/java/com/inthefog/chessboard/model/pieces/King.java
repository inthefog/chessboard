package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class King extends ChessPiece {
	
	/**
	 * 
	 * @param color
	 * @param loc
	 */
	public King(PieceColor color, ChessCoords loc) {
		super(PieceType.KING, color, loc);
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidMove(ChessCoords dst) {
		
		if (loc == null || loc.compareTo(dst)) {
			return false;
		}
		
		//Doesn't cover castling
		if (Math.abs(dst.getRank() - loc.getRank()) > 1 || Math.abs(dst.getFile() - loc.getFile()) > 1) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isValidTake(ChessCoords dst) {
		return isValidMove(dst);
	}
}
