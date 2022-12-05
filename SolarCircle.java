
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class SolarCircle implements Circle {

    private final double CLOSE_DISTANCE = 0.5;
    private final String CIRCLE_MARK = "-";
    private final String EMPTY_MARK = " ";
    private double x_location;
    private double y_location;
    private double rotationalSpeed;

    private double revolutionRadius;


    SolarCircle() {}
    SolarCircle(double rotationalSpeed, double x_location, double y_location, double revolutionRadius) {
        this.rotationalSpeed = rotationalSpeed;
        this.x_location = x_location;
        this.y_location = y_location;
        this.revolutionRadius = revolutionRadius;
    }

    @Override
    public List<String> draw(int size) {
        List<String> circle = new ArrayList<>();
        double radius = size / 2.0 - 0.5;

        for (int x_location = 0; x_location < size; x_location++) {
            for (int y_location = 0; y_location < size; y_location++) {
                double distance = calculateDistance(x_location, y_location, radius);
                addMark(circle, distance);
            }
        }
        return circle;
    }

    private void addMark(List<String> circle, double distance) {
        if (distance <= CLOSE_DISTANCE) {
            circle.add(CIRCLE_MARK);
            return;
        }
        circle.add(EMPTY_MARK);
    }

    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt((Math.pow(x - radius, 2) + Math.pow(y - radius, 2))));

        return distance;
    }

    @Override
    public double rotate(LocalDate currentDate, SolarCircle orbitalCircle) {
        LocalDate firstDate = LocalDate.of(2022, 01, 01);
        long rotationDays = ChronoUnit.DAYS.between(firstDate, currentDate);
        System.out.println("공전한 날 : " + rotationDays);
        System.out.println("공전 바퀴 수 : " + rotationalSpeed * rotationDays);
        double rotationAngle = 2*Math.PI * rotationalSpeed * rotationDays;

        x_location = orbitalCircle.x_location + (revolutionRadius * Math.cos(rotationAngle));
        y_location = orbitalCircle.y_location + (revolutionRadius * Math.sin(rotationAngle));
        System.out.println("현재 x 위치 : " + x_location);
        System.out.println("현재 y 위치 : " + y_location);

        return rotationDays;
    }
}
