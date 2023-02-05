import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece implements Movable {

    Queen(Position position, Color color) {
        super(position, color);
        score = 9;
        if (color == Color.WHITE) {
            mark = "\u2655";
        }
        if (color == Color.BLACK) {
            mark = "\u265B";
        }
    }

    @Override
    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();

        //직선 움직임
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 1, 0,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), -1, 0,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 0, 1,
            positions);
        addPositionRecursively(position.getRankLocation(), position.getFileLocation(), 0, -1,
            positions);

        //대각선 움직임
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
        if (isBlack() && position.getRankLocation() == 0 && position.getFileLocation() == 4) {
            return true;
        }
        if (isWhite() && position.getRankLocation() == 7 && position.getFileLocation() == 4) {
            return true;
        }
        return false;
    }


}
