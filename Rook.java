import java.util.List;

public class Rook extends Piece implements Movable {

    Rook(Position position, Color color) {
        super(position, color);
        score = 5;
    }


    @Override
    public List<Position> possiblePositions() {
        return null;
    }

}
