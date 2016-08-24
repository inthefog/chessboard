package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class Knight extends ChessPiece {
	
	/**
	 * 
	 * @param color
	 * @param loc
	 */
	public Knight(PieceColor color, ChessCoords loc) {
		super(PieceType.KNIGHT, color, loc);
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidMove(ChessCoords dst) {

		if (loc == null || loc.compareTo(dst)) {
			return false;
		}
		
		int deltaRank = dst.getRank()-loc.getRank();
		int deltaFile = dst.getFile()-loc.getFile();
		
		if (deltaRank == 0 || deltaFile == 0) {
			return false;
		}
		
		if (Math.abs(deltaFile) + Math.abs(deltaRank) != 3) {
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
