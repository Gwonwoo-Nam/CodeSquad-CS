import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Controller {

    public static void run() {
        OrderQueue orderQueue = new OrderQueue();

        Manager manager = new Manager(orderQueue);

        Cashier cashier = new Cashier(orderQueue);
        cashier.takeOrder();
        manager.checkOrder();

        while (true) {
            if (timeOut(orderQueue)) {
                return;
            }
        }
    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean timeOut(OrderQueue orderQueue) {
        ExecutorService es = Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> {
                    if (Barista.done() && orderQueue.isEmpty()) {
                        System.out.println("true");
                        sleep(5000);
                        return true;
                    }
                    System.out.println("false");
                    return false;
                }, es).thenApply(s -> {
                    finish(orderQueue);
                    return false;
                }).orTimeout(3, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    if (e instanceof TimeoutException) {
                        return true;
                    }
                    finish(orderQueue);
                    return false;
                });
        return false;
    }

    public static void main(String[] args) {
        run();

    }
}
