import java.util.ArrayList;
import java.util.List;

public class PolygonFactory {

    public Line createPolygon(int[] numbers) {
        List<Dot> dots = new ArrayList<>();
        if (numbers.length == 4) {
            return new Line(dots, numbers);
        }
        if (numbers.length == 6) {
            return (Line) new Triangle(dots, numbers);
        }
        if (numbers.length == 8) {
            return (Line) new Rectangle(dots, numbers);
        }
        if (numbers.length >= 10 && numbers.length % 2 == 0) {
            return (Line) new Polygon(dots, numbers);
        }
        throw new IllegalArgumentException("인자의 개수가 홀수입니다.");
    }
}
