import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

public class Manager {
    public ArrayBlockingQueue<Boolean> orderedList = new ArrayBlockingQueue<Boolean>(10);

    private OrderQueue orderQueue;

    public Manager(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void work() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                checkOrder(); //1초마다 일하기
            }
        };
        CompletableFuture.supplyAsync(() -> {
            timer.scheduleAtFixedRate(task, 0L, 1000);
            return true;
        });
    }

    public void checkOrder() {
        CompletableFuture.supplyAsync(() -> {
            if (Barista.done() && orderQueue.isEmpty()) { //손이 없는데 주문도 없는 경우
                timeOut();
            }
            return orderQueue.peek();
        }).thenAccept(order -> { //손이 있고 주문이 있는 경우
            if (Barista.hasHands() == true && !orderQueue.isEmpty()) {
                orderQueue.dequeue();
                Barista.makeDrink(order);
                System.out.println(orderQueue.printLog());
            }
        });
    }

    private void timeOut() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (Barista.done() && orderQueue.isEmpty()) {
                    orderedList.add(true);
                    System.out.println("모든 음료를 제조했습니다.");
                }
            }
        };

        CompletableFuture.supplyAsync(() -> {
            if (Barista.done() && orderQueue.isEmpty()) {
                timer.schedule(task, 3000);
                return true;
            }
            return false;
        });
    }

}
