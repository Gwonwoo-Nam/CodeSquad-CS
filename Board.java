import java.util.List;

public class Board {

    private List<Piece> pieceList;

    public void initPiece(Piece piece) {
        pieceList.add(piece);
    }

    public int calculateScore() {

    }

    public Piece[][] display() {

    }

    public void setPiece(Piece piece, Position position) {
        pieceList.add(piece);
    }

    public boolean move(Position from, Position to) {

        return true;

    }
}
