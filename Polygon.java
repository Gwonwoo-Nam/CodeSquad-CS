import java.util.Collections;
import java.util.List;


public class Polygon extends Triangle implements Drawable {

    public Polygon(List<Dot> dots, int[] numbers) {
        super(dots, numbers);
    }


    @Override
    public double calculate() {
        double sumArea = 0;
        sort();
        for (int i = 1; i < dots.size() - 1; i++) {
            sumArea += calculateTriangleArea(0, i, i + 1);
        }

        return sumArea;
    }

    private double crossProduct(int p1, int p2) {
        double vectorX1 = dots.get(0).getX() - dots.get(p1).getX();
        double vectorY1 = dots.get(0).getY() - dots.get(p1).getY();
        double vectorX2 = dots.get(0).getX() - dots.get(p2).getX();
        double vectorY2 = dots.get(0).getY() - dots.get(p2).getY();
        return (vectorY2 * vectorX1 - vectorX2 * vectorY1);
    }

    private void sort() {
        for (int i = 1; i < dots.size() - 1; i++) {
            for (int j = 1; j < dots.size() - 1; j++) {
                if (crossProduct(i, j) < 0) {
                    Collections.swap(dots, i, j);
                }
            }

        }
    }
}
