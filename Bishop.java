import java.util.List;

public class Bishop extends Piece implements Movable {

    Bishop(Position position, Color color) {
        super(position, color);
        score = 3;
    }

    @Override
    public List<Position> possiblePositions() {
        return null;
    }

}
