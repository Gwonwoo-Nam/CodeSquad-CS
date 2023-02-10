import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Manager {

    private OrderQueue orderQueue;
    public Manager(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String str) {
        System.out.println(str);
    }

    public void checkOrder() {
        CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            if (Barista.hasHands()) {
                return orderQueue.dequeue();
            }
            throw new RuntimeException(); // 손이 없으면 에러 발생
        }).thenAccept(result -> { //손이 있고 주문이 있는 경우
            Barista.makeDrink(result);
            System.out.println(orderQueue.printLog());
            checkOrder(); //재귀
        }).exceptionally(e -> {
            checkOrder();
            return null;
        });
    }
}
