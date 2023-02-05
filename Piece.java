import java.util.List;

public class Piece implements Movable {

    protected Position position;
    protected final Color color;
    protected int score = 0;
    protected String mark;


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


    public List<Position> possiblePositions() {

        return null;
    }


    public boolean isOnRightPosition() {
        return false;
    }


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
}


