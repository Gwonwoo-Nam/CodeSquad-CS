import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Board {
    Board(List<Piece> pieceList) {
        for (Piece p: pieceList) {
            initPiece(p);
        }
    }
    private List<Piece> pieceList = new ArrayList<>();
    private Color turn = Color.BLACK; //흑말부터 시작

    public void initPiece(Piece piece) {
        if (!piece.isOnRightPosition()) {
            Errors.ILLEGAL_POSITION.throwError();
        }
        if (hasPlace(piece.getPosition()) != null) {
            Errors.PIECE_EXIST.throwError();
        }
        pieceList.add(piece);
        if (hasMoreThanMax()) {
            Errors.EXCEED_MAX.throwError();
        }
    }

    public Piece hasPlace(Position position) {
        for (Piece piece : pieceList) {
            if (piece.getPosition().equals(position)) {
                return piece;
            }
        }
        return null;
    }

    public boolean hasMoreThanMax() {
        int[] validCounts = new int[] {8, 8, 2, 2, 2, 2, 2, 2, 1, 1};
        int[] countList = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Piece piece : pieceList) {
            if (piece.isBlack() && piece instanceof Pawn) {
                countList[0]++;
            }
            if (piece.isWhite() && piece instanceof Pawn) {
                countList[1]++;
            }
            if (piece.isBlack() && piece instanceof Knight) {
                countList[2]++;
            }
            if (piece.isWhite() && piece instanceof Knight) {
                countList[3]++;
            }
            if (piece.isBlack() && piece instanceof Bishop) {
                countList[4]++;
            }
            if (piece.isWhite() && piece instanceof Bishop) {
                countList[5]++;
            }
            if (piece.isBlack() && piece instanceof Rook) {
                countList[6]++;
            }
            if (piece.isWhite() && piece instanceof Rook) {
                countList[7]++;
            }
            if (piece.isBlack() && piece instanceof Queen) {
                countList[8]++;
            }
            if (piece.isWhite() && piece instanceof Queen) {
                countList[9]++;
            }
        }
        for (int i = 0; i < validCounts.length; i++) {
            if (countList[i] > validCounts[i]) {
                return true;
            }
        }
        return false;
    }

    public List<Position> getPossiblePositions(Position from) {
        if (hasPlace(from) == null) {
            Errors.NOT_EXIST_PIECE.throwError();
        }
        Piece p = hasPlace(from);

        List<Position> positionList = new ArrayList<>();

        for (Position to : p.possiblePositions()) {
            if (hasPlace(to) != null && hasPlace(to).hasSameColor(p)) {
                continue;
            }
            if (p instanceof Knight) {
                addKnightsPosition(from, positionList, to);
            }
            if (from.getRankLocation() == to.getRankLocation()) {
                if (!existHorizontalPlace(from, to)) {
                    positionList.add(to);
                }
            }
            if (from.getFileLocation() == to.getFileLocation()) {
                if (!existVerticalPlace(from, to)) {
                    positionList.add(to);
                }
            }
            if (Math.abs(from.getFileLocation() - to.getFileLocation()) ==
                    Math.abs(from.getRankLocation() - to.getRankLocation())) {
                if (!existDiagonalPlace(from, to)) {
                    positionList.add(to);
                }
            }
        }
        return positionList;
    }

    private void addKnightsPosition(Position from, List<Position> positionList, Position to) {
        if (to.getFileLocation() - from.getFileLocation() == 2) {
            Position frontPos = new Position(from.getRankLocation(), from.getFileLocation() + 1);
            if (hasPlace(frontPos) == null) {
                positionList.add(to);
            }
        }
        if (to.getRankLocation() - from.getRankLocation() == 2) {
            Position frontPos = new Position(from.getRankLocation() + 1, from.getFileLocation());
            if (hasPlace(frontPos) == null) {
                positionList.add(to);
            }
        }
        if (to.getFileLocation() - from.getFileLocation() == -2) {
            Position frontPos = new Position(from.getRankLocation(), from.getFileLocation() - 1);
            if (hasPlace(frontPos) == null) {
                positionList.add(to);
            }
        }
        if (to.getRankLocation() - from.getRankLocation() == -2) {
            Position frontPos = new Position(from.getRankLocation() - 1, from.getFileLocation());
            if (hasPlace(frontPos) == null) {
                positionList.add(to);
            }
        }
    }

    private boolean existHorizontalPlace(Position from, Position to) {
        int fromLoc = from.getFileLocation();
        int toLoc = to.getFileLocation();
        while (fromLoc != toLoc) {
            if (fromLoc != toLoc && fromLoc != from.getFileLocation()
                    && hasPlace(new Position(from.getRankLocation(), fromLoc)) != null) {
                return true;
            }
            if (fromLoc < toLoc) {
                fromLoc++;
            }
            if (fromLoc > toLoc) {
                fromLoc--;
            }
        }
        return false;
    }

    private boolean existVerticalPlace(Position from, Position to) {
        int fromLoc = from.getRankLocation();
        int toLoc = to.getRankLocation();
        while (fromLoc != toLoc) {
            if (fromLoc != toLoc && fromLoc != from.getRankLocation()
                    && hasPlace(new Position(fromLoc, from.getFileLocation())) != null) {
                return true;
            }
            if (fromLoc < toLoc) {
                fromLoc++;
            }
            if (fromLoc > toLoc) {
                fromLoc--;
            }

        }
        return false;
    }

    private boolean existDiagonalPlace(Position from, Position to) {
        int fromRank = from.getRankLocation();
        int fromFile = from.getFileLocation();
        int toRank = to.getRankLocation();
        int toFile = to.getFileLocation();
        while (fromRank != toRank && fromFile != toFile) {
            if (fromRank != toRank && fromRank != from.getRankLocation() &&
                    hasPlace(new Position(fromRank, fromFile)) != null) {
                return true;
            }
            if (fromRank < toRank) {
                fromRank++;
                if (fromFile < toFile) {
                    fromFile++;
                }
                if (fromFile > toFile) {
                    fromFile--;
                }
            }
            if (fromRank > toRank) {
                fromRank--;
                if (fromFile < toFile) {
                    fromFile++;
                }
                if (fromFile > toFile) {
                    fromFile--;
                }
            }
        }
        return false;
    }

    public int[] calculateScore() {
        int sumBlack = 0;
        int sumWhite = 0;
        for (Piece p : pieceList) {
            if (p.getColor() == Color.BLACK) {
                sumBlack += p.getScore();
            }
            if (p.getColor() == Color.WHITE) {
                sumWhite += p.getScore();
            }
        }
        return new int[] {sumBlack, sumWhite};
    }

    public char[][] display() {
        char[][] pieceMap = new char[8][8];

        for (int rank = 0; rank < pieceMap.length; rank++) {
            for (int file = 0; file < pieceMap.length; file++) {
                pieceMap[rank][file] = '.';
            }
        }
        for (Piece piece : pieceList) {
            pieceMap[piece.getPosition().getRankLocation()][piece.getPosition()
                    .getFileLocation()] = piece.mark;
        }

        return pieceMap;
    }

    public void setPiece(Piece piece) {
        if (hasPlace(piece.getPosition()) != null) {
            Errors.PIECE_EXIST.throwError();
        }
        pieceList.add(piece);
    }

    public boolean move(Position from, Position to) {
        for (Position pos : getPossiblePositions(from)) {
            if (pos.equals(to)) {
                Piece piece = hasPlace(from);
                if (!piece.getColor().equals(turn)) {
                    Errors.NOT_YOUR_TURN.throwError();
                }
                if (piece instanceof Pawn) {
                    promotePawn(piece,to);
                }
                catchOpponent(to);
                piece.move(to);
                turn = piece.getColor().getNextTurn();
                return true;
            }
        }
        return false;
    }

    private void catchOpponent(Position to) {
        Optional<Piece> opponent = Optional.ofNullable(hasPlace(to));
        opponent.ifPresent(
                piece -> {
                    if (piece.getColor().equals(turn.getNextTurn())) {
                        pieceList.remove(piece);
                    }
                }
        );
    }

    private void promotePawn(Piece piece, Position to) {
        if (to.isInPromotionArea()) {
            pieceList.add(new Queen(new Position(to), piece.getColor()));
            pieceList.remove(piece);
        }
    }

}
