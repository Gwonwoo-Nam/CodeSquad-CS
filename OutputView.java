import java.util.List;
import java.util.Optional;

public class OutputView {

    static final String BOOT_MESSAGE = "(프로그램 실행)";
    static final String INITIALIZER_MESSAGE = "체스 보드를 초기화했습니다.";

    static final String COMMAND_MESSAGE = "명령을 입력하세요> ";

    public static void bootProgram() {
        System.out.println(BOOT_MESSAGE);
    }

    public static void initialize() {
        System.out.println(INITIALIZER_MESSAGE);
    }

    public static void getCommand() {
        System.out.print(COMMAND_MESSAGE);
    }

    public static void render(char[][] pieceMap) {
        StringBuffer renderBuffer = new StringBuffer();
        renderBuffer.append("ABCDEFGH\n");
        for (int rank = 0; rank < pieceMap.length; rank++) {
            renderBuffer.append(rank + 1);
            for (int file = 0; file < pieceMap.length; file++) {
                renderBuffer.append(pieceMap[rank][file]);
            }
            renderBuffer.append("\n");
        }
        renderBuffer.append("ABCDEFGH\n");
        System.out.println(renderBuffer.toString());
    }

    public static void printPossiblePositions(List<Position> positions) {
        Optional position = positions.stream().map(pos -> pos.toString())
            .reduce((str1, str2) -> str1 + "," + str2);
        position.ifPresentOrElse(value -> System.out.println(value), () -> System.out.println("없음"));

    }

    public static void showScore(int[] score) {
        System.out.println("--- 점수 현황표 ---");
        System.out.println("흑팀 : " + score[0] + " | 백팀 : " + score[1] + "\n");
    }



}
