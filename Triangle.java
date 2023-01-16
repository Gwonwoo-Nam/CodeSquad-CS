import java.util.List;

public class Triangle extends Line implements Drawable {
    public Triangle(List<Dot> dots, int[] numbers) {
        super(dots, numbers);
    }

    @Override
    public double calculate() {
        int p1 = 0;
        int p2 = 1;
        int p3 = 2;
        double a = calculateDistance(p1,p2);
        double b = calculateDistance(p2,p3);
        double c = calculateDistance(p3,p1);
        return 1.0 / 4 * Math.sqrt(
            4 * Math.pow(a, 2) * Math.pow(b, 2) - Math.pow((a * a + b * b - c * c), 2));
    }

    protected double calculateTriangleArea(int p1, int p2, int p3) {
        double a = calculateDistance(p1, p2);
        double b = calculateDistance(p2, p3);
        double c = calculateDistance(p3, p1);
        return 1.0 / 4 * Math.sqrt(
            4 * Math.pow(a, 2) * Math.pow(b, 2) - Math.pow((a * a + b * b - c * c), 2));
    }


}
