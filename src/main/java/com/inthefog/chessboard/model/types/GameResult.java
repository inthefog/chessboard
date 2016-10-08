package com.inthefog.chessboard.model.types;

/**
 * Created by pavel on 01/09/2016.
 */
public enum GameResult {
    UNKNOWN, DRAW, WHITE_WON, BLACK_WON;

    /**
     *
     * @param s
     * @return
     */
    public static GameResult fromAlgebraic(String s) {
        if ("1/2-1/2".equals(s)) {
            return DRAW;
        } else if ("1-0".equals(s)) {
            return WHITE_WON;
        } else if ("0-1".equals(s)) {
            return BLACK_WON;
        } else {
            return GameResult.UNKNOWN;
        }
    }

    /**
     *
     * @param result
     * @return
     */
    public static String toAlgebraic(GameResult result) {
        if (result == DRAW) {
            return "1/2-1/2";
        } else if (result == WHITE_WON) {
            return "1-0";
        } else if (result == BLACK_WON) {
            return "0-1";
        } else {
            return "";
        }
    }
}
