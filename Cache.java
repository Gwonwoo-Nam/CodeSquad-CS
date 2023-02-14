import java.util.Comparator;
import java.util.PriorityQueue;

public class Cache {
    /**
     * LFU 알고리즘 - 참조 횟수가 가장 적은 것부터 poll
     */
    private PriorityQueue<Response> cacheQueue = new PriorityQueue();
    private int maxCapacity = 10;

    public void addCache(Response response) {
        if (cacheQueue.size() >= maxCapacity) {
            cacheQueue.add(response);
        }
    }

    public void
}
