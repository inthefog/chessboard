package com.inthefog.chessboard.model.types;

import com.inthefog.chessboard.exceptions.InvalidAlgebraicException;

public enum PieceType {
	PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;

    /**
     *
     * @param c
     * @return
     * @throws InvalidAlgebraicException
     */
	public static PieceType fromAlgebraic(char c) throws InvalidAlgebraicException {
        if (c == 'B') {
            return PieceType.BISHOP;
        } else if (c == 'N') {
            return PieceType.KNIGHT;
        } else if (c == 'R') {
            return PieceType.ROOK;
        } else if (c == 'Q') {
            return PieceType.QUEEN;
        } else if (c == 'K') {
            return PieceType.KING;
        } else {
            throw new InvalidAlgebraicException(String.format("Piece %c is not supported by algebraic notation!", c));
        }
    }

    /**
     *
     * @param p
     * @return
     */
    public static char toAlgebraic(PieceType p) {
        if (p == PieceType.BISHOP) {
            return 'B';
        } else if (p == PieceType.KNIGHT) {
            return 'N';
        } else if (p == PieceType.ROOK) {
            return 'R';
        } else if (p == PieceType.QUEEN) {
            return 'Q';
        } else {
            return 'K';
        }
    }
}
