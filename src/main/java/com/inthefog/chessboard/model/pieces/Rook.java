package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class Rook extends ChessPiece {
	
	/**
	 * 
	 * @param color
	 * @param loc
	 */
	public Rook(PieceColor color, ChessCoords loc) {
		super(PieceType.ROOK, color, loc);
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidMove(ChessCoords dst) {

		if (loc == null || loc.compareTo(dst)) {
			return false;
		}
		
		if (dst.getRank() != loc.getRank() && dst.getFile() != loc.getFile()) {
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