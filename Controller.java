import java.io.IOException;

public class Controller {

    private Polygon polygon = new Polygon();

    public void run() throws IOException {
        InputView inputView = new InputView();

        int inputSize = registerInputs(inputView);

        OutputView outputView = new OutputView();
        try {
            outputView.drawMap(polygon);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("유효하지 않은 입력 값");
        }
        outputView.render();

        if (inputSize == 4) {
            runLine();
        }
        if (inputSize == 6) {
            runTriangle();
        }

    }

    private int registerInputs(InputView inputView) throws IOException {
        int[] numbers = inputView.readLine();
        int x = 0;
        int y = 0;
        Line line = new Line();
        for (int index = 0; index < numbers.length; index++) {

            if (index % 2 == 0) {
                x = numbers[index];
            }
            if (index % 2 == 1) {
                y = numbers[index];
                line.add(new Dot(x, y));
            }
        }
        polygon.add(line);
        return numbers.length;
    }

    private void runLine() {
        System.out.println("두 점 사이의 거리는 " + polygon.calculateDistance(0, 1));
    }

    private void runTriangle() {
        System.out.println("삼각형의 넓이는 " + polygon.calculateArea(0,1,2));
    }
}
