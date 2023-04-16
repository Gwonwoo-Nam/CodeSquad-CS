import java.time.LocalDate;
import java.util.List;

/**
 * 3단계 구현
 */
public class Application_3 {
    private static final int MAP_SIZE_X = 70;
    private static final int MAP_SIZE_Y = 70;

    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        SolarMap.setXAxisMapSize(MAP_SIZE_X);
        SolarMap.setYAxisMapSize(MAP_SIZE_Y);

        for (Planets planet : Planets.values()) {
            planet.rotate(inputDate);
            planet.draw();
        }

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
