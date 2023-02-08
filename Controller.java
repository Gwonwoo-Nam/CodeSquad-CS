import java.io.IOException;
import java.util.List;

public class Controller {
    private static Board board;

    public static void run() throws IOException {
        initializeBoard();

        while (true) {
            try {
                String command = InputView.getCommandFromUser();

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

    private static void initializeBoard() {
        OutputView.bootProgram();
        List<Piece> pieceList = PieceFactory.initializeBoards();
        board = new Board(pieceList);
        OutputView.initialize();
        OutputView.render(board.display());
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



}
