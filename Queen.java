import java.util.List;

public class Queen extends Piece implements Movable {

    Queen(Position position, Color color) {
        super(position, color);
        score = 9;
    }

    @Override
    public List<Position> possiblePositions() {
        return null;
    }

}
