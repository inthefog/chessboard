package com.inthefog.chessboard.model.types;

public enum PieceColor {
	WHITE, BLACK;
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static PieceColor switchColor(PieceColor color) {
		return (color == PieceColor.BLACK) ? PieceColor.WHITE : PieceColor.BLACK;
	}
}
