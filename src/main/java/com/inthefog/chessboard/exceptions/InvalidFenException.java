package com.inthefog.chessboard.exceptions;

@SuppressWarnings("serial")
public class InvalidFenException extends Exception {
	
	/**
	 * 
	 * @param message
	 */
	public InvalidFenException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param e
	 */
	public InvalidFenException(String message, Throwable e) {
		super(message, e);
	}
}
