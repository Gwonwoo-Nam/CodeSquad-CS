import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberClassifier {

    static final String prime = "prime";
    static final String squared = "squared";

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
    public static Predicate<Integer> isPrime = (number ->
        number > 1 && factors.apply(number).equals(Collections.unmodifiableSet(Set.of(1,number))));

    public static Predicate<Integer> isSquared = (number ->
        IntStream.rangeClosed(1, (int) Math.sqrt(number))
        .filter(index -> index * index == number)
        .anyMatch(hasSquared -> true));


    public static <T,R> int sum(Set<Integer> factors) {
        return factors.stream().reduce(0, (total, n) -> total + n);
    }

    public static <T> boolean filter(T number, Predicate<T> predicate) {
        return predicate.test(number);
    }

    public static <T, U> boolean filter(T number, U index, BiPredicate<T, U> biPredicate) {
        return biPredicate.test(number, index);
    }

    public static void classify(int numberRange) {
        IntStream.rangeClosed(2, numberRange)
            .mapToObj(index -> getType(index))
            .forEach(System.out::println);
    }

    public static String getIntType(int number) {
        return filter(number, isPerfect) ? "perfect" : filter(number, isAbundant) ? "abundant" : "deficient";
    }

    public static String getType(int number) {
        return String.format("%d : %s", number,
            Stream.of(getIntType(number),
                    isSquared.test(number) ? squared : null,
                    isPrime.test(number) ? prime : null)
                .filter(Objects::nonNull)
                .reduce((b1, b2) -> b1 + ", " + b2)
                .get());
    }


    public static void main(String[] args) {
        classify(100);
    }

}
