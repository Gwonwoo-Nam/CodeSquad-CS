import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<CoffeeMenu> orderQueue = new LinkedList<>();

    public void enqueue(Order order) {
        while (order.hasCount()) {
            orderQueue.add(order.dequeueMenu());
        }
    }

    public CoffeeMenu dequeue() {
        if (orderQueue.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 주문이 비어있습니다.");
        }
        return orderQueue.poll();
    }

    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }

    public String printLog() {
        StringBuffer sb = new StringBuffer();
        Iterator iter = orderQueue.iterator();
        sb.append("/");
        while(iter.hasNext()) {
            CoffeeMenu coffeeMenu = (CoffeeMenu)iter.next();
            sb.append(coffeeMenu.getMenuNumber());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("/");
        return sb.toString();
    }
}
