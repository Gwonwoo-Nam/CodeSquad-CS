import java.util.List;

public class Rectangle extends Triangle implements Drawable {

    private double area;


    public void validate() {
        if (isRightTriangle(0, 1, 2) && isRightTriangle(0, 1, 3)
            && isRightTriangle(0, 2, 3) && isRightTriangle(1, 2, 3)) {
            return ;
        }
        throw new IllegalArgumentException("직사각형이 아닙니다.");
    }

    Rectangle(List<Dot> dots, int[] numbers) {
        super(dots, numbers);
        validate();
    }

    Rectangle(List<Dot> dots, double width, double height, int[] numbers) { //생성자가 다른 경우
        super(dots, numbers);
        area = width * height;
    }

    @Override
    public double calculate() {
        return (calculateTriangleArea(0, 1, 2) + calculateTriangleArea(0, 1, 3)
            + calculateTriangleArea(0, 2, 3) + calculateTriangleArea(1, 2, 3)) / 2;
    }

    private boolean isRightTriangle(int p1, int p2, int p3) {
        if (Math.abs(Math.pow(calculateDistance(p1, p2), 2) + Math.pow(calculateDistance(p1, p3), 2)
            - Math.pow(calculateDistance(p2, p3), 2)) < 0.01) {
            return true;
        }
        if (Math.abs(Math.pow(calculateDistance(p1, p3), 2) + Math.pow(calculateDistance(p2, p3), 2)
            - Math.pow(calculateDistance(p1, p2), 2)) < 0.01) {
            return true;
        }
        if (Math.abs(Math.pow(calculateDistance(p1, p2), 2) + Math.pow(calculateDistance(p2, p3), 2)
            - Math.pow(calculateDistance(p1, p3), 2)) < 0.01) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return ("사각형의 넓이는 " + calculate());
    }
}
