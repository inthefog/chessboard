package com.inthefog.chessboard.model;

import com.inthefog.chessboard.model.types.PieceType;

public class ChessMove {
	
	private ChessCoords src = null;
	private ChessCoords dst = null;
	private PieceType promotion = null;

	/**
	 * 
	 */
	public ChessMove() {
		
	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 */
	public ChessMove(ChessCoords src, ChessCoords dst) {
		this.src = src;
		this.dst = dst;
	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 * @param promotion
	 */
	public ChessMove(ChessCoords src, ChessCoords dst, PieceType promotion) {
		this.src = src;
		this.dst = dst;
		this.promotion = promotion;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessCoords getSrc() {
        return src;
	}

    /**
     *
     * @param src
     */
	public ChessMove setSrc(ChessCoords src) {
        this.src = src;
        return this;
    }
	
	/**
	 * 
	 * @return
	 */
	public ChessCoords getDst() {
        return dst;
	}

    /**
     *
     * @param dst
     */
    public ChessMove setDst (ChessCoords dst) {
        this.dst = dst;
        return this;
    }
	
	/**
	 * 
	 * @return
	 */
	public PieceType getPromotion() {
        return promotion;
	}
	
	/**
	 * 
	 * @param promotion
	 */
	public ChessMove setPromotion(PieceType promotion) {
        this.promotion = promotion;
        return this;
	}

    /**
     *
     * @return
     */
    @Override
	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(src.toString());
        sb.append(dst.toString());

        if (promotion != null) {
            sb.append(PieceType.toAlgebraic(promotion));
        }
        return sb.toString();
    }
}
