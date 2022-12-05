import java.util.List;

public class Application {

    public static void main(String[] args) {
        SolarCircle solarCircle = new SolarCircle();
        OutputView outputView = new OutputView();

        for (int size = 1; size < 80; size++) {
            List<String> c1 = solarCircle.draw(size);
            outputView.printCircle(c1, size);
        }

    }

}

