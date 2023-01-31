import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class PrimeAlpha {

    public static boolean isPrime(int number) {
        Set<Integer> primeSet = new HashSet<Integer>(){ {add(1); add(number);} };
        return number > 1 && factors(number).equals(primeSet);
    }

    public static boolean isFactor(int number, int potentialFactor) {
        return number % potentialFactor == 0;
    }

    public static Set<Integer> factors(int number) {
        HashSet<Integer> factors = new HashSet<>();

        IntStream.range(1,(int)Math.sqrt(number)+1)
            .filter(index -> isFactor(number, index))
            .forEach(index -> {
                factors.add(index);
                factors.add(number / index);
            });

        return factors;
    }

    public static void main(String[] args) {
        System.out.println(PrimeAlpha.isPrime(10));
        System.out.println(PrimeAlpha.isPrime(7));
    }
}