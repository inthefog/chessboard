package com.inthefog.chessboard.view;

import com.inthefog.chessboard.view.helpers.GridBagConstraintsFiller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pkogan on 5/20/16.
 */
public class JClock extends JPanel{

    public JClock() {
        super(new GridBagLayout());
        J7Segment seg = new J7Segment().setValue(9);
        seg.setOpaque(true);
        add(new J7Segment().setDecimal(true), GridBagConstraintsFiller.newBuilder()
            .setGridx(1)
            .setGridy(1)
            .setGridWidth(1)
            .setGridHeight(2)
            .build().getConstraints()
        );
        /*add(new J7Segment().setDecimal(true));
        add(new J7Segment());
        add(new J7Segment().setDecimal(true));
        add(new J7Segment());
        add(new J7Segment());*/
    }
}
