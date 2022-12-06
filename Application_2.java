import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(50,50);
        SolarCircle earth = new SolarCircle(1/365.25,40, sun);
        SolarCircle moon = new SolarCircle(1/27.3,8,earth);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        earth.rotate(inputDate);
        moon.rotate(inputDate);

        int mapX = 100;
        int mapY = 100;
        sun.draw(10,mapX,mapY);
        earth.draw(5, mapX,mapY);
        moon.draw(3,mapX,mapY);

        List<String> solarMap = SolarCircle.getSolarMap();
        outputView.printMap(solarMap, mapX,mapY);
    }
}
