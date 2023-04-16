import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    private static List<Piece> pieceList = new ArrayList<>();

    public static List<Piece> initializeBoards() {
        for (int file = 0; file < 8; file++) {
            pieceList.add(new Pawn(new Position(1, file), Color.BLACK));
            pieceList.add(new Pawn(new Position(6, file), Color.WHITE));
        }
        pieceList.add(new Rook(new Position(0, 0), Color.BLACK));
        pieceList.add(new Rook(new Position(7, 0), Color.WHITE));
        pieceList.add(new Knight(new Position(0, 1), Color.BLACK));
        pieceList.add(new Knight(new Position(7, 1), Color.WHITE));
        pieceList.add(new Bishop(new Position(0, 2), Color.BLACK));
        pieceList.add(new Bishop(new Position(7, 2), Color.WHITE));
        pieceList.add(new Rook(new Position(0, 7), Color.BLACK));
        pieceList.add(new Rook(new Position(7, 7), Color.WHITE));
        pieceList.add(new Knight(new Position(0, 6), Color.BLACK));
        pieceList.add(new Knight(new Position(7, 6), Color.WHITE));
        pieceList.add(new Bishop(new Position(0, 5), Color.BLACK));
        pieceList.add(new Bishop(new Position(7, 5), Color.WHITE));

        pieceList.add(new Queen(new Position(0, 4), Color.BLACK));
        pieceList.add(new Queen(new Position(7, 4), Color.WHITE));
        //board.setPiece(new Queen(new Position(File.E, Rank.SIX), Color.WHITE));

        return pieceList;
    }
}
