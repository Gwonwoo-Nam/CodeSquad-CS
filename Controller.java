import java.io.IOException;

public class Controller {

    private Line polygon;
    private final PolygonFactory polygonFactory = new PolygonFactory();
    private final MapGenerator mapGenerator = new MapGenerator();
    private final Renderer renderer = new Renderer();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() throws IOException {
        registerInputs(inputView);

        int[][] map;
        try {
            map = mapGenerator.drawMap(polygon);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("유효하지 않은 입력 값");
        }

        renderer.importMap(map);
        StringBuffer renderedMap = renderer.render();

        String calculationResult = addCalculation();

        outputView.printResult(renderedMap + calculationResult);
    }

    private void registerInputs(InputView inputView) throws IOException {
        int[] numbers = inputView.readLine();
        polygon = polygonFactory.createPolygon(numbers);
    }

    private String addCalculation() {
        if (polygon instanceof Polygon) {
            return ("두 점 사이의 거리는 " + polygon.calculateDistance(0, 1));
        }
        if (polygon instanceof Rectangle) {
            return ("사각형의 넓이는 " + polygon.calculate());
        }
        if (polygon instanceof Triangle) {
            return ("삼각형의 넓이는 " + polygon.calculate());

        }
        if (polygon instanceof Line) {
            return ("다각형의 넓이는" + polygon.calculate());
        }
        return null;
    }
}