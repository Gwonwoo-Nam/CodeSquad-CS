import java.util.ArrayList;
import java.util.List;

/**
 * SolarMap
 *
 * - 출력될 Map의 정보를 가지고 있는 객체
 * - Map X Size, Map Y Size와 맵 각 위치에서 marker를 저장한 map을 가지고 있다.
 */
public class SolarMap {

    private static int xAxisMapSize;
    private static int yAxisMapSize;

    private static List<String> solarMap = new ArrayList<>();

    public static void setXAxisMapSize(int mapSizeX) {
        xAxisMapSize = mapSizeX;
    }

    public static void setYAxisMapSize(int mapSizeY) {
        yAxisMapSize = mapSizeY;
    }

    public static int getXAxisMapSize() {
        return xAxisMapSize;
    }

    public static int getYAxisMapSize() {
        return yAxisMapSize;
    }

    public static List<String> getSolarMap() {
        return solarMap;
    }

    public static void setMarker(int index, String marker) {
        solarMap.set(index, marker);
    }

    public static void addMarker(String marker) {
        solarMap.add(marker);
    }
}
