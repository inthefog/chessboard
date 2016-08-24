package com.inthefog.chessboard.tests;

import com.inthefog.chessboard.formats.PgnReader;
import com.inthefog.chessboard.model.ChessGame;

/**
 * Created by pkogan on 9/17/15.
 */
public class PgnReaderTest {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String pgnFile = "/Users/pkogan/Chessbase2012Export/1977_87.pgn";

        try (PgnReader pgnReader = new PgnReader(pgnFile)) {
            long tStart = System.currentTimeMillis();
            System.out.println(String.format("Parsing games from %s ... ", pgnFile));

            ChessGame game = null;
            int gamesCount=0;
            int failuresCount = 0;
            while ((game = pgnReader.readNext()) != null) {
                if (++gamesCount % 1000 == 0) {
                    System.out.println(String.valueOf(gamesCount));
                }

                if (game.getParseError() != null) {
                    System.err.println(String.format("Failed to parse game %d.[%s] - %s!", gamesCount, game, game.getParseError()));
                    failuresCount++;
                }
            }

            long tEnd = System.currentTimeMillis();
            System.out.println(String.format("Parsed %d games in %d seconds", gamesCount, (tEnd - tStart)/1000));
            System.err.println(String.format("Failed to parse %d games", failuresCount));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
