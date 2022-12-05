import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(0,10,20,0);
        SolarCircle earth = new SolarCircle(1/365.25,10,20,10);
        SolarCircle moon = new SolarCircle(1/27.3,10,25,5);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        earth.rotate(LocalDate.of(2022,5,14), sun);
        moon.rotate(LocalDate.of(2022,5,14), earth);

        int mapX = 70;
        int mapY = 70;
        sun.drawMap(5,mapX,mapY);
        earth.drawMap(3, mapX,mapY);
        moon.drawMap(1,mapX,mapY);

        List<String> solarMap = SolarCircle.getSolarMap();
        outputView.printMap(solarMap, mapX,mapY);




    }

}
