import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CountApplication {

    public static void main(String[] args) {
        CountSet a = new CountSet(List.of(1,2,3),List.of(2,2,2));
        CountSet b = new CountSet(List.of(1,3),List.of(1,3));

        Function<Integer, Integer> squareFunction = (map) -> (map * map); //square function
        Predicate<Integer> getEvenIndexFunction = (index) -> (index % 2 == 0); //even filter
        BinaryOperator<String> reducer = (str1, str2) -> (str1+" , "+str2); // reducer
        Consumer<String> consumer = (str) -> System.out.println(str); //출력

        System.out.println("제곱 출력");
        a.map(squareFunction).display(reducer, consumer);
        System.out.println("짝수 출력");
        a.filter(getEvenIndexFunction).display(reducer,consumer);
        System.out.println("단독 출력");
        a.display(reducer, consumer);

        System.out.println("append");
        a.append(3).display(reducer, consumer);
        a.append(4).display(reducer, consumer);
        System.out.println("remove");
        b.remove(1).display(reducer, consumer);
        b.remove(3).display(reducer, consumer);
        System.out.println("COUNT "+b.countFor(3));
        System.out.println("A 합집합 B");
        a.sum(b).display(reducer, consumer);
        System.out.println("A 차집합 B");
        a.complement(b).display(reducer, consumer);
        System.out.println("A 교집합 B");
        a.intersect(b).display(reducer, consumer);

    }
}
