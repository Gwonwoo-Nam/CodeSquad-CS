import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(10,20);
        SolarCircle earth = new SolarCircle(1/365.25,10, sun);
        SolarCircle moon = new SolarCircle(1/27.3,5,earth);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        earth.rotate(inputDate);
        moon.rotate(inputDate);

        int mapX = 90;
        int mapY = 30;
        sun.drawMap(5,mapX,mapY);
        earth.drawMap(3, mapX,mapY);
        moon.drawMap(1,mapX,mapY);

        List<String> solarMap = SolarCircle.getSolarMap();
        outputView.printMap(solarMap, mapX,mapY);




    }

}
