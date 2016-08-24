package com.inthefog.chessboard.exceptions;

/**
 * Created by pkogan on 9/20/15.
 */
public class InvalidPgnException extends Exception {

    /**
     *
     * @param message
     */
    public InvalidPgnException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param e
     */
    public InvalidPgnException(String message, Throwable e) {
        super(message, e);
    }
}