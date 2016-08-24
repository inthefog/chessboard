package com.inthefog.chessboard.view.helpers;

import com.inthefog.chessboard.view.JBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by pavel on 24/08/2016.
 */
public class JBoardActions {
    private final JBoard board;
    private static final String MOVE_FORWARD = "MoveForward";
    private static final String MOVE_BACKWARD = "MoveBackward";
    private static final String MOVE_TO_END = "MoveToEnd";
    private static final String MOVE_TO_START = "MoveToStart";

    public JBoardActions(JBoard board) {
        this.board = board;
        board.getActionMap().put(MOVE_FORWARD, new AbstractAction(MOVE_FORWARD) {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveForward();
            }
        });
        board.getActionMap().put(MOVE_BACKWARD, new AbstractAction(MOVE_BACKWARD) {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveBackward();
            }
        });
        board.getActionMap().put(MOVE_TO_START, new AbstractAction(MOVE_TO_START) {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveToStart();
            }
        });
        board.getActionMap().put(MOVE_TO_END, new AbstractAction(MOVE_TO_END) {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.moveToEnd();
            }
        });

        board.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), MOVE_FORWARD);
        board.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), MOVE_BACKWARD);
        board.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), MOVE_TO_END);
        board.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), MOVE_TO_START);
    }
}
