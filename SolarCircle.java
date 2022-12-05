
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class SolarCircle implements Circle {

    private final double CLOSE_DISTANCE = 0.5;
    private final String CIRCLE_MARK = "-";
    private final String EMPTY_MARK = "X";

    private double x_location;
    private double y_location;
    private double rotationalSpeed;

    private double revolutionRadius;
    static private List<String> solarMap = new ArrayList<>();


    SolarCircle() {
    }

    public static List<String> getSolarMap() {
        return solarMap;
    }

    SolarCircle(double rotationalSpeed, double x_location, double y_location,
            double revolutionRadius) {
        this.rotationalSpeed = rotationalSpeed;
        this.x_location = x_location;
        this.y_location = y_location;
        this.revolutionRadius = revolutionRadius;
    }

    @Override
    public List<String> draw(int size) {/*
        List<String> circle = new ArrayList<>();
        double radius = size / 2.0 - 0.5;

        for (int x_location = 0; x_location < size; x_location++) {
            for (int y_location = 0; y_location < size; y_location++) {
                double distance = calculateDistance(x_location, y_location, radius);
                int index = x_location * size + y_location;
                addMark(distance, index, mapX, mapY);
            }
        }
        return circle;*/
        return null;
    }

    public void drawMap(int size, int mapX, int mapY) {

        double radius = size / 2.0 - 0.5;

        for (int x_location = 0; x_location < mapX; x_location++) {
            for (int y_location = 0; y_location < mapY; y_location++) {
                double distance = calculate(x_location, y_location, radius);
                int index = x_location * mapX + y_location;
                addMark(distance, index, mapX, mapY);
            }
        }
    }


    private void addMark(double distance, int index, int mapX, int mapY) {
        if (distance <= CLOSE_DISTANCE) {
            if (index < mapX*mapY && solarMap.get(index) == EMPTY_MARK) {
             //   solarMap.set(index, CIRCLE_MARK);
                return ;
            }
            solarMap.add(CIRCLE_MARK);
            return;
        }
        solarMap.add(EMPTY_MARK);
    }


    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt((Math.pow(x - radius, 2) + Math.pow(y - radius, 2))));

        return distance;
    }

    private double calculate(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt(
                        (Math.pow(x - (int) x_location, 2) + Math.pow(y - (int) y_location, 2))));

        return distance;
    }

    @Override
    public double rotate(LocalDate currentDate, SolarCircle orbitalCircle) {
        LocalDate firstDate = LocalDate.of(2022, 01, 01);
        long rotationDays = ChronoUnit.DAYS.between(firstDate, currentDate);
        System.out.println("공전한 날 : " + rotationDays);
        System.out.println("공전 바퀴 수 : " + rotationalSpeed * rotationDays);
        double rotationAngle = 2 * Math.PI * rotationalSpeed * rotationDays;

        x_location = orbitalCircle.x_location + (revolutionRadius * Math.cos(rotationAngle));
        y_location = orbitalCircle.y_location + (revolutionRadius * Math.sin(rotationAngle));
        System.out.println("현재 x 위치 : " + x_location);
        System.out.println("현재 y 위치 : " + y_location);

        return rotationDays;
    }
}
