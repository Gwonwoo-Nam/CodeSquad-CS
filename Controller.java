import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    public static void run() throws IOException {
        OutputView.bootProgram();
        Board board = new Board();

        initializeBoards(board);

        while (true) {
            OutputView.getCommand();
            try {
                String command = InputView.readLine();
                if (command.matches("^[?][A-H][1-8]$")) {
                    List<Position> positions = board.getPossiblePositions(
                        new Position(command.charAt(2) - '1', command.charAt(1) - 'A'));
                    OutputView.printPossiblePositions(positions);
                }
                if (command.matches("^[A-H][1-8][A-H][1-8]$")) {
                    List<String> pos = Arrays.stream(command.split(""))
                        .filter(str -> !str.isEmpty()).collect(
                            Collectors.toList());
                    Position fromPos = new Position(pos.get(1).charAt(0) - '1',
                        pos.get(0).charAt(0) - 'A');
                    Position toPos = new Position(pos.get(3).charAt(0) -'1',
                        pos.get(2).charAt(0)-'A');
                    board.move(fromPos, toPos);
                    OutputView.render(board.display());
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


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

        //board.setPiece(new Queen(new Position(File.E, Rank.SIX), Color.WHITE));

        OutputView.initialize();
        OutputView.render(board.display());
    }

}
