import java.util.Iterator;
import java.util.PriorityQueue;

public class Cache {
    /**
     * LFU 알고리즘 - 참조 횟수가 가장 적은 것부터 poll
     */
    private static PriorityQueue<Response> cacheQueue = new PriorityQueue();
    private static int maxCapacity = 30;

    public static int getCacheSize() {
        return cacheQueue.size();
    }

    /**
     * 캐시 추가
     * @param response
     */

    public static void addCache(Response response) {
        if (cacheQueue.size() >= maxCapacity) {
            cacheQueue.poll();
            cacheQueue.add(response);
            return ;
        }
        cacheQueue.add(response);
    }

    /**
     * 캐시에서 가져오기
     * @param response
     * @return
     */
    public static Response getCache(String url) {
        Iterator<Response> iterator = cacheQueue.iterator();
        while (iterator.hasNext()) {
            Response cachedResponse = iterator.next();
            if (url.equals(cachedResponse.getUrl())) {
                return cachedResponse;
            }
        }

        return null;
    }
}
