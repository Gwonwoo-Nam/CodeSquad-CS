import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Barista {
    private static int semaphore = 2;
    private static boolean isWorking = false;
    //ExecutorService es = Executors.newFixedThreadPool(2);


    public static void makeDrink(CoffeeMenu coffeeMenu) {
        //2개까지 비동기 가능
        CompletableFuture.supplyAsync(() -> {
            semaphore--;

            System.out.println(coffeeMenu.getName() + " 시작");
            sleep(coffeeMenu.getTIME()*1000);
            System.out.println(coffeeMenu.getName() + " 완성");
            semaphore++;

            return null;
        });

    }

    public static boolean hasHands() {
        return semaphore > 0;
    }

    public static boolean done() {
        return semaphore == 2;
    }
    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
