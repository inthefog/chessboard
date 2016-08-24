package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class Pawn extends ChessPiece {
	
	/**
	 * 
	 * @param color
	 */
	public Pawn(PieceColor color, ChessCoords loc) {
		super(PieceType.PAWN, color, loc);
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidMove(ChessCoords dst) {

		if (loc == null) {
			return false; 
		}
		
		int srcRank = loc.getRank();
		int dstRank = dst.getRank();
		int srcFile = loc.getFile();
		int dstFile = dst.getFile();
		int deltaRank = dstRank - srcRank;
		int deltaFile = dstFile - srcFile;
		
		if (color == PieceColor.WHITE) {
			
			if (deltaFile != 0 || deltaRank > 2 || deltaRank < 1 || (deltaRank == 2 && srcRank != 1)) {
				return false;
			}
		} 
		else {
			
			if (deltaFile != 0 || deltaRank < -2 || deltaRank > -1 || (deltaRank == -2 && srcRank != 6)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidTake(ChessCoords dst) {
		
		if (loc == null) {
			return false; 
		}
		
		int srcRank = loc.getRank();
		int dstRank = dst.getRank();
		int srcFile = loc.getFile();
		int dstFile = dst.getFile();
		int deltaRank = dstRank - srcRank;
		int deltaFile = dstFile - srcFile;
		
		if (color == PieceColor.WHITE) {
			
			if (Math.abs(deltaFile) != 1 || deltaRank != 1) {
				return false;
			}
		} 
		else {
			
			if (Math.abs(deltaFile) != 1 || deltaRank != -1) {
				return false;
			}
		}
		
		return true;
	}
}