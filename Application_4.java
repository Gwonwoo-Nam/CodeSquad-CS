import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;



public class Application_4 {

    public static void main(String[] args) throws InterruptedException {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView();

        outputView.askDate();
        LocalDate inputDate;
        inputDate = inputView.readDate();

        outputView.askTimeSpeed();
        int timeSpeed = inputView.readTimeSpeed();

        new GUI();

        while (true) {
            for (PlanetsGUI planet : PlanetsGUI.values()) {
                planet.rotate(inputDate);
                planet.draw();
            }

            GUI.date = inputDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));

            TimeUnit.MILLISECONDS.sleep(17);

            inputDate = inputDate.plusDays(timeSpeed);
        }
    }
}
