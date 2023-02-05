import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece implements Movable {

    Rook(Position position, Color color) {
        super(position, color);
        score = 5;
        if (color == Color.WHITE) {
            mark =  "\u2656";
        }
        if (color == Color.BLACK) {
            mark =  "\u265C";
        }
    }


    @Override
    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();

        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 1, 0,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), -1, 0,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 0, 1,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 0, -1,
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
        if (isBlack() && position.getRankLocation() == 0 && (position.getFileLocation() == 0
            || position.getFileLocation() == 7)) {
            return true;
        }
        if (isWhite() && position.getRankLocation() == 7 && (position.getFileLocation() == 0
            || position.getFileLocation() == 7)) {
            return true;
        }
        return false;
    }
}
