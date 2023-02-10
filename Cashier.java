import java.util.concurrent.CompletableFuture;

public class Cashier {
    private OrderQueue orderQueue;

    /**
     * 주문 받기
     */

    Cashier(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public void takeOrder() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println("> 메뉴  =  1. 아메리카노(3s)    2. 카페라떼(5s)    3. 프라프치노(10s)");
            System.out.println("> 주문할 음료를 입력하세요. 예) 아메리카노 2개 => 1:2");
            return InputView.readLine();  //주문 받기
        }).thenAccept(result -> {
            orderQueue.enqueue(result);
            System.out.println(orderQueue.printLog());
            takeOrder();
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            takeOrder();
            return null;
        });
    }

}
