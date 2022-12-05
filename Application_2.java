import java.time.LocalDate;
import java.util.List;


public class Application_2 {

    public static void main(String[] args) {
        SolarCircle sun = new SolarCircle(0,25,25,0);
        SolarCircle earth = new SolarCircle(1/365.25,40,25,15);
        SolarCircle moon = new SolarCircle(1/27.3,45,25,5);
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        earth.rotate(LocalDate.of(2022,3,1), sun);
        moon.rotate(LocalDate.of(2022,3,1), earth);

        int mapX = 70;
        int mapY = 70;
        //sun.drawMap(25,mapX,mapY);
        earth.drawMap(9, mapX,mapY);
        //moon.drawMap(3,mapX,mapY);

        List<String> solarMap = SolarCircle.getSolarMap();
        outputView.printMap(solarMap, mapX,mapY);




    }

}
