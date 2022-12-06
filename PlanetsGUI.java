import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public enum PlanetsGUI {
    SUN(320, 320, 6, "O"),
    MERCURY(1 / 88.0, 60, 2, "~", SUN),
    VENUS(1 / 224.0, 120, 2, "@", SUN),

    EARTH(1 / 365.25, 162, 3, "*", SUN),
    MOON(1 / 27.3, 25, 1, "-", EARTH),
    MARS(1/670.0, 216, 2, "#", SUN),
    DEIMOS(1 / 420.0, 4, 1, "*", MARS),
    PHOBOS(1/73.0, 6,1,"-", MARS),
    JUPITER(1 / 560.0, 24, 2, "X", SUN),
    SATURN(1 / 1230.0, 28, 2, "%", SUN),
    TITAN(1/180.0, 4, 1, "~", SATURN),
    IAPETUS(1/37.2, 6, 1, "*", SATURN),
    NEPTUNE(1 / 2884.0, 32, 2, "&", SUN),
    PLUTO(1 / 6420.0, 36, 2, "o", SUN);


    private final double CLOSE_DISTANCE = 0.5;
    private String circleMark;
    private final String EMPTY_MARK = " ";
    private double xLocation;
    private double yLocation;
    private int size;
    private double rotationalSpeed;
    private double revolutionRadius;
    private PlanetsGUI orbitalCircle;

    PlanetsGUI(double xLocation, double yLocation, int size, String circleMark) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.size = size;
        this.circleMark = circleMark;
    }

    PlanetsGUI(double rotationalSpeed, double revolutionRadius, int size, String circleMark,
            PlanetsGUI orbitalCircle) {
        this.rotationalSpeed = rotationalSpeed;
        this.revolutionRadius = revolutionRadius;
        this.size = size;
        this.circleMark = circleMark;
        this.orbitalCircle = orbitalCircle;
    }


    public void draw() {

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
                        (Math.pow(x - (int) xLocation, 2) + Math.pow(y - (int) yLocation, 2))));

        return distance;
    }

    private void addMark(double distance, int index) {
        if (distance <= CLOSE_DISTANCE) {
            drawCircleMark(index);
        }
        drawEmptyMark(index);
    }

    /**
     * Empty Mark가 이미 있는 경우 Circle Mark로 교체한다. 없는 경우 Circle Mark를 추가한다.
     *
     * @param index
     */
    private void drawCircleMark(int index) {
        try {
            if (SolarMap.getSolarMap().get(index) == EMPTY_MARK) {
                SolarMap.setMarker(index, circleMark);
            }
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(circleMark);
        }
    }

    /**
     * Mark가 이미 있는 경우 Skip한다. 없는 경우 Empty Mark를 추가한다.
     *
     * @param index
     */
    private void drawEmptyMark(int index) {
        try {
            SolarMap.getSolarMap().get(index); // 값이 이미 있으면 넘어간다.
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(EMPTY_MARK);
        }
    }

    public void rotate(LocalDate currentDate) {
        LocalDate firstDate = LocalDate.of(1, 01, 01);
        long rotationDays = ChronoUnit.DAYS.between(firstDate, currentDate);

        double rotationAngle = 2 * Math.PI * rotationalSpeed * rotationDays;

        if (orbitalCircle != null) {
            xLocation = orbitalCircle.xLocation + (revolutionRadius * Math.sin(rotationAngle));
            yLocation = orbitalCircle.yLocation + (revolutionRadius * Math.cos(rotationAngle));
        }
    }

    public double getXLocation() {
        return xLocation;
    }
    public double getYLocation() {
        return yLocation;
    }

}