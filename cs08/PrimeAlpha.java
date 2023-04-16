import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeAlpha {
    public static BiPredicate<Integer, Integer> isFactor = ((number, potentialFactor) ->
        number % potentialFactor == 0);

    public static Function<Integer, Set<Integer>> factors = (number ->
        IntStream.rangeClosed(1, (int) Math.sqrt(number))
            .filter(index -> filter(number, index, isFactor))
            .flatMap(index -> IntStream.of(index, number/index))
            .boxed()
            .collect(Collectors.toUnmodifiableSet()));

    public static Predicate<Integer> isPrime = (number ->
        number > 1 && factors.apply(number).equals(Collections.unmodifiableSet(Set.of(1,number))));




    public static <T> boolean filter(T number, Predicate<T> predicate) {
        return predicate.test(number);
    }

    public static <T, U> boolean filter(T number, U index, BiPredicate<T, U> biPredicate) {
        return biPredicate.test(number, index);
    }

    public static void main(String[] args) {
        System.out.println(filter(10, isPrime));
        System.out.println(filter(5, isPrime));
    }
}