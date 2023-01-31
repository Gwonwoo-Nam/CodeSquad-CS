import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberClassifier {

    static String prime = "prime";
    static String squared = "squared";

    public static boolean isFactor(int number, int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public static Set<Integer> factors(int number) {
        HashSet<Integer> factors = new HashSet<>();

        IntStream.rangeClosed(1, (int)Math.sqrt(number))
            .filter(index -> isFactor(number, index))
            .forEach(index -> {
                factors.add(index);
                factors.add(number / index);
            });

        return factors;
    }

    public static int sum(Set<Integer> factors) {
        return factors.stream().reduce(0, (total, n) -> total + n);
    }

    public static boolean isPrime(int number) {
        Set<Integer> primeSet = new HashSet<Integer>() {
            {
                add(1);
                add(number);
            }
        };
        return number > 1 && factors(number).equals(primeSet);
    }

    public static boolean isPerfect(int number) {
        return sum(factors(number)) - number == number;
    }

    public static boolean isAbundant(int number) {
        return sum(factors(number)) - number > number;
    }

    public static boolean isDeficient(int number) {
        return sum(factors(number)) - number < number;
    }

    public static boolean isSquared(int number) {
        return IntStream.rangeClosed(1, (int) Math.sqrt(number))
            .filter(index -> index * index == number)
            .anyMatch(hasSquared -> true);
    }

    public static void classify(int numberRange) {
        IntStream.rangeClosed(2, numberRange)
            .mapToObj(index -> getType(index))
            .forEach(System.out::println);
    }

    public static String getIntType(int number) {
        return isPerfect(number) ? "perfect" : isAbundant(number) ? "abundant" : "deficient";
    }

    public static String getType(int number) {
        return String.format("%d : %s", number,
            Stream.of(getIntType(number),
                    isSquared(number) ? squared : null,
                    isPrime(number) ? prime : null)
                .filter(Objects::nonNull)
                .reduce((b1, b2) -> b1 + ", " + b2)
                .get());
    }


    public static void main(String[] args) {
        classify(100);
    }

}
