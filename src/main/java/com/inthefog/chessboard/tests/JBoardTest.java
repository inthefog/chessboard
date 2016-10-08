package com.inthefog.chessboard.tests;

/**
 * Created by pkogan on 9/17/15.
 */

import com.inthefog.chessboard.model.ChessPosition;
import com.inthefog.chessboard.view.JBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.inthefog.chessboard.view.JClock;
import com.inthefog.chessboard.view.JNotation;

@SuppressWarnings("serial")
public class JBoardTest extends JFrame implements ActionListener {

    private JBoard board = null;
    private JNotation notation = null;
    private JMenuBar menuBar = null;
    private JMenu fileMenu = null;
    private JMenu boardMenu = null;
    private JMenuItem copyGameMenuItem = null;
    private JMenuItem pasteGameMenuItem = null;
    private JMenuItem startPosMenuItem = null;
    private JMenuItem flipMenuItem = null;
    private JMenuItem autoPromotionMenuItem = null;
    private JMenuItem clearMenuItem = null;
    private JMenuItem exitMenuItem = null;

    private static final int UI_WIDTH = 1200;
    private static final int UI_HEIGHT = 600;

    /**
     *
     * @param title
     */
    public JBoardTest(String title) {
        super(title);

        //setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMainPanel();
        setMenu();
        pack();
        setVisible(true);
    }

    /**
     *
     */
    private void setMainPanel() {

        board = new JBoard();
        board.setGame(new ChessPosition().setStartPosition());

        notation = new JNotation();

        JPanel mainPanel = new JMainPanel(board, new JClock(), notation);
        mainPanel.setPreferredSize(new Dimension(UI_WIDTH, UI_HEIGHT));
        add(mainPanel);
    }

    /**
     *
     */
    private void setMenu() {

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        boardMenu = new JMenu("Board");

        copyGameMenuItem = new JMenuItem("Copy Game", null);
        copyGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copyGameMenuItem.setToolTipText("Copy game to clipboard");
        copyGameMenuItem.addActionListener(this);

        pasteGameMenuItem = new JMenuItem("Paste Game", null);
        pasteGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        pasteGameMenuItem.setToolTipText("Paste game from clipboard");
        pasteGameMenuItem.addActionListener(this);

        startPosMenuItem = new JMenuItem("Start Position", null);
        startPosMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        startPosMenuItem.setToolTipText("Set start position");
        startPosMenuItem.addActionListener(this);

        flipMenuItem = new JMenuItem("Flip", null);
        flipMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
        flipMenuItem.setToolTipText("Flip board");
        flipMenuItem.addActionListener(this);

        autoPromotionMenuItem = new JCheckBoxMenuItem("Auto Promotion");
        autoPromotionMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        autoPromotionMenuItem.setToolTipText("Auto promotion to queen");
        autoPromotionMenuItem.addActionListener(this);

        clearMenuItem = new JMenuItem("Clear", null);
        clearMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        clearMenuItem.setToolTipText("Clear board");
        clearMenuItem.addActionListener(this);

        exitMenuItem = new JMenuItem("Exit", null);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        exitMenuItem.setToolTipText("Exit board");
        exitMenuItem.addActionListener(this);

        boardMenu.add(copyGameMenuItem);
        boardMenu.add(pasteGameMenuItem);
        boardMenu.add(startPosMenuItem);
        boardMenu.add(flipMenuItem);
        boardMenu.add(autoPromotionMenuItem);
        boardMenu.add(clearMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(boardMenu);
        setJMenuBar(menuBar);
    }

    /**
     *
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitMenuItem) {
            System.exit(0);
        }
        else if (e.getSource() == copyGameMenuItem) {
            board.copyGameToClipboard();
        }
        else if (e.getSource() == pasteGameMenuItem) {
            board.pasteGameFromClipboard();
        }
        else if (e.getSource() == startPosMenuItem) {
            board.setGame(new ChessPosition().setStartPosition());
        }
        else if (e.getSource() == flipMenuItem) {
            board.flip();
        }
        else if (e.getSource() == autoPromotionMenuItem) {
            board.setAutoPromotion(((JCheckBoxMenuItem)e.getSource()).isSelected());
        }
		/*else if (e.getSource() == clearMenuItem) {
			board.clear();
		}*/
    }



    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new JBoardTest("Chessboard");
    }
}
