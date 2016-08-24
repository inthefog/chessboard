package com.inthefog.chessboard.exceptions;

@SuppressWarnings("serial")
public class InvalidPieceException extends Exception {
	
	/**
	 * 
	 * @param message
	 */
	public InvalidPieceException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param e
	 */
	public InvalidPieceException(String message, Throwable e) {
		super(message, e);
	}
}
