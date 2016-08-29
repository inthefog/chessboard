package com.inthefog.chessboard.model.pieces;

import com.inthefog.chessboard.model.ChessCoords;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPiece;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends ChessPiece {

    private final int rankStep;
    private final int prePromotionRank;
    private static final PieceType[] promotionPieces = new PieceType[] {
        PieceType.QUEEN, PieceType.ROOK, PieceType.BISHOP, PieceType.KNIGHT };

	/**
	 * 
	 * @param color
	 */
	public Pawn(PieceColor color, ChessCoords loc) {
	    super(PieceType.PAWN, color, loc);
        rankStep = (color == PieceColor.WHITE) ? 1 : -1;
        prePromotionRank = (color == PieceColor.WHITE) ? 6 : 1;
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
        for (int fileStep = -1; fileStep <= 1; fileStep++) {
            ChessCoords dst = loc.incr(rankStep, fileStep);

            if (!dst.inRange()) {
                continue;
            }

            if (loc.getRank() == prePromotionRank) {
                for (PieceType promotionPiece : promotionPieces) {
                    moves.add(new ChessMove(loc, dst, promotionPiece));
                }
            } else {
                moves.add(new ChessMove(loc, dst));
            }
        }

		return moves;
	}
}