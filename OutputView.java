public class OutputView {

    private int[][] map = new int[25][25];

    public void drawMap(Line polygon) throws IndexOutOfBoundsException {

        for (int x = 0; x <= 24; x++) {
            for (int y = 0; y <= 24; y++) {
                mark(polygon, x, y);
            }
        }
    }

    private void mark(Line polygon, int x, int y) {
        for (int pointIndex = 0; pointIndex < polygon.getDots().size(); pointIndex++) {
            if ((polygon.getX(pointIndex) == x && polygon.getY(pointIndex) == y)) {
                map[x][y] = 1;
            }
        }
    }

    public void render() {
        StringBuffer renderer = new StringBuffer();
        for (int y = 24; y >= 0; y--) {
            renderYBar(renderer, y);
            for (int x = 1; x <= 24; x++) {
                renderPoints(renderer, x,y);
                renderXAxis(renderer, x,y);
            }
            renderer.append("\n");
        }
        renderXBar(renderer);
        System.out.println(renderer);
    }

    private void renderXAxis(StringBuffer renderer, int x, int y) {
        if (y == 0 && map[x][0] == 1) {
            renderer.append("-*-");
            return ;
        }
        if (y == 0) {
            renderer.append("---");
        }
    }

    private void renderYBar(StringBuffer renderer, int y) {
        if (map[0][0] == 1 && y == 0) { //x축
            renderer.append("  *");
            return ;
        }
        if (y == 0) { //x축
            renderer.append("  +");
            return ;
        }
        if (y % 2 == 0) { //짝수
            if (y < 10) { //두자릿수
                renderer.append(" ");
            }
            renderer.append(y);
            addBarMark(renderer, y);
            return ;
        }
        if (y % 2 != 0) { //홀수
            renderer.append("  ");
            addBarMark(renderer, y);
        }
    }

    private void addBarMark(StringBuffer renderer, int loc) {
        if (map[0][loc] == 1) {
            renderer.append("*");
            return ;
        }
        renderer.append("|");
    }

    private void renderPoints(StringBuffer renderer, int x, int y) {
        if (map[x][y] == 1 && x != 0 && y != 0) {
            renderer.append(" * ");
        }

        if (x!=0 && y != 0 && map[x][y] != 1) {
            renderer.append("   ");
        }
    }

    private static void renderXBar(StringBuffer renderer) {

        renderer.append(" 0    ");
        for (int x = 2; x <= 24; x++) {
            if (x % 2 == 0 && x < 10) {
                renderer.append(" " + x + " ");
                continue;
            }
            if (x % 2 == 0) {
                renderer.append(x + " ");
                continue;
            }
            renderer.append("   ");
        }
    }


}
