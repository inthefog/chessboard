package com.inthefog.chessboard.exceptions;

/**
 * Created by pkogan on 9/22/15.
 */
public class InvalidAlgebraicException extends Exception {

    /**
     *
     * @param message
     */
    public InvalidAlgebraicException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param e
     */
    public InvalidAlgebraicException(String message, Throwable e) {
        super(message, e);
    }
}
