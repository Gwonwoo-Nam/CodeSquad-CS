import static java.lang.System.*;

import java.util.concurrent.ForkJoinPool;

public class Controller {
    private static OrderQueue orderQueue = new OrderQueue();

    private static Manager manager = new Manager(orderQueue);

    private static Cashier cashier = new Cashier(orderQueue);
    public static void run() {
        cashier.takeOrder();
        manager.work();

        finishEventLoop();
    }

    private static void finishEventLoop() {
        while (true) {
            if (manager.orderedList.contains(true)) {
                ForkJoinPool.commonPool().shutdown();
                break ;
            }
        }
        exit(0);
    }

    public static void main(String[] args) {
        run();
    }
}

