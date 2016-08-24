package com.inthefog.chessboard.model;

public class ChessCoords {
	private int file = 0;
	private int rank = 0;
	
	/**
	 * 
	 */
	public ChessCoords() {
		
	}
	
	/**
	 * 
	 * @param rank
	 * @param file
	 */
	public ChessCoords(int rank, int file) {
		this.file = file;
		this.rank = rank;
	}
	
	/**
	 * 
	 * @param rank
	 * @param file
	 */
	public ChessCoords (int rank, char file) {
		this.file = file - 'a';
		this.rank = rank;
	}
	
	/**
	 * 
	 * @param coords
	 */
	public ChessCoords(ChessCoords coords) {
		if (coords != null) {
			this.file = coords.file;
			this.rank = coords.rank;
		}
	}
	
	/**
	 * 
	 * @param notation
	 */
	public ChessCoords(String notation) {
		if (notation != null && notation.length() == 2) {
			notation = notation.toLowerCase();
			char fileNotation = notation.charAt(0);
			char rankNotation = notation.charAt(1);
			file = fileNotation - 'a';
			rank = rankNotation - '1';	
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean inRange() {
		return (rank<0 || rank>7 || file<0 || file>7) ? false : true;
	}
	
	/**
	 * 
	 * @param coords
	 * @return
	 */
	public boolean compareTo(ChessCoords coords) {
		if (coords == null) {
			return false;
		}
		
		return (coords.file == file && coords.rank == rank) ? true : false;
	}
	
	/**
	 * 
	 * @param rankStep
	 * @param fileStep
	 */
	public void incrInPlace(int rankStep, int fileStep) {
		this.rank += rankStep;
		this.file += fileStep;
	}
	
	/**
	 * 
	 * @param rankStep
	 * @param fileStep
	 * @return
	 */
	public ChessCoords incr(int rankStep, int fileStep) {
		return new ChessCoords(rank + rankStep, file + fileStep);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFile() {
		return file;
	}

    /**
     *
     * @return
     */
	public ChessCoords setFile(int file) {
        this.file = file;
        return this;
    }

	/**
	 * 
	 * @return
	 */
	public int getRank() {
		return rank;
	}

    /**
     *
     * @param rank
     * @return
     */
    public ChessCoords setRank(int rank) {
        this.rank = rank;
        return this;
    }
	
	@Override
	public String toString() {
		return new StringBuilder().append((char)('a' + file)).append((char)('1' + rank)).toString();
	}
}
