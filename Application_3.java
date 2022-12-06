import java.time.LocalDate;
import java.util.List;


public class Application_3 {

    public static void main(String[] args) {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        int mapSizeX = 50;
        int mapSizeY = 50;
        SolarMap.setXAxisMapSize(mapSizeX);
        SolarMap.setYAxisMapSize(mapSizeY);

        for (Planets planet : Planets.values()) {
            planet.rotate(inputDate);
            planet.draw();
        }

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
