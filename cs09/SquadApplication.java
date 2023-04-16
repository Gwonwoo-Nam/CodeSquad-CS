import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SquadApplication {

    public static void main(String[] args) {
        SquadSet a = new SquadSet(List.of(1, 2, 3));
        SquadSet b = new SquadSet(List.of(1, 3));
        SquadSet c = new SquadSet(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Function<Integer, Integer> squareFunction = (map) -> (map * map); //square function
        Function<Integer, String> toStringFunction = (num) -> Integer.toString(num); // toString Function
        Predicate<Integer> getEvenIndexFunction = (index) -> (index % 2 == 0); //even filter
        BinaryOperator<String> reducer = (str1, str2) -> str1 + ", " + str2; // reducer
        Consumer<String> consumer = (str) -> System.out.println(str); //출력

        c.map(squareFunction).filter(getEvenIndexFunction).map(toStringFunction)
            .display(reducer, consumer);
        //제곱 , 짝수 인덱스, 스트링 변환, 출력

        System.out.println(a.complement(b));
        System.out.println(a.intersect(b));
        System.out.println(a.getElements());
        for (int i = 0; i < a.resultAll().length; i++) {
            System.out.println(a.resultAll()[i]);
        }
    }
}
