package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		for (int rankStep = -2; rankStep <= 2; rankStep++) {
		    for (int fileStep = -2; fileStep <= 2; fileStep++) {
		        if (Math.abs(rankStep) + Math.abs(fileStep) != 3) {
		            continue;
                }

                ChessCoords dst = loc.incr(rankStep, fileStep);
                if (dst.inRange()) {
                    moves.add(new ChessMove(loc, dst));
                }
            }
        }

		return moves;
	}
}
