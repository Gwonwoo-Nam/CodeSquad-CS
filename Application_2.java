import java.time.LocalDate;
import java.util.List;

/**
 * 2단계 구현
 */
public class Application_2 {
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

        Planets.EARTH.rotate(inputDate);
        Planets.MOON.rotate(inputDate);
        Planets.SUN.draw();
        Planets.EARTH.draw();
        Planets.MOON.draw();

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
