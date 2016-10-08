package com.inthefog.chessboard.exceptions;

/**
 * Created by pavel on 01/09/2016.
 */
public class ClipboardException extends Exception {

    /**
     *
     * @param message
     */
    public ClipboardException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param e
     */
    public ClipboardException(String message, Throwable e) {
        super(message, e);
    }
}
