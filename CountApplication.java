import java.util.Map;

public class CountApplication {

    public static void main(String[] args) {
        Map<Integer, Integer> inputA = Map.of(1,2,2,2,3,2);
        CountSet a = new CountSet(inputA);
        CountSet b = new CountSet(Map.of(1,1,3,3));

        a.append(3).resultAll();
        a.append(4).resultAll();
        b.remove(1).resultAll();
        b.remove(3).resultAll();
        System.out.println("COUNT:"+b.countFor(3));
        a.sum(b).resultAll();
        a.complement(b).resultAll();
        a.intersect(b).resultAll();

    }
}
