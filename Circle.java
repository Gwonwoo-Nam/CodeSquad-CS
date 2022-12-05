
import java.time.LocalDate;
import java.util.List;

public interface Circle {
    List<String> draw(int size);

    double rotate(LocalDate currentDate);
}
