import java.io.IOException;
import java.util.List;

public class Controller {
    private static Board board = new Board();

    public static void run() throws IOException {
        OutputView.bootProgram();

        initializeBoards();

        while (true) {
            OutputView.getCommand();
            try {
                String command = InputView.readLine();

                if (ControlMenu.POSSIBLE_LOCATION.choose(command)) {
                    checkPossibleLocations(command);
                    continue;
                }
                if (ControlMenu.MOVE.choose(command)) {
                    move(command);
                    continue;
                }
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 명령입니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private static void move(String command) {
        int fileFrom = command.charAt(0) - 'A';;
        int rankFrom = command.charAt(1) - '1';
        int fileTo = command.charAt(4) - 'A';
        int rankTo = command.charAt(5) - '1';
        Position fromPos = new Position(rankFrom, fileFrom);
        Position toPos = new Position(rankTo, fileTo);
        if (!board.move(fromPos, toPos)) {
            throw new IllegalArgumentException("[ERROR] 이동할 수 없는 위치입니다.");
        }
        OutputView.showScore(board.calculateScore());
        OutputView.render(board.display());
    }

    private static void checkPossibleLocations(String command) {
        int fileFromCommand = command.charAt(1) - 'A';
        int rankFromCommand = command.charAt(2) - '1';
        List<Position> positions = board.getPossiblePositions(
                new Position(rankFromCommand, fileFromCommand));
        OutputView.printPossiblePositions(positions);
    }

    public static void initializeBoards() {
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
