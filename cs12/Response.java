import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Response implements Comparable {
    private HttpResponse<String> response;
    private String url;
    private long time;
    private int referenced = 1;

    public Response(String url, HttpResponse<String> response) {
        this.url = url;
        this.response = response;
    }

    public String getUrl() {
        return url;
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

    public void ifPushedOut() {
        referenced = 1;
    }

    @Override
    public int compareTo(Object o) {
        //참조 횟수가 적은 것이 우선순위가 높다.(먼저 큐에서 빠짐)
        Response target = (Response)o;
        return target.getReferenced() < this.getReferenced() ? 1 : -1;
    }

}
