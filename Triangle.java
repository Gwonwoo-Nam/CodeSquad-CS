public class Triangle extends Line implements Drawable {

    @Override
    public void init() {

    }


    public double calculateArea(int p1, int p2, int p3) {
        double a = calculateDistance(p1,p2);
        double b = calculateDistance(p2,p3);
        double c = calculateDistance(p3,p1);
        return 1 / 4 * Math.sqrt(
            4 * Math.pow(a, 2) * Math.pow(b, 2) - Math.pow((a * a + b * b - c * c), 2));
    }


}
