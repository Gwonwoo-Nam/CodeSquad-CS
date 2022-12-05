import java.util.List;

public class OutputView {
    final String INPUT_SIZE_MESSAGE = "원의 크기는?";
    public void askSize() {
        System.out.println(INPUT_SIZE_MESSAGE);
    }

    public void printCircle(List<String> circle, int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(circle.get(size * x + y));
            }
            System.out.println();
        }
    }


}
