import Piece.Color;
import Position.File;
import Position.Rank;

public class Controller {

    public static void run() {
        OutputView.bootProgram();

        Board board = new Board();
        initializeBoards(board);
        OutputView.initialize();


    }

    public static void initializeBoards(Board board) {
        board.initPiece(new Pawn(new Position(File.A, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.B, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.C, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.D, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.E, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.F, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.G, Rank.TWO), Color.BLACK));
        board.initPiece(new Pawn(new Position(File.H, Rank.TWO), Color.BLACK));
        board.initPiece(new Rook(new Position(File.A, Rank.ONE), Color.BLACK));
        board.initPiece(new Rook(new Position(File.H, Rank.ONE), Color.BLACK));
        board.initPiece(new Knight(new Position(File.B, Rank.ONE), Color.BLACK));
        board.initPiece(new Knight(new Position(File.G, Rank.ONE), Color.BLACK));
        board.initPiece(new Bishop(new Position(File.C, Rank.ONE), Color.BLACK));
        board.initPiece(new Bishop(new Position(File.F, Rank.ONE), Color.BLACK));
        board.initPiece(new Queen(new Position(File.E, Rank.ONE), Color.BLACK));

        board.initPiece(new Pawn(new Position(File.A, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.B, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.C, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.D, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.E, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.F, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.G, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Pawn(new Position(File.H, Rank.SEVEN), Color.WHITE));
        board.initPiece(new Rook(new Position(File.A, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Rook(new Position(File.H, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Knight(new Position(File.B, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Knight(new Position(File.G, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Bishop(new Position(File.C, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Bishop(new Position(File.F, Rank.EIGHT), Color.WHITE));
        board.initPiece(new Queen(new Position(File.E, Rank.EIGHT), Color.WHITE));
    }

}
