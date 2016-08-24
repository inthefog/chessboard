package com.inthefog.chessboard.model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class ChessPositionDelta {
	
	public List<ChessPiece> disposedPieces = new ArrayList<>();
	public List<ChessPiece> addedPieces = new ArrayList<>();
	public List<ChessPiece> movedPieces = new ArrayList<>();
	
	/**
	 * 
	 */
	public void clear() {
		disposedPieces.clear();
		addedPieces.clear();
		movedPieces.clear();
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessPositionDelta createReverted() {
		ChessPositionDelta reverted = new ChessPositionDelta();
		reverted.disposedPieces.addAll(addedPieces);
		reverted.addedPieces.addAll(disposedPieces);
		
		try {
			for (ChessPiece piece : movedPieces) {
				reverted.movedPieces.add(piece.getClass().getConstructor(PieceType.class, PieceColor.class).newInstance(piece.getType(), piece.getColor()).setLocation(piece.getPreviousLocation()));
			} 
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				
		}
		
		return reverted;
	}
	
}
