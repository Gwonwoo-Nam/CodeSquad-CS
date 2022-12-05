import java.util.ArrayList;
import java.util.List;


public class SolarCircle implements Circle {
    private final double CLOSE_DISTANCE = 0.5;
    private final String CIRCLE_MARK = "-";
    private final String EMPTY_MARK = " ";

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
        }
        circle.add(EMPTY_MARK);
    }

    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt((Math.pow(x - radius, 2) + Math.pow(y - radius, 2))));

        return distance;
    }
}
