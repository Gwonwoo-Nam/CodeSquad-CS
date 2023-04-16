import java.util.List;

public class OutputView {
    public static void printOpenMessage() {
        System.out.println("> 빈 자리는 다음과 같습니다.");
    }

    public static void printEmptySeats(List<Integer> emptySeats) {
        System.out.println(emptySeats);
    }
}
