import java.util.ArrayList;
import java.util.List;

public class Line implements Drawable{

    private List<Dot> dots = new ArrayList<>();


    public void add(Dot dot) {
        dots.add(dot);
    }


    @Override
    public void init() {

    }


    public double calculateDistance(int p1, int p2) {
        return Math.sqrt((dots.get(p1).getX() - dots.get(p2).getX())*(dots.get(p1).getX() - dots.get(p2).getX()) +
            (dots.get(p1).getY() - dots.get(p2).getY())*(dots.get(p1).getY() - dots.get(p2).getY()));

    }

    public List<Dot> getDots() {
        return dots;
    }
}
