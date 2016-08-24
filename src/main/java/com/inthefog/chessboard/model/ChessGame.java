package com.inthefog.chessboard.model;

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
     * Return all game moves in ChessDb notation.
     * @return
     */
    public String getAllMovesString() {
        StringBuilder sb = new StringBuilder();
        for (Node tmpIterator=head.next; tmpIterator!= null; tmpIterator=tmpIterator.next) {
            sb.append(tmpIterator.move.toString());
            sb.append(';');
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
		
		iterator.next = new Node(iterator.position.forkPosition(move), null, iterator).setMove(move);
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
	 * @author pasha
	 *
	 */
	static class Node {
		
		public ChessPosition position = null;
		public ChessMove move = null;
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
	}	
}
