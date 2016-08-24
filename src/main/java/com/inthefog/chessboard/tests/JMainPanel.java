package com.inthefog.chessboard.tests;

import com.inthefog.chessboard.view.helpers.GridBagConstraintsFiller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pkogan on 5/20/16.
 */
public class JMainPanel extends JPanel {

    private static int BOARD_COLUMNS_NUM = 2;

    /**
     *
     * @param board
     * @param others
     */
    public JMainPanel(JComponent board, JComponent... others) {
        super(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        add(board, GridBagConstraintsFiller.newBuilder()
                .setGridWidth(BOARD_COLUMNS_NUM)
                .setGridHeight(others.length)
                .setWeightx(1.0)
                .setWeighty(1.0)
                .setInsets(new Insets(10, 10, 0, 0))
                .build().getConstraints()
        );

        int i = 0;
        for (JComponent other : others) {
            add(other, GridBagConstraintsFiller.newBuilder()
                    .setGridx(BOARD_COLUMNS_NUM + 1)
                    .setGridy(++i)
                    .setWeightx(1.0 / BOARD_COLUMNS_NUM)
                    .setWeighty(1.0 / others.length)
                    .setInsets(new Insets(10, 10, 10, 10))
                    .build().getConstraints()
            );
        }
    }




}
