public class MapGenerator {
    private int[][] map = new int[25][25];

    public int[][] drawMap(Line polygon) throws IndexOutOfBoundsException {

        for (int x = 0; x <= 24; x++) {
            for (int y = 0; y <= 24; y++) {
                mark(polygon, x, y);
            }
        }
        return map;
    }

    private void mark(Line polygon, int x, int y) {
        for (int pointIndex = 0; pointIndex < polygon.getDots().size(); pointIndex++) {
            if ((polygon.getX(pointIndex) == x && polygon.getY(pointIndex) == y)) {
                map[x][y] = 1;
            }
        }
    }

}
