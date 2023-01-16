public class Dot {

    private final int x;
    private final int y;


    public void init() {
        validateNumberRange(x, y);

    }

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
        init();
    }

    private void validateNumberRange(int x, int y) {
        if (x > 24 || x < 0 || y > 24 || y < 0) {
            throw new IllegalArgumentException("유효하지 않은 범위의 좌표 입력입니다.");
        }
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
