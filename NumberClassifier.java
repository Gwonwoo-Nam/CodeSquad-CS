import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberClassifier {

    static String squared = "squared";
    static String prime = "prime";
    static String deficient = "deficient";
    static String perfect = "perfect";
    static String abundant = "abundant";

    public static boolean isFactor(int number, int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public static Set<Integer> factors(int number) {
        HashSet<Integer> factors = new HashSet<>();

        IntStream.range(1, (int) Math.sqrt(number) + 1)
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
        return IntStream.range(1, (int) Math.sqrt(number) + 1)
            .filter(index -> index * index == number)
            .anyMatch(hasSquared -> true);
    }

    public static void classify(int numberRange) {
        IntStream.range(2, numberRange + 1)
            .forEach(index -> {
                Optional<String> result =
                    (Stream.of(isDeficient(index) ? deficient : null,
                            isAbundant(index) ? abundant : null,
                            isSquared(index) ? squared : null,
                            isPerfect(index) ? perfect : null,
                            isPrime(index) ? prime : null)
                        .filter(Objects::nonNull)
                        .reduce((b1, b2) -> b1 + ", " + b2));
                System.out.println(index + " : " + result.get());
            });
    }


    public static void main(String[] args) {
        classify(100);
    }

}
