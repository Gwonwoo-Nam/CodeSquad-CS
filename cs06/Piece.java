import java.util.List;

abstract class Piece {

    protected Position position;
    private final Color color;
    protected int score = 0;
    protected char mark;

    public int getScore() {
        return score;
    }

    Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }


    abstract List<Position> possiblePositions();


    abstract boolean isOnRightPosition();


    public void addPositionInBoard(int rankIndex, int fileIndex, List<Position> positions) {
        if (rankIndex >= 0 && rankIndex <= 7 && fileIndex >= 0 && fileIndex <= 7) {
            positions.add(new Position(rankIndex, fileIndex));
        }
    }

    public boolean hasSameColor(Piece piece) {
        if (color.equals(piece.color)) {
            return true;
        }
        return false;
    }

    public void move(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }
}


