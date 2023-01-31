import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class ClassifierAlpha {

    public static boolean isFactor(int number, int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public static Set<Integer> factors(int number) {
        HashSet<Integer> factors = new HashSet<>();

        IntStream.rangeClosed(1,(int)Math.sqrt(number))
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

    public static boolean isPerfect(int number) {
        return sum(factors(number)) - number == number;
    }

    public static boolean isAbundant(int number) {
        return sum(factors(number)) - number > number;
    }

    public static boolean isDeficient(int number) {
        return sum(factors(number)) - number < number;
    }

    public static void main(String[] args) {

        System.out.println(ClassifierAlpha.isPerfect(10));
        System.out.println(ClassifierAlpha.isPerfect(6));
    }
}