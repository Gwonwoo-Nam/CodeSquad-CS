import java.util.List;

public class Knight extends Piece implements Movable {
    Knight(Position position, Color color) {
        super(position, color);
        score = 3;
    }

    @Override
    public List<Position> possiblePositions() {
        return null;
    }
}
