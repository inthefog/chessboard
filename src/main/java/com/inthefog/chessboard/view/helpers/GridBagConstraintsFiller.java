package com.inthefog.chessboard.view.helpers;

import org.inferred.freebuilder.FreeBuilder;

import java.awt.*;

/**
 * Created by pkogan on 5/22/16.
 */
@FreeBuilder
public abstract class GridBagConstraintsFiller {
    public abstract int getGridx();
    public abstract int getGridy();
    public abstract int getGridWidth();
    public abstract int getGridHeight();
    public abstract int getFill();
    public abstract double getWeightx();
    public abstract double getWeighty();
    public abstract int getAnchor();
    public abstract Insets getInsets();
    public abstract int getInPadx();
    public abstract int getInPady();

    /** Builder. */
    public static class Builder extends GridBagConstraintsFiller_Builder {
        public Builder() {
            setGridx(1);
            setGridy(1);
            setGridWidth(1);
            setGridHeight(1);
            setFill(GridBagConstraints.BOTH);
            setWeightx(0.0);
            setWeighty(0.0);
            setAnchor(GridBagConstraints.CENTER);
            setInsets(new Insets(0, 0, 0, 0));
            setInPadx(0);
            setInPady(0);
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * @return
     */
    public GridBagConstraints getConstraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = getGridx();
        c.gridy = getGridy();
        c.gridwidth = getGridWidth();
        c.gridheight = getGridHeight();
        c.fill = getFill();
        c.weightx = getWeightx();
        c.weighty = getWeighty();
        c.anchor = getAnchor();
        c.insets = getInsets();
        c.ipadx = getInPadx();
        c.ipady = getInPady();
        return c;
    }
}
