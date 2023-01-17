import java.util.List;

public class Line implements Drawable {

    protected List<Dot> dots;

    Line(List<Dot> dots, int[] numbers) {
        this.dots = dots;
        init(numbers);
    }

    @Override
    public void init(int[] numbers) {
        int x = 0;
        int y = 0;
        for (int index = 0; index < numbers.length; index++) {
            if (index % 2 == 0) {
                x = numbers[index];
            }
            if (index % 2 == 1) {
                y = numbers[index];
                dots.add(new Dot(x, y));
            }
        }
    }

    @Override
    public double calculate() {
        return calculateDistance(0,1);
    }
    public double calculateDistance(int p1, int p2) {
        return Math.sqrt((dots.get(p1).getX() - dots.get(p2).getX())*(dots.get(p1).getX() - dots.get(p2).getX()) +
            (dots.get(p1).getY() - dots.get(p2).getY())*(dots.get(p1).getY() - dots.get(p2).getY()));

    }

    public List<Dot> getDots() {
        return dots;
    }

    public int getX (int pointIndex) {
        return dots.get(pointIndex).getX();
    }

    public int getY (int pointIndex) {
        return dots.get(pointIndex).getY();
    }

    @Override
    public String toString() {
        return ("두 점 사이의 거리는 " + calculate());
    }
}
