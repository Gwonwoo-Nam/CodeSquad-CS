/**
 * Application 1에서만 사용됨. (인스턴스 생성 불가한 Enum의 특성 때문에 확장 불가)
 */
public class SolarCircle implements Circle {

    private final double CLOSE_DISTANCE = 0.5;
    private String circleMark;
    private final String EMPTY_MARK = " ";
    private double xLocation;
    private double yLocation;


    SolarCircle(double xLocation, double yLocation, String circleMark) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.circleMark = circleMark;
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
            if (SolarMap.getSolarMap().get(index) == EMPTY_MARK) {
                SolarMap.setMarker(index, circleMark);
            }
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(circleMark);
        }
    }
    /**
     * Mark가 이미 있는 경우 Skip한다.
     * 없는 경우 Empty Mark를 추가한다.
     * @param index
     */
    private void drawEmptyMark(int index) {
        try {
            SolarMap.getSolarMap().get(index); // 값이 이미 있으면 넘어간다.
        } catch (IndexOutOfBoundsException exception) {
            SolarMap.addMarker(EMPTY_MARK);
        }
    }
}
