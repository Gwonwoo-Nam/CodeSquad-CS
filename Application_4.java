import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Application_4 {

    public static void main(String[] args) throws InterruptedException {

        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        int mapSizeX = 50;
        int mapSizeY = 50;
        SolarMap.setXAxisMapSize(mapSizeX);
        SolarMap.setYAxisMapSize(mapSizeY);

        int i = 0;
        while (i<1000) {
            for (Planets planet : Planets.values()) {
                planet.rotate(inputDate);
                planet.draw();
            }

            List<String> solarMap = SolarMap.getSolarMap();
            outputView.printMap(solarMap);

            TimeUnit.MILLISECONDS.sleep(500);

            for (int lineIndex=0; lineIndex<25; lineIndex++) {
                System.out.println();
            }
            solarMap.clear();

            inputDate = inputDate.plusDays(1);
            i++;
        }

        List<String> solarMap = SolarMap.getSolarMap();
        outputView.printMap(solarMap);

    }
}
