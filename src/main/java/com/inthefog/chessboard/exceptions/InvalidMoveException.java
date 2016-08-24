package com.inthefog.chessboard.exceptions;

/**
 * Created by pkogan on 9/23/15.
 */
public class InvalidMoveException extends Exception {

    /**
     *
     * @param message
     */
    public InvalidMoveException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param e
     */
    public InvalidMoveException(String message, Throwable e) {
        super(message, e);
    }
}
