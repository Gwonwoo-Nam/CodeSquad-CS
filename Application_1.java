import java.util.List;

public class Application_1 {

    public static void main(String[] args) {
        SolarCircle solarCircle = new SolarCircle();
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askSize();
        int size = inputView.readSize();
        solarCircle.drawMap(size, size, size);
        outputView.printMap(solarCircle.getSolarMap(), size, size);
    }
}

