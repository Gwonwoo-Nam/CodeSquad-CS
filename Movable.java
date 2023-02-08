import java.util.List;

public interface Movable {

    List<Position> possiblePositions();

    boolean isOnRightPosition();

}
