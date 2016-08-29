package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bishop extends ChessPiece {
	
	/**
	 * 
	 * @param color
	 * @param loc
	 */
	public Bishop(PieceColor color, ChessCoords loc) {
		super(PieceType.BISHOP, color, loc);
	}

	/**
	 * 
	 */
	@Override
	public boolean isValidMove(ChessCoords dst) {
		
		if (loc == null || loc.compareTo(dst)) {
			return false;
		}
		
		if (Math.abs(dst.getRank()-loc.getRank()) != Math.abs(dst.getFile()-loc.getFile())) {
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

	/**
	 *
	 * @return
	 */
	@Override
	protected Collection<ChessMove> getAllMoves() {
		if (loc == null || !loc.inRange()) {
			return new ArrayList<>();
		}

		List<ChessMove> moves = new ArrayList<>();

        ChessCoords dst = new ChessCoords(loc);
        while (dst.incrInPlace(1, 1).inRange()) {
            moves.add(new ChessMove(loc, dst));
        }

        dst = new ChessCoords(loc);
        while (dst.incrInPlace(-1, 1).inRange()) {
            moves.add(new ChessMove(loc, dst));
        }

        dst = new ChessCoords(loc);
        while (dst.incrInPlace(1, -1).inRange()) {
            moves.add(new ChessMove(loc, dst));
        }

        dst = new ChessCoords(loc);
        while (dst.incrInPlace(-1, -1).inRange()) {
            moves.add(new ChessMove(loc, dst));
        }

		return moves;
	}
}

