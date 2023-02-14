import java.net.http.HttpResponse;

public class Response {
    private HttpResponse<String> response;
    private long time;

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
}
