import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class Barista {
    private static AtomicInteger semaphore = new AtomicInteger(2);

    public static void makeDrink(CoffeeMenu coffeeMenu) {
        CompletableFuture.supplyAsync(() -> {

            semaphore.decrementAndGet();
            System.out.println(coffeeMenu.getName() + " 시작");
            sleep(coffeeMenu.getTIME() * 1000);
            System.out.println(coffeeMenu.getName() + " 완성");
            semaphore.incrementAndGet();

            return null;
        });
    }

    public static boolean hasHands() {
        return semaphore.get() > 0;
    }

    public static boolean done() {
        return semaphore.get() == 2;
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
