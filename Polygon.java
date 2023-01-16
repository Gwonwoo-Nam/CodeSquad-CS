import java.util.ArrayList;
import java.util.List;

public class Polygon extends Triangle implements Drawable {

    private List<Dot> dotList = new ArrayList<>();
    @Override
    public void init() {

    }

    public void add(Line line) {
        dotList.add(line);
    }



    public Line getLine(int lineIndex) {
        return lineList.get(lineIndex);
    }

    public List<Dot> getDots(int lineIndex) {
        return getLine(lineIndex).getDots();
    }

    public int getX (int lineIndex, int pointIndex) {
        return getDots(lineIndex).get(pointIndex).getX();
    }

    public int getY (int lineIndex, int pointIndex) {
        return getDots(lineIndex).get(pointIndex).getY();
    }

}
