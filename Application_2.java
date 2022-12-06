import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(10,25,"O");
        SolarCircle earth = new SolarCircle(1/365.25,15,  "*" ,sun);
        SolarCircle moon = new SolarCircle(1/27.3,6,"-",earth);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        earth.rotate(inputDate);
        moon.rotate(inputDate);

        int mapSizeX = 50;
        int mapSizeY = 50;
        SolarMap.setXAxisMapSize(mapSizeX);
        SolarMap.setYAxisMapSize(mapSizeY);

        sun.draw(10);
        earth.draw(5);
        moon.draw(3);

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);
    }
}
