
import java.time.LocalDate;
import java.util.List;

public interface Circle {
    void draw(int size, int mapX, int mapY);

    double rotate(LocalDate currentDate);
}
