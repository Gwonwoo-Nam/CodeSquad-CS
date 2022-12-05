import java.util.List;

public class Application_1 {

    public static void main(String[] args) {
        SolarCircle solarCircle = new SolarCircle();
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askSize();
        int size = inputView.readSize();
        List<String> circle = solarCircle.draw(size);
        outputView.printCircle(circle, size);
    }
}

