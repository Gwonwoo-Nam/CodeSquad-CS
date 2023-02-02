import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountApplication {

    public static void main(String[] args) {
        Map<Integer, Integer> inputA = Map.of(1,2,2,2,3,2);
        CountSet a = new CountSet(inputA);
        CountSet b = new CountSet(Map.of(1,1,3,3));

        System.out.println(a.sum(b));
        a.complement(b).resultAll();
    }
}
