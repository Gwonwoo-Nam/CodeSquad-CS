public class Piece {

    private final Position position;
    private final Color color;
    protected final int score = 0;

    enum Color {
        BLACK, WHITE
    }

    Piece(Position position, Color color) {
        this.position = position;
        this.color = color;
    }


}
