
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class SolarCircle implements Circle {

    private final double CLOSE_DISTANCE = 0.5;
    private double xLocation;
    private double yLocation;
    private double rotationalSpeed;
    private double revolutionRadius;


    private SolarCircle orbitalCircle;


    SolarCircle() {
    }

    public static List<String> getSolarMap() {
        return solarMap;
    }

    /**
     * 공전하는 행성이 없는 경우(태양)
     *
     * @param rotationalSpeed
     * @param xLocation
     * @param yLocation
     * @param revolutionRadius
     */
    SolarCircle(double xLocation, double yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    /**
     * 공전하는 행성이 있는 경우(지구, 달)
     *
     * @param rotationalSpeed
     * @param revolutionRadius
     * @param orbitalCircle
     */

    SolarCircle(double rotationalSpeed, double revolutionRadius, SolarCircle orbitalCircle) {
        this.rotationalSpeed = rotationalSpeed;
        this.revolutionRadius = revolutionRadius;
        this.orbitalCircle = orbitalCircle;
    }

    public void draw(int size) {

        double radius = (size - 1) / 2.0;

        for (int xLocation = 0; xLocation < SolarMap.getXAxisMapSize(); xLocation++) {
            for (int yLocation = 0; yLocation < SolarMap.getYAxisMapSize(); yLocation++) {
                double distance = calculateDistance(xLocation, yLocation, radius);
                int index = xLocation * SolarMap.getYAxisMapSize() + yLocation;
                addMark(distance, index);
            }
        }
    }

    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt(
                        (Math.pow(x - xLocation, 2) + Math.pow(y - yLocation, 2))));

        return distance;
    }

    private void addMark(double distance, int index) {
        if (distance <= CLOSE_DISTANCE) {
            drawCircleMark(index);
        }
        drawEmptyMark(index);
    }

    /**
     * Empty Mark가 이미 있는 경우 Circle Mark로 교체한다.
     * 없는 경우 Circle Mark를 추가한다.
     * @param index
     */
    private void drawCircleMark(int index) {
        try {
            if (solarMap.get(index) == MapMarkers.EMPTY_MARK.getMarker()) {
                solarMap.set(index, MapMarkers.CIRCLE_MARK.getMarker());
            }
        } catch (IndexOutOfBoundsException exception) {
            solarMap.add(MapMarkers.CIRCLE_MARK.getMarker());
        }
    }
    /**
     * Mark가 이미 있는 경우 Skip한다.
     * 없는 경우 Empty Mark를 추가한다.
     * @param index
     */
    private void drawEmptyMark(int index) {
        try {
            solarMap.get(index); // 값이 이미 있으면 넘어간다.
        } catch (IndexOutOfBoundsException exception) {
            solarMap.add(MapMarkers.EMPTY_MARK.getMarker());
        }
    }


    @Override
    public double rotate(LocalDate currentDate) {
        LocalDate firstDate = LocalDate.of(2022, 01, 01);
        long rotationDays = ChronoUnit.DAYS.between(firstDate, currentDate);

        double rotationAngle = 2 * Math.PI * rotationalSpeed * rotationDays;

        xLocation = orbitalCircle.xLocation + (revolutionRadius * Math.sin(rotationAngle));
        yLocation = orbitalCircle.yLocation + (revolutionRadius * Math.cos(rotationAngle));

        return rotationDays;
    }
}
