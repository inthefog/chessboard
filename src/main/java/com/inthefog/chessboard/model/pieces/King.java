package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends ChessPiece {

    private final ChessCoords startPos;

	/**
	 * 
	 * @param color
	 * @param loc
	 */
	public King(PieceColor color, ChessCoords loc) {
	    super(PieceType.KING, color, loc);
        startPos = (color == PieceColor.WHITE) ? new ChessCoords(0, 'e') : new ChessCoords(7, 'e');
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
        if (loc.compareTo(startPos)) {
            moves.add(new ChessMove(loc, loc.incr(0, 2)));
            moves.add(new ChessMove(loc, loc.incr(0, -2)));
        }

        for (int rankStep = -1; rankStep <= 1; rankStep++) {
            for (int fileStep = -1; fileStep <=1; fileStep++) {
                if (rankStep == 0 && fileStep == 0) {
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
