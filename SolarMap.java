import java.util.ArrayList;
import java.util.List;

public class SolarMap {
    private static int xAxisMapSize;
    private static int yAxisMapSize;

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

    private List<String> map = new ArrayList<>();



}
