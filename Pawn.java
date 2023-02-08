import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements Movable {

    Pawn(Position position, Color color) {
        super(position, color);
        score = 1;
        if (color == Color.WHITE) {
            mark = '\u2659';
        } else {
            mark = '\u265F';
        }
    }


    @Override
    public List<Position> possiblePositions() {
        List<Position> positions = new ArrayList<>();
        if (isBlack()) {
            addPositionInBoard(position.getRankLocation() + 1, position.getFileLocation(),positions);
        }
        if (isWhite()) {
            addPositionInBoard(position.getRankLocation() - 1, position.getFileLocation(),positions);
        }

        return positions;
    }

    @Override
    public boolean isOnRightPosition() {
        if (isBlack() && position.getRankLocation() == 1) {
            return true;
        }
        if (isWhite() && position.getRankLocation() == 6) {
            return true;
        }
        return false;
    }

}
