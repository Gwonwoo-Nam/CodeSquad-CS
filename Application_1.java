import java.util.List;

public class Application_1 {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askSize();
        int size = inputView.readSize();

        SolarMap.setYAxisMapSize(size);
        SolarMap.setXAxisMapSize(size);

        double xLocation = (size - 1) / 2.0;
        double yLocation = (size - 1) / 2.0;
        SolarCircle solarCircle = new SolarCircle(xLocation, yLocation, "-");
        solarCircle.draw(size);

        outputView.printMap(SolarMap.getSolarMap());
    }
}

