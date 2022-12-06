
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
    static private List<String> solarMap = new ArrayList<>();

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

    public void draw(int size, int mapX, int mapY) {

        double radius = (size - 1) / 2.0;

        for (int xLocation = 0; xLocation < mapX; xLocation++) {
            for (int yLocation = 0; yLocation < mapY; yLocation++) {
                double distance = calculateDistance(xLocation, yLocation, radius);
                int index = xLocation * mapY + yLocation;
                addMark(distance, index, mapX, mapY);
            }
        }
    }

    private double calculateDistance(int x, int y, double radius) {
        double distance = Math.abs(
                radius - Math.sqrt(
                        (Math.pow(x - xLocation, 2) + Math.pow(y - yLocation, 2))));

        return distance;
    }

    private void addMark(double distance, int index, int mapX, int mapY) {
        if (distance <= CLOSE_DISTANCE) {
            try {
                if (solarMap.get(index) == MapMarkers.EMPTY_MARK.getMarker()) {
                    solarMap.set(index, MapMarkers.CIRCLE_MARK.getMarker());
                    return;
                }
            } catch (IndexOutOfBoundsException exception) {
                solarMap.add(MapMarkers.CIRCLE_MARK.getMarker());
                return;
            }
        }
        if (solarMap.size() < mapX*mapY) {
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
