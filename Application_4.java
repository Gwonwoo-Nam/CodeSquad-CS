import java.time.LocalDate;
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



        GUI gameView = new GUI();
        new Thread(gameView).start();

        int i = 0;
        while (i<1000) {
            for (PlanetsGUI planet : PlanetsGUI.values()) {
                planet.rotate(inputDate);
                planet.draw();
            }

            TimeUnit.MILLISECONDS.sleep(100);

            inputDate = inputDate.plusDays(1);
            i++;
        }
    }
}
