import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    Knight(Position position, Color color) {
        super(position, color);
        score = 3;
        if (color == Color.WHITE) {
            mark = '\u2658';
        }
        if (color == Color.BLACK) {
            mark = '\u265E';
        }
    }

    @Override
    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();

        addPositionInBoard(position.getRankLocation() + 2, position.getFileLocation() + 1, positions);
        addPositionInBoard(position.getRankLocation() + 2, position.getFileLocation() - 1,positions);
        addPositionInBoard(position.getRankLocation() - 2, position.getFileLocation() + 1,positions);
        addPositionInBoard(position.getRankLocation() - 2, position.getFileLocation() - 1,positions);
        addPositionInBoard(position.getRankLocation() + 1, position.getFileLocation() + 2,positions);
        addPositionInBoard(position.getRankLocation() + 1, position.getFileLocation() - 2,positions);
        addPositionInBoard(position.getRankLocation() - 1, position.getFileLocation() + 2,positions);
        addPositionInBoard(position.getRankLocation() - 1, position.getFileLocation() - 2,positions);

        return positions;
    }

    @Override
    public boolean isOnRightPosition() {
        if (isBlack() && position.getRankLocation() == 0 && (position.getFileLocation() == 1
            || position.getFileLocation() == 6)) {
            return true;
        }
        if (isWhite() && position.getRankLocation() == 7 && (position.getFileLocation() == 1
            || position.getFileLocation() == 6)) {
            return true;
        }
        return false;
    }
}
