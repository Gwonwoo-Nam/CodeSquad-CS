import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Line polygon;

    public void run() throws IOException {

        InputView inputView = new InputView();

        registerInputs(inputView);

        OutputView outputView = new OutputView();
        try {
            outputView.drawMap(polygon);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("유효하지 않은 입력 값");
        }
        outputView.render();

        printResult();

    }

    private void registerInputs(InputView inputView) throws IOException {
        int[] numbers = inputView.readLine();
        List<Dot> dots = new ArrayList<>();
        if (numbers.length == 4) {
            polygon = new Line(dots, numbers);
            return ;
        }
        if (numbers.length == 6) {
            polygon = new Triangle(dots, numbers);
            return ;
        }
        if (numbers.length == 8) {
            polygon = new Rectangle(dots, numbers);
            return ;
        }
        if (numbers.length >= 10 && numbers.length % 2 == 0) {
            polygon = new Polygon(dots, numbers);
            return ;
        }
        throw new IllegalArgumentException("인자의 개수가 홀수입니다.");
    }

    private void printResult() {
        if (polygon instanceof Polygon) {
            runPolygon((Polygon)polygon);
            return;
        }
        if (polygon instanceof Rectangle) {
            runRectangle((Rectangle) polygon);
            return;
        }
        if (polygon instanceof Triangle) {
            runTriangle((Triangle) polygon);
            return;
        }
        if (polygon instanceof Line) {
            runLine(polygon);
        }


    }

    private void runLine(Line line) {
        System.out.println("두 점 사이의 거리는 " + line.calculateDistance(0, 1));
    }

    private void runTriangle(Triangle triangle) {
        System.out.println("삼각형의 넓이는 " + triangle.calculate());
    }

    private void runRectangle(Rectangle rectangle) {
        System.out.println("사각형의 넓이는 " + rectangle.calculate());
    }

    private void runPolygon(Polygon polygon) {
        System.out.println("다각형의 넓이는" + polygon.calculate());
    }
}
