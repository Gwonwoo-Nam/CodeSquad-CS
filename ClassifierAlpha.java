import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ClassifierAlpha {

    public static BiPredicate<Integer, Integer> isFactor = ((number, potentialFactor) ->
        number % potentialFactor == 0);
    public static Function<Integer, Set<Integer>> factors = (number ->
        IntStream.rangeClosed(1, (int) Math.sqrt(number))
            .filter(index -> filter(number, index, isFactor))
            .flatMap(index -> IntStream.of(index, number/index))
            .boxed()
            .collect(Collectors.toUnmodifiableSet()));

    public static Predicate<Integer> isPerfect = (number -> sum(factors.apply(number)) - number
        == number);
    public static Predicate<Integer> isAbundant = (number -> sum(factors.apply(number)) - number
        > number);
    public static Predicate<Integer> isDeficient = (number -> sum(factors.apply(number)) - number
        < number);



    public static <T,R> int sum(Set<Integer> factors) {
        return factors.stream().reduce(0, (total, n) -> total + n);
    }

    public static <T> boolean filter(T number, Predicate<T> predicate) {
        return predicate.test(number);
    }

    public static <T, U> boolean filter(T number, U index, BiPredicate<T, U> biPredicate) {
        return biPredicate.test(number, index);
    }

    public static void main(String[] args) {

        System.out.println(filter(10, isPerfect));
        System.out.println(filter(10, isAbundant));
        System.out.println(filter(10, isDeficient));
        System.out.println(filter(6, isPerfect));
        System.out.println(filter(6, isAbundant));
        System.out.println(filter(6, isDeficient));
    }
}