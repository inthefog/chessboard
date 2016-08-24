package com.inthefog.chessboard.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pkogan on 5/13/16.
 */
public class JNotation extends JComponent {

    private final JTextArea textArea;

    public JNotation() {
        super();

        textArea = new JTextArea("aaa", 100, 100);
        //JScrollPane scroll = new JScrollPane(textArea);
        this.setBackground(Color.blue);
        //this.add(scroll);
        this.setVisible(true);
    }
}
