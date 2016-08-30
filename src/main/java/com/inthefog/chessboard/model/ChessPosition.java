package com.inthefog.chessboard.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.inthefog.chessboard.exceptions.InvalidAlgebraicException;
import com.inthefog.chessboard.exceptions.InvalidFenException;
import com.inthefog.chessboard.exceptions.InvalidMoveException;
import com.inthefog.chessboard.model.pieces.Bishop;
import com.inthefog.chessboard.model.pieces.King;
import com.inthefog.chessboard.model.pieces.Knight;
import com.inthefog.chessboard.model.pieces.Pawn;
import com.inthefog.chessboard.model.pieces.Queen;
import com.inthefog.chessboard.model.pieces.Rook;
import com.inthefog.chessboard.model.types.PieceColor;
import com.inthefog.chessboard.model.types.PieceType;

public class ChessPosition {
	
	private ChessPiece[][] position = new ChessPiece[8][8];
	private ChessCoords enPassant = null;
	private PieceColor turn = PieceColor.WHITE;
	private boolean whiteCanCastleKingside = true; //castle availability 
	private boolean whiteCanCastleQueenside = true; //castle availability 
	private boolean blackCanCastleKingside = true; //castle availability
	private boolean blackCanCastleQueenside = true; //castle availability
	private ChessPositionDelta delta = null; //delta from previous position
	private Multimap<PieceType, ChessPiece> whitePieces = ArrayListMultimap.create();
    private Multimap<PieceType, ChessPiece> blackPieces = ArrayListMultimap.create();
	
	/**
	 * 
	 */
	public ChessPosition() {
		
	}
	
	/**
	 * 
	 * @param fen
	 * @throws InvalidFenException
	 */
	public ChessPosition(String fen) throws InvalidFenException {
		setPosition(fen);
	}
	
	/**
	 * 
	 * @param position
	 * @throws InvalidFenException 
	 */
	public ChessPosition(ChessPosition position) {
		if (position != null) {
			try {
				setPosition(position.toString());
			} 
			catch (InvalidFenException e) {

			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessPosition setStartPosition() {
		
		try {
			setPosition("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
		} 
		catch (InvalidFenException e) {
			
		}
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessPositionDelta getDelta() {
		return delta;
	}
	
	/**
	 * 
	 * @return
	 */
	public PieceColor getTurn() {
		return turn;
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessPosition clear() {
	
		for (int rank=0; rank<8; rank++) {
			for (int file=0; file<8; file++) {
				clearSquare(new ChessCoords(rank, file));
			}
		}

        whitePieces.clear();
        blackPieces.clear();
		
		enPassant = null;
		turn = PieceColor.WHITE;
		whiteCanCastleKingside = true; 
		whiteCanCastleQueenside = true;
		blackCanCastleKingside = true; 
		blackCanCastleQueenside = true;
		return this;
	}
	
	/**
	 * 
	 * @param piece
	 */
	private void addPiece(ChessPiece piece) {
		ChessCoords loc = piece.getLocation();
		position[loc.getRank()][loc.getFile()] = piece;
        getPieces(piece.getColor()).put(piece.getType(), piece);
	}
	
	/**
	 * 
	 * @param fen
	 * @param loc
	 * @throws InvalidFenException
	 */
	private void addPiece(char fen, ChessCoords loc) throws InvalidFenException {
		
		ChessPiece piece = null;
		PieceColor color = Character.isLowerCase(fen) ? PieceColor.BLACK : PieceColor.WHITE;
		
		switch (Character.toLowerCase(fen)) {
			case 'p':
				piece = new Pawn(color, loc);
				break;
			case 'n':
				piece = new Knight(color, loc);
				break;
			case 'b':
				piece = new Bishop(color, loc);
				break;
			case 'r':
				piece = new Rook(color, loc);
				break;
			case 'q':
				piece = new Queen(color, loc);
				break;
			case 'k':
				piece = new King(color, loc);
				break;
			default:
				throw new InvalidFenException(String.format("Invalid piece fen %s!", fen));
		}
		
		addPiece(piece);
	}
	
	/**
	 * 
	 * @param piece
	 * @param loc
	 */
	private void movePiece(ChessPiece piece, ChessCoords loc) {
		ChessCoords pieceLocation = piece.getLocation();
        deletePiece(piece);
		addPiece(piece.setLocation(loc).setPreviousLocation(pieceLocation));
	}
	
	/**
	 * 
	 * @param piece
	 */
	private void deletePiece(ChessPiece piece) {
		clearSquare(piece.getLocation());
        getPieces(piece.getColor()).remove(piece.getType(), piece);
	}
	
	/**
	 * 
	 * @return
	 */
	public ChessPosition switchTurn() {
		turn = PieceColor.switchColor(turn);
		return this;
	}
	
	/**
	 * 
	 * @param loc
	 * @return
	 */
	public ChessPiece getPiece(ChessCoords loc) {
		return (loc != null && loc.inRange()) ? position[loc.getRank()][loc.getFile()] : null;
	}
	
	/**
	 * 
	 * @param pieceType
	 * @param pieceColor
	 * @return
	 */
	public List<ChessPiece> findPieces(PieceType pieceType, PieceColor pieceColor) {
		return new ArrayList<>(getPieces(pieceColor).get(pieceType));
	}
	
	/**
	 * @param pieceColor
	 * @return
	 */
	public List<ChessPiece> findPieces(PieceColor pieceColor) {
        return new ArrayList<>(getPieces(pieceColor).values());
	}

    /**
     * Translates algebraic move to a <u>valid</u> chessboard move on board
     *
     * <p>Examples of input:
     * Be5 - move a bishop to e5
     * Nf3 - move a knight to f3
     * c5 - move a pawn to c5
     * Bxe5 - bishop makes a capture on e5
     * Nxf3 - knight makes a capture on f3
     * dxc5 - pawn makes a capture on c5 (same notation for en passant)
     * e8=Q - pawn promotes to queen
     * O-O - king side castling (uppercase O)
     * O-O-O - queen side castling (uppercase O)
     * Nc5+ - making check or double check.
     * Nd5# - making checkmate.
     * Disambiguating moves:
     * Nbd2 - if there are two knights on different files.
     * N4d2 - if there are two knights on same file but different ranks.
     * Nb1d2 - in rare cases of more than two of same piece (after promotion).</p>
     * @param s
     * @return
     */
    public ChessMove parseAlgebraic(String s) {
        try {
            s = s.trim();

            ChessMove move = new ChessMove();

            if (s.startsWith("O-O-O")) {
                move.setSrc((turn == PieceColor.WHITE) ? new ChessCoords("e1") : new ChessCoords("e8"));
                move.setDst((turn == PieceColor.WHITE) ? new ChessCoords("c1") : new ChessCoords("c8"));
            } else if (s.startsWith("O-O")) {
                move.setSrc((turn == PieceColor.WHITE) ? new ChessCoords("e1") : new ChessCoords("e8"));
                move.setDst((turn == PieceColor.WHITE) ? new ChessCoords("g1") : new ChessCoords("g8"));
            }
            else {
                PieceType pieceType = PieceType.PAWN;
                int i = s.length() - 1;
                while (i >= 0) {
                    char c = s.charAt(i);

                    if (c == '#' || c == '+' || c == ' ' || c == 'x') {
                        i--;
                    } else if (Character.isUpperCase(c)) {
                        if (i == 0) {
                            pieceType = PieceType.fromAlgebraic(c);
                            i--;
                        } else if (i > 0 && s.charAt(i - 1) == '=') {
                            //Promotion piece
                            move.setPromotion(PieceType.fromAlgebraic(c));
                            i -= 2;
                        } else {
                            throw new InvalidAlgebraicException("Invalid promotion assertion!");
                        }
                    } else if (Character.isDigit(c)) {
                        if (i > 0) {
                            if (i == 1 && Character.isUpperCase(s.charAt(0))) {
                                move.setSrc(new ChessCoords(c-'1', -1));
                                i--;
                            } else {
                                String square = s.substring(i - 1, i + 1);
                                if (move.getDst() == null) {
                                    move.setDst(new ChessCoords(square));
                                } else {
                                    move.setSrc(new ChessCoords(square));
                                }
                                i -= 2;
                            }
                        } else {
                            throw new InvalidAlgebraicException("Invalid destination square!");
                        }
                    } else if (Character.isLowerCase(c)) {
                        move.setSrc(new ChessCoords(-1, c));
                        i--;
                    }
                }

                if (move.getDst() == null) {
                    throw new InvalidAlgebraicException("Missing destination square!");
                }

                if (move.getSrc() == null) {
                    move.setSrc(new ChessCoords(-1, -1));
                }

                if (pieceType == PieceType.PAWN && move.getSrc().getFile() == -1) {
                    move.getSrc().setFile(move.getDst().getFile());
                }

                //Verifies source square which is not always provided by PGN format.
                //If missing, source square should be figured out hard way.
                if (move.getSrc().getRank() == -1 || move.getSrc().getFile() == -1) {
                    Collection<ChessPiece> pieces = getPieces(turn).get(pieceType);
                    if (pieces == null || pieces.isEmpty()) {
                        throw new InvalidMoveException("Piece is missing on board!");
                    }

                    for (ChessPiece piece : pieces) {
                        if (move.getSrc().getFile() != -1 &&
                                piece.getLocation().getFile() != move.getSrc().getFile()) {
                            continue;
                        }

                        if (move.getSrc().getRank() != -1 &&
                                piece.getLocation().getRank() != move.getSrc().getRank()) {
                            continue;
                        }

                        if (validateMove(new ChessMove(piece.getLocation(), move.getDst(), move.getPromotion()))) {
                            move.setSrc(piece.getLocation());
                            break;
                        }
                    }
                }
            }

            return validateMove(move) ? move : null;
        }
        catch (Exception e) {
            return null;
        }
    }

	/**
	 * Creates algebraic move from a chessboard move on board.
	 * This function validates the move with {@link #validateMove(ChessMove)}.
	 */
	public String createAlgebraic(ChessMove move) {
		try {
			if (!validateMove(move)) {
				return null;
			}

			ChessPosition newPosition = forkPosition(move);

			ChessCoords src = move.getSrc();
			ChessCoords dst = move.getDst();
			ChessPiece srcPiece = getPiece(src);
			ChessPiece dstPiece = getPiece(dst);
            PieceType promotionPiece = move.getPromotion();
			PieceType pieceType = srcPiece.getType();
			int srcRank = src.getRank();
			int dstRank = dst.getRank();
			int srcFile = src.getFile();
			int dstFile = dst.getFile();
			int deltaRank = dstRank - srcRank;
			int deltaFile = dstFile - srcFile;

			if (pieceType == PieceType.KING) {
				if (deltaRank == 0 && deltaFile == 2) {
					return "0-0";
				} else if (deltaRank == 0 && deltaFile == -2) {
					return "0-0-0";
				}
			}

			StringBuilder resultBuilder = new StringBuilder();

            if (pieceType == PieceType.PAWN) {
				if (srcFile != dstFile) {
					resultBuilder.append((char)(srcFile + 'a'));
				}
			} else {
				resultBuilder.append(PieceType.toAlgebraic(pieceType));
                Collection<ChessPiece> similarPieces = getPieces(getTurn()).get(pieceType);
                int validVariants = 0;
                for (ChessPiece piece : similarPieces) {
                    if (validateMove(new ChessMove(piece.getLocation(), dst))) {
                        validVariants += 1;
                    }
                }

                // Simplifying the original notation. If more than one piece can move to same destination square,
                // source square is asserted.
                if (validVariants > 1) {
                    resultBuilder.append(src.toString());
                }
			}

			if (dstPiece != null || (pieceType == PieceType.PAWN && srcFile != dstFile)) {
				resultBuilder.append('x');
			}

			resultBuilder.append(dst.toString());

            if (promotionPiece != null) {
                resultBuilder.append("=").append(PieceType.toAlgebraic(promotionPiece));
            }

            if (newPosition.kingChecked()) {
                resultBuilder.append(newPosition.hasValidMove() ? '+' : '#');
            }

			return resultBuilder.toString();
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * Generates new modified position object. 
	 * Move is not being validated. All validations should be done separately
	 * using: validateMove
	 * @param move
	 * @return
	 */
	public ChessPosition forkPosition(ChessMove move) {
		return new ChessPosition(this).updatePosition(move);
	}
	
	/**
	 * Updates same position object. 
	 * Move is not being validated. All validations should be done separately
	 * using: validateMove
	 * @param move
	 */
	public ChessPosition updatePosition(ChessMove move) {
		
		ChessCoords src = move.getSrc();
		ChessCoords dst = move.getDst();
		delta = new ChessPositionDelta();
		ChessPiece piece = getPiece(src);
		PieceType pieceType = piece.getType();
		PieceColor pieceColor = piece.getColor();
		int srcRank = src.getRank();
		int dstRank = dst.getRank();
		int srcFile = src.getFile();
		int dstFile = dst.getFile();
		int deltaRank = dstRank - srcRank;
		int deltaFile = dstFile - srcFile;
		
		ChessPiece target = getPiece(dst);

		//en passant take
		if (target == null && pieceType == PieceType.PAWN && dst.compareTo(enPassant)) {
			ChessCoords targetCoords = dst.incr((pieceColor == PieceColor.WHITE) ? -1 : 1, 0);
			target = getPiece(targetCoords);
			clearSquare(targetCoords);
		}
		
		if (target != null) {
			delta.disposedPieces.add(target);
            deletePiece(target);
		}
		
		//en passant set/reset
		if (Math.abs(deltaRank) == 2 && pieceType == PieceType.PAWN) {
			enPassant = dst.incr((pieceColor == PieceColor.WHITE) ? -1 : 1, 0);
		} 
		else {
			enPassant = null;
		}
		
		//If rook moved no castling to its direction is possible
		if (pieceType == PieceType.ROOK) {
			if (srcFile == 0) {
				if (pieceColor == PieceColor.WHITE && srcRank == 0) {
                    whiteCanCastleQueenside = false;
				} 
				else if (pieceColor == PieceColor.BLACK && srcRank == 7){
					blackCanCastleQueenside = false;
				}
			}
			else if (srcFile == 7) {
				if (pieceColor == PieceColor.WHITE && srcRank == 0) {
					whiteCanCastleKingside = false;
				} 
				else if (pieceColor == PieceColor.BLACK && srcRank == 7){
					blackCanCastleKingside = false;
				}
			}
		}
		
		//If king moved no castling to any direction is possible
		if (pieceType == PieceType.KING) {
			if (pieceColor == PieceColor.WHITE) {
				whiteCanCastleKingside = false;
				whiteCanCastleQueenside = false;
			} 
			else {
				blackCanCastleKingside = false;
				blackCanCastleQueenside = false;
			}
		}
		
		if (pieceType == PieceType.KING && deltaFile == 2) {
			makeKingsideCastle(move);
		}
		else if (pieceType == PieceType.KING && deltaFile == -2) {
			makeQueensideCastle(move);
		} 
		else {		
			if (pieceType == PieceType.PAWN && ((pieceColor == PieceColor.WHITE && dstRank == 7) || (pieceColor == PieceColor.BLACK && dstRank == 0))) {
				ChessPiece promotedPiece = ChessPiece.create(move.getPromotion(), pieceColor, dst);
				deletePiece(piece);
				addPiece(promotedPiece);
				delta.addedPieces.add(promotedPiece);
				delta.disposedPieces.add(piece);
			}
			else {
				movePiece(piece, dst);
				delta.movedPieces.add(piece);
			}
		}
		
		turn = (turn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
		return this;
	}
		
	/**
	 * 
	 * @param move
	 * @return
	 */
	public boolean validateMove(ChessMove move) {
		
		ChessCoords src = move.getSrc();
		ChessCoords dst = move.getDst();
		ChessPiece srcPiece = getPiece(src);
		ChessPiece dstPiece = getPiece(dst);
			
		//No piece in source square
		if (srcPiece == null) {
			return false;
		}
		
		//Not my turn
		if (srcPiece.getColor() != turn) {
			return false;
		}
		
		//Destination square is out the board
		if (!dst.inRange()) {
			return false;
		}
		
		//Same square
		if (src.compareTo(dst)) {
			return false;
		}
		
		//Piece of same color or king of any color is found on destination square
		if (dstPiece != null && (dstPiece.getColor() == srcPiece.getColor() || dstPiece.getType() == PieceType.KING)) {
			return false;
		}
		
		PieceType pieceType = srcPiece.getType();
		PieceColor pieceColor = srcPiece.getColor();
		int srcRank = src.getRank();
		int dstRank = dst.getRank();
		int srcFile = src.getFile();
		int dstFile = dst.getFile();
		int deltaRank = dstRank - srcRank;
		int deltaFile = dstFile - srcFile;
		
		if (pieceType == PieceType.KING && deltaFile == 2) {
			return validateKingsideCastle(src, dst);
		}
		else if (pieceType == PieceType.KING && deltaFile == -2) {
			return validateQueensideCastle(src, dst);
		}
		else {				
			if (pieceType == PieceType.PAWN) {
				if (dstPiece == null) {
					if (enPassant != null && dst.compareTo(enPassant)) {
						dstPiece = (pieceColor == PieceColor.WHITE) ? getPiece(dst.incr(-1, 0)) : getPiece(dst.incr(1, 0));
					}
				}
				
				//Validate correct promotion
				if ((pieceColor == PieceColor.WHITE && dstRank == 7) || (pieceColor == PieceColor.BLACK && dstRank == 0)) {
					PieceType promotedPiece = move.getPromotion();
					if (promotedPiece == null || promotedPiece == PieceType.PAWN || promotedPiece == PieceType.KING) {
						return false;
					}
				}
			}
				
			if (dstPiece == null) {
				if (!srcPiece.isValidMove(dst)) {
					return false;
				}
			} 
			else {
				if (!srcPiece.isValidTake(dst)) {
					return false;
				}
			}	
			
			if (pathBlocked(src, dst, pieceType)) {
				return false;
			}
		}
		
		//Verify that king is not going to be under attack
		if (forkPosition(move).switchTurn().kingChecked()) {
			return false;
		}
		
		return true;
	}
		
	/**
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	private boolean validateKingsideCastle(ChessCoords src, ChessCoords dst) {
		
		ChessPiece king = getPiece(src);
		
		if (king == null || king.getType() != PieceType.KING) {
			return false;
		}
		
		PieceColor kingColor = king.getColor();
		PieceColor oppositeColor = king.getOppositeColor();
		ChessPiece rook = getPiece(new ChessCoords(src.getRank(), 'h'));
		
		if (rook == null) {
			return false;
		}
		
		if (kingColor == PieceColor.WHITE) {
			if (!whiteCanCastleKingside || !src.compareTo(new ChessCoords("e1"))) {
				return false;
			}
		}

		
		if (kingColor == PieceColor.BLACK) {
			if (!blackCanCastleKingside || !src.compareTo(new ChessCoords("e8"))) {
				return false;
			}
		}
		
		if (dst.getFile() - src.getFile() != 2) {
			return false;
		}
		
		if (squareUnderAttack(oppositeColor, src)) {
			return false;
		}
		
		for (int fileStep=1; fileStep<3; fileStep++) {
			ChessCoords testSquare = src.incr(0, fileStep);
			if (getPiece(testSquare) != null || squareUnderAttack(oppositeColor, testSquare)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	private boolean validateQueensideCastle(ChessCoords src, ChessCoords dst) {
		
		ChessPiece king = getPiece(src);
		
		if (king == null || king.getType() != PieceType.KING) {
			return false;
		}
		
		PieceColor kingColor = king.getColor();
		PieceColor oppositeColor = king.getOppositeColor();
		ChessPiece rook = getPiece(new ChessCoords(src.getRank(), 'a'));
		
		if (rook == null) {
			return false;
		}
		
		if (kingColor == PieceColor.WHITE) {
			if (!whiteCanCastleQueenside || !src.compareTo(new ChessCoords("e1"))) {
				return false;
			}
		}

		
		if (kingColor == PieceColor.BLACK) {
			if (!blackCanCastleQueenside || !src.compareTo(new ChessCoords("e8"))) {
				return false;
			}
		}
		
		if (dst.getFile() - src.getFile() != -2) {
			return false;
		}
		
		if (squareUnderAttack(oppositeColor, src)) {
			return false;
		}
		
		for (int fileStep=-1; fileStep>-3; fileStep--) {
			ChessCoords testSquare = src.incr(0, fileStep);
			if (getPiece(testSquare) != null || squareUnderAttack(oppositeColor, testSquare)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Loading position from FEN string
	 * @param fen
	 * @throws InvalidFenException 
	 */
	public void setPosition(String fen) throws InvalidFenException {
		clear();
		
		String[] fenFields = fen.split(" ");
		if (fenFields.length < 4) {
			throw new InvalidFenException(String.format("Invalid number of fields in fen %s!", fen));
		}
		
		String[] fenRanks = fenFields[0].split("/");
		if (fenRanks.length != 8) {
			throw new InvalidFenException(String.format("Invalid number of ranks in fen %s!", fen));
		}
		
		int rank = 7;
		for (String fenRank : fenRanks) {
			int file = 0;
			for (int i=0; i<fenRank.length(); i++) {
				char fenChar = fenRank.charAt(i);
				if (Character.isDigit(fenChar)) {
					file += (fenChar - '0');
				}
				else {
					addPiece(fenChar, new ChessCoords(rank, file));
					file++;
				}
				
			}
			rank--;
		}
		
		String fenTurn = fenFields[1];
		if (fenTurn.equals("w")) {
			turn = PieceColor.WHITE;
		} 
		else if (fenTurn.equals("b")) {
			turn = PieceColor.BLACK;
		}
		else {
			throw new InvalidFenException(String.format("Invalid fen second field %s!", fenTurn));
		}
		
		String fenCastling = fenFields[2];
		whiteCanCastleKingside = fenCastling.contains("K");
		whiteCanCastleQueenside = fenCastling.contains("Q");
		blackCanCastleKingside = fenCastling.contains("k");
		blackCanCastleQueenside = fenCastling.contains("q");
		
		String fenEnPassant = fenFields[3];
		enPassant = fenEnPassant.equals("-") ? null : new ChessCoords(fenEnPassant);
	}
	
	/**
	 * Serializing to String in FEN format.
	 */
	@Override
	public String toString() {
		StringBuilder fenBuilder = new StringBuilder();
		for (int rank=7; rank>=0; rank--) {
			int emptyCount = 0;
			for (int file=0; file<=7; file++) {
				ChessPiece piece = position[rank][file];
				
				if (piece == null) {
					emptyCount++;
				} 
				
				if ((piece != null || file == 7) && emptyCount > 0) {
					fenBuilder.append(emptyCount);
					emptyCount=0;	
				}
				
				if (piece != null) {
					fenBuilder.append(piece.toFen());		
				}
			}	
			
			fenBuilder.append("/");
		}
	
		fenBuilder.append(turn == PieceColor.WHITE ? " w " : " b ");
		
		StringBuilder castlingFenBuilder = new StringBuilder();
		if (whiteCanCastleKingside) {
			castlingFenBuilder.append('K');
		}
		
		if (whiteCanCastleQueenside) {
			castlingFenBuilder.append('Q');
		}
		
		if (blackCanCastleKingside) {
			castlingFenBuilder.append('k');
		}
		
		if (blackCanCastleQueenside) {
			castlingFenBuilder.append('q');
		}
		
		if (castlingFenBuilder.length() == 0) {
			castlingFenBuilder.append('-');
		} 
		
		castlingFenBuilder.append(' ');
		fenBuilder.append(castlingFenBuilder);
		
		fenBuilder.append((enPassant == null) ? "-" : enPassant.toString());
		fenBuilder.append(" 0 0"); //half and full moves counters - not useful. 
			
		return fenBuilder.toString();
	}
	
	/**
	 * 
	 * @param kingMove
	 */
	private void makeKingsideCastle(ChessMove kingMove) {
		
		ChessCoords srcKing = kingMove.getSrc();
		ChessCoords dstKing = kingMove.getDst();
		ChessPiece king = getPiece(srcKing);
		ChessCoords srcRook = srcKing.incr(0, 3);
		ChessCoords dstRook = dstKing.incr(0, -1);
		ChessPiece rook = getPiece(srcRook);
		
		if (king == null || rook == null || king.getType() != PieceType.KING || rook.getType() != PieceType.ROOK) {
			return;
		}
		
		movePiece(king, dstKing);
		movePiece(rook, dstRook);
		delta.movedPieces.add(king);
		delta.movedPieces.add(rook);
	}
	
	/**
	 * 
	 * @param kingMove
	 */
	private void makeQueensideCastle(ChessMove kingMove) {

		ChessCoords srcKing = kingMove.getSrc();
		ChessCoords dstKing = kingMove.getDst();
		ChessPiece king = getPiece(srcKing);
		ChessCoords srcRook = srcKing.incr(0, -4);
		ChessCoords dstRook = dstKing.incr(0, 1);
		ChessPiece rook = getPiece(srcRook);
		
		if (king == null || rook == null || king.getType() != PieceType.KING || rook.getType() != PieceType.ROOK) {
			return;
		}
		
		movePiece(king, dstKing);
		movePiece(rook, dstRook);
		delta.movedPieces.add(king);
		delta.movedPieces.add(rook);
	}
	
	/**
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	private boolean pathBlocked(ChessCoords src, ChessCoords dst, PieceType pieceType) {
		
		if (pieceType == PieceType.BISHOP || pieceType == PieceType.QUEEN || pieceType == PieceType.ROOK || pieceType == PieceType.PAWN) {
			int rankStep = (dst.getRank() == src.getRank()) ? 0 : ((dst.getRank() > src.getRank()) ? 1 : -1);
			int fileStep = (dst.getFile() == src.getFile()) ? 0 : ((dst.getFile() > src.getFile()) ? 1 : -1);
			ChessCoords iter = new ChessCoords(src);
			
			while (!iter.compareTo(dst)) {
				iter.incrInPlace(rankStep, fileStep);
				if (getPiece(iter) != null) {
					if (!iter.compareTo(dst)) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	/**
	 * 
	 * @param byColor
	 * @param dst
	 * @return
	 */
	private boolean squareUnderAttack(PieceColor byColor, ChessCoords dst) {
		List<ChessPiece> opPieces = findPieces(byColor);
		for (ChessPiece opPiece : opPieces) {
			if (!opPiece.isValidTake(dst)) {
				continue;
			}
			
			if (pathBlocked(opPiece.getLocation(), dst, opPiece.getType())) {
				continue;
			}
			
			return true;	
		}

		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean kingChecked() {
		ChessPiece myKing = findPieces(PieceType.KING, turn).get(0);
		return squareUnderAttack(PieceColor.switchColor(turn), myKing.getLocation());
	}

    /**
     *
     * @return
     */
	private boolean hasValidMove() {
        for (ChessPiece piece : getPieces(getTurn()).values()) {
            for (ChessMove move : piece.getAllMoves()) {
                if (validateMove(move)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param color
     * @return
     */
    private Multimap<PieceType, ChessPiece> getPieces(PieceColor color) {
        return (color == PieceColor.WHITE) ? whitePieces : blackPieces;
    }

    /**
     *
     * @param loc
     */
    private void clearSquare(ChessCoords loc) {
        position[loc.getRank()][loc.getFile()] = null;
    }
}
