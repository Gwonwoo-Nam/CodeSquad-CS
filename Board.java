import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Piece> pieceList = new ArrayList<>();

    public void initPiece(Piece piece) {
        if (!piece.isOnRightPosition()) {
            throw new IllegalArgumentException("[ERROR] 말의 위치가 적절하지 않습니다.");
        }
        if (hasPlace(piece.getPosition()) != null) {
            throw new IllegalArgumentException("[ERROR] 이미 말이 존재합니다.");
        }
        pieceList.add(piece);
        if (hasMoreThanMax()) {
            throw new IllegalArgumentException("[ERROR] 지정된 말의 개수보다 많습니다.");
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
        int[] validCounts = new int[]{8, 8, 2, 2, 2, 2, 2, 2, 1, 1};
        int[] countList = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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
            throw new IllegalArgumentException("[ERROR] 말이 없습니다.");
        }
        Piece p = hasPlace(from);

        List<Position> positionList = new ArrayList<>();
        for (Position to : p.possiblePositions()) {
            if (hasPlace(to) != null && hasPlace(to).hasSameColor(p)) {
                continue;
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
                return false;
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
        return true;
    }

    public int calculateScore() {

        return 0;
    }

    public String[][] display() {
        String[][] pieceMap = new String[8][8];

        for (int rank = 0; rank < pieceMap.length; rank++) {
            for (int file = 0; file < pieceMap.length; file++) {
                pieceMap[rank][file] = ".";
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
            throw new IllegalArgumentException("[ERROR] 이미 말이 존재합니다.");
        }
        pieceList.add(piece);
    }

    public boolean move(Position from, Position to) {
        for (Position pos : getPossiblePositions(from)) {
            if (pos.equals(to)) {
                hasPlace(from).move(to);
                return true;
            }
        }

        return false;
    }

}
