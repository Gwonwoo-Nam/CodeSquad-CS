import java.net.http.HttpResponse;

public class Response implements Comparable {
    private HttpResponse<String> response;
    private long time;
    private int referenced = 1;

    public Response(HttpResponse<String> response) {
        this.response = response;
    }

    public HttpResponse<String> getResponse() {
        return response;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void isReferenced() {
        this.referenced++;
    }

    public int getReferenced() {
        return referenced;
    }

    @Override
    public int compareTo(Object o) {
        //참조 횟수가 적은 것이 우선순위가 높다.(먼저 큐에서 빠짐)
        Response target = (Response)o;
        return target.getReferenced() < this.getReferenced() ? 1 : -1;
    }

}
