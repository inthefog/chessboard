package com.inthefog.chessboard.model;

import com.inthefog.chessboard.model.types.GameResult;
import com.inthefog.chessboard.model.types.PieceColor;

import java.awt.*;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * @author pasha
 *
 */
public class ChessGame {

	private Map<String, String> props = new HashMap<>();
	private Node head = null;
	private Node tail = null;
	private Node iterator = null;
	private String parseError = null;
    private GameResult result = GameResult.UNKNOWN;

    /**
     *
     */
    public ChessGame() {
        this(null);
    }
	/**
	 * 
	 * @param startPosition
	 */
	public ChessGame(ChessPosition startPosition) {
		if (startPosition == null) {
			startPosition = new ChessPosition().setStartPosition();
		}

		iterator = new Node(startPosition, null, null);
		head = iterator;
		tail = iterator;
	}

	/**
	 *
	 * @param name
	 */
	public String getProperty(String name) {
		return props.get(name);
	}

	/**
	 *
	 * @param name
	 * @param value
	 */
	public ChessGame setProperty(String name, String value) {
		props.put(name, value);
		return this;
	}

    /**
     *
     * @return
     */
	public GameResult getResult() {return result;}

    /**
     *
     * @param result
     * @return
     */
	public ChessGame setResult(GameResult result) {
	    this.result = result;
        return this;
    }

    /**
     *
     * @param name
     * @return
     */
	public boolean hasProperty(String name) {
        return props.containsKey(name);
    }

	/**
	 *
	 * @return
	 */
	public List<String> listProperties() {
		return new ArrayList<String>(props.keySet());
	}

    /**
     * Returns all moves separated with semi-colon.
     * @return
     */
    public String toMoves() {
        StringBuilder sb = new StringBuilder();
        Node tmpIterator = head;
        while (tmpIterator.next != null) {
            sb.append(tmpIterator.next.move.toString());
            sb.append(' ');
            tmpIterator = tmpIterator.next;
        }
        return sb.toString();
    }

	/**
	 * 
	 * @return
	 */
	public ChessPosition get() {
		return (iterator == null) ? null : iterator.position;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessGame moveToStart() {
		iterator = head;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessGame moveToEnd() {
		iterator = tail;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNext() {
		return (iterator == null || iterator.next == null) ? false : true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasPrevious() {
		return (iterator == null || iterator.prev == null) ? false : true;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessGame moveForward() {
		if (hasNext()) {
			iterator = iterator.next;
		}
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessGame moveBackward() {
		if (hasPrevious()) {
			iterator = iterator.prev;
		}
		return this;
	}
 	
	/**
	 * 
	 * @param move
	 * @return
	 */
	public ChessPosition makeMove(ChessMove move) {
		if (move == null || iterator == null || !iterator.position.validateMove(move)) {
			return null;
		}

		Node nextNode = new Node(iterator.position.forkPosition(move), null, iterator).setMove(move);
        if (iterator == null) {
            nextNode.setMoveId(1);
        } else if (iterator.position.getTurn() == PieceColor.WHITE) {
            nextNode.setMoveId(iterator.moveId+1);
        } else {
            nextNode.setMoveId(iterator.moveId);
        }
		
		iterator.next = nextNode;
		iterator = iterator.next;
		tail = iterator;
		return iterator.position;
	}

	@Override
	public String toString() {
		return String.format("%s - %s (%s)", getProperty("White"), getProperty("Black"), getProperty("Date"));
	}

	/**
	 *
	 * @param parseError
	 * @return
	 */
	public ChessGame setParseError(String parseError) {
		this.parseError = parseError;
		return this;
	}

	/**
	 *
	 * @return
	 */
	public String getParseError() {
		return parseError;
	}

    /**
     *
     * @return
     */
    public String toPgn() {
        StringBuilder sb = new StringBuilder();

        for (String propName : props.keySet()) {
            if (propName.equals("Result") && result != GameResult.UNKNOWN) {
                continue;
            }
            String propValue = props.get(propName);
            sb.append(String.format("%s \"%s\"\n", propName, propValue));
        }

        if (result != GameResult.UNKNOWN) {
            sb.append(String.format("%s \"%s\"\n", "Result", GameResult.toAlgebraic(result)));
        }

        sb.append("\n");

        Node tmpIterator = head;
        while (tmpIterator.next != null) {
            if (tmpIterator.prev == null || tmpIterator.position.getTurn() == PieceColor.WHITE) {
                sb.append(tmpIterator.next.moveId);
                sb.append(tmpIterator.position.getTurn() == PieceColor.WHITE ? ". " : "... ");
            }
            sb.append(tmpIterator.position.createAlgebraic(tmpIterator.next.move));
            sb.append(' ');
            tmpIterator = tmpIterator.next;
        }

        return sb.append(GameResult.toAlgebraic(result)).append("\n").toString();
    }

    public static ChessGame fromPgn(Reader reader) {
        BufferedReader br = new BufferedReader(reader);

    }

	/**
	 * 
	 * @author pasha
	 *
	 */
	static class Node {
		
		public ChessPosition position = null;
		public ChessMove move = null;
        public int moveId = 0;
		public Node next = null;
		public Node prev = null;
		
		/**
		 * 
		 */
		public Node() {
			
		}
		
		/**
		 * 
		 * @param position
		 * @param next
		 * @param prev
		 */
		public Node(ChessPosition position, Node next, Node prev) {
			this.position = position;
			this.next = next;
			this.prev = prev;
		}

		/**
		 *
		 * @param move
		 * @return
		 */
		public Node setMove(ChessMove move) {
			this.move = move;
			return this;
		}

        /**
         *
         * @param moveId
         * @return
         */
        public Node setMoveId(int moveId) {
            this.moveId = moveId;
            return this;
        }
	}	
}
