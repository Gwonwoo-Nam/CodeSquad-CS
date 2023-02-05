import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece implements Movable {

    Bishop(Position position, Color color) {
        super(position, color);
        score = 3;
        if (color == Color.WHITE) {
            mark = "\u2657";
        }
        if (color == Color.BLACK) {
            mark = "\u265D";
        }
    }

    @Override
    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();

        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 1, 1,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 1, -1,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), -1, 1,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), -1, -1,
            positions);

        return positions;
    }

    private void addPositionRecursively(int rank, int file, int rankIncrement, int fileIncrement,
        List<Position> positions) {
        if (rank + rankIncrement > 7 || rank + rankIncrement < 0) {
            return;
        }
        if (file + fileIncrement > 7 || file + fileIncrement < 0) {
            return;
        }
        positions.add(new Position(rank + rankIncrement, file + fileIncrement));
        addPositionRecursively(rank + rankIncrement, file + fileIncrement, rankIncrement,
            fileIncrement, positions);
    }

    @Override
    public boolean isOnRightPosition() {
        if (isBlack() && position.getRankLocation() == 0 && (position.getFileLocation() == 2
            || position.getFileLocation() == 5)) {
            return true;
        }
        if (isWhite() && position.getRankLocation() == 7 && (position.getFileLocation() == 2
            || position.getFileLocation() == 5)) {
            return true;
        }
        return false;
    }

}
