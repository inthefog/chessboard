package com.inthefog.chessboard.formats;

import com.google.common.collect.Lists;
import com.inthefog.chessboard.exceptions.InvalidPgnException;
import com.inthefog.chessboard.model.ChessGame;
import com.inthefog.chessboard.model.ChessMove;
import com.inthefog.chessboard.model.ChessPosition;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by pkogan on 9/17/15.
 */
public class PgnReader implements Closeable {

    BufferedReader br = null;
    String line = null;
    Pattern headerPattern = Pattern.compile("^\\[(.*)\\s+\\\"(.*)\\\"\\]");

    /**
     *
     * @param pgnFilepath
     * @throws IOException
     */
    public PgnReader(String pgnFilepath) throws IOException {
        close();
        br = new BufferedReader(new FileReader(pgnFilepath));
        line = br.readLine();
    }

    /**
     * @throws IOException
     */
    public void close() throws IOException {
        if (br != null) {
            br.close();
            br = null;
        }
    }

    /**
     *
     * @return
     */
    public ChessGame readNext() {

        ChessGame game = new ChessGame(new ChessPosition().setStartPosition());

        try {
            //Skip empty lines
            while (line != null && line.isEmpty()) {
                line = br.readLine();
            }

            //End of file
            if (line == null) {
                return null;
            }

            //Read header
            while (line != null && !line.isEmpty()) {
                if (line.startsWith("[")) {
                    //Header line
                    Matcher m = headerPattern.matcher(line);
                    m.find();
                    String key = m.group(1);
                    String val = m.group(2);
                    game.setProperty(key, val);
                }
                line = br.readLine();
            }

            //End of file
            if (line == null) {
                throw new InvalidPgnException(String.format("Game %s without moves!", game));
            }

            //Skip empty lines
            while (line.isEmpty()) {
                line = br.readLine();
            }

            if (line.startsWith("[")) {
                throw new InvalidPgnException(String.format("Game %s without moves!", game));
            }

            StringBuilder movesBuilder = new StringBuilder();
            //Read moves
            while (line != null && !line.isEmpty()) {
                movesBuilder.append(line);
                movesBuilder.append(' ');
                line = br.readLine();
            }

            String startPosition = game.getProperty("FEN");
            if (startPosition != null) {
                game.get().setPosition(startPosition);
            }

            parseMoves(movesBuilder.toString(), game);
            return game;
        }
        catch (Exception e) {
            return game.setParseError(e.getMessage());
            //throw new InvalidPgnException(String.format("Failed to parse game %s", game.toString()), e);
        }
    }

    /**
     *
     * @param movesLine
     * @param game
     * @throws InvalidPgnException
     */
    private void parseMoves(String movesLine, ChessGame game) throws InvalidPgnException {
        movesLine = removeComments(movesLine);
        movesLine = removeVariants(movesLine);

        // Chessbase contains games with missing moves replaced with --
        if (movesLine.contains("--")) {
            throw new InvalidPgnException("Missing move");
        }

        List<String> moves = Lists.newArrayList(movesLine.split(" "));
        moves = moves.stream().filter(s -> !s.isEmpty() && Character.isLetter(s.charAt(0))).
                collect(Collectors.toList());

        for (String move : moves) {
            ChessMove chessMove = game.get().parseAlgebraic(move);
            if (chessMove != null) {
                game.makeMove(chessMove);
            } else {
                throw new InvalidPgnException(String.format("Invalid move %s!", move));
            }
        }
    }

    /**
     * Removing comments which resides between curly brackets.
     * Note: comments can not be recursive.
     * @param movesLine
     * @return
     * @throws InvalidPgnException
     */
    private String removeComments(String movesLine) throws InvalidPgnException {
        if (movesLine == null) {
            return null;
        }

        while (true) {
            int commentStart = movesLine.indexOf('{');
            if (commentStart == -1) {
                break;
            }
            int commentEnd = movesLine.indexOf('}', commentStart);
            if (commentEnd == -1) {
                throw new InvalidPgnException(String.format("Invalid comment starting char %d!", commentStart));
            }
            movesLine = movesLine.replace(movesLine.substring(commentStart, commentEnd + 1), "");
        }

        return movesLine;
    }

    /**
     * Removing variants which resides between parentheses.
     * Note: variants can be recursive.
     * @param movesLine
     * @return
     * @throws InvalidPgnException
     */
    private String removeVariants(String movesLine) throws InvalidPgnException {
        if (movesLine == null) {
            return movesLine;
        }

        while (true) {
            int counter = 0;
            int variantStart = -1;
            int variantEnd = -1;
            for (int i = 0; i < movesLine.length(); i++) {
                char c = movesLine.charAt(i);
                if (c == '(') {
                    if (counter++ == 0) {
                        variantStart = i;
                    }
                }
                else if (c == ')') {
                    if (--counter == 0) {
                        variantEnd = i;
                        break;
                    }
                }
            }

            if (variantStart == -1) {
                break;
            }

            if (variantEnd == -1) {
                throw new InvalidPgnException(String.format("Invalid variant starting char %d!", variantStart));
            }

            movesLine = movesLine.replace(movesLine.substring(variantStart, variantEnd + 1), "");
        }

        return movesLine;
    }
}
