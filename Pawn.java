import java.util.List;

public class Pawn extends Piece implements Movable{

    Pawn(Position position, Color color) {
        super(position, color);
        score = 1;
    }



    @Override
    public List<Position> possiblePositions() {
        return null;
    }
}
