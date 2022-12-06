import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(50,50,"O");
        SolarCircle earth = new SolarCircle(1/365.25,40,  "*" ,sun);
        SolarCircle moon = new SolarCircle(1/27.3,8,"-",earth);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        earth.rotate(inputDate);
        moon.rotate(inputDate);

        int mapSizeX = 100;
        int mapSizeY = 100;
        SolarMap.setXAxisMapSize(mapSizeX);
        SolarMap.setYAxisMapSize(mapSizeY);

        sun.draw(10);
        earth.draw(5);
        moon.draw(3);

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
