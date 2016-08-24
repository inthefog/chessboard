package com.inthefog.chessboard.view.painters;

import java.awt.*;

/**
 * Created by pkogan on 5/24/16.
 */
public class SevenSegmentPainter {

    private int width = 0;
    private int height = 0;
    private VALUE value = VALUE.ZERO;

    private final static Color COLOR_OFF = Color.green.darker().darker().darker().darker();
    private final static Color COLOR_ON = Color.green.brighter().brighter().brighter().brighter().brighter();

    /**
     *
     * @param value
     * @return
     */
    public SevenSegmentPainter setValue(VALUE value) {
        this.value = value;
        return this;
    }
    /**
     *
     * @param g
     * @param rect
     */
    public void paint(Graphics g, Rectangle rect) {

        /*if (polygon == null || rect.width != width || rect.height != height) {
            this.polygon = createPolygon(rect.width, rect.height);
            this.width = rect.width;
            this.height = rect.height;
        }

        g.translate(rect.x, rect.y);
        g.setColor(pieceColor);
        g.fillPolygon(polygon);
        g.setColor(outlineColor);
        g.drawPolygon(polygon);
        drawOutline(g, rect.width, rect.height);
        g.translate(-rect.x, -rect.y);*/
    }

    /**
     *
     * @param width
     * @param height
     * @return
     */
    private Polygon[] createPolygons(int width, int height) {
        return null;
    }

    public enum VALUE {
        ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);

        VALUE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        private final int value;
    }
}
