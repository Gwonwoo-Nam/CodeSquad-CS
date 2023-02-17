import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Request {

    private URI uri;

    private enum Methods {
        GET(),
        POST(),
        PUT(),
        DELETE(),
        PATCH();

        public String getName() {
            return this.name();
        }
    }

    private class Header {

        private Map<String, String> headers = new HashMap<>();

        Header(URI uri) {
            headers.put("Host", uri.getAuthority());
            headers.put("User-Agent", "Mozilla/5.0");
            headers.put("Accept", "text/html");
            headers.put("Connection", "close");
        }

        public void addHeader(String key, String value) {
            headers.put(key, value);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Entry<String, String> e : headers.entrySet()) {
                sb.append(e.getKey() + ": " + e.getValue() + "\r\n");
            }
            sb.append("\r\n");
            return sb.toString();
        }
    }

    public Request(URI uri) {
        this.uri = uri;
    }

    public String getRequestMessage() {
        StringBuilder sb = new StringBuilder();

        //첫 줄
        sb.append(Methods.GET.getName());
        if (uri.getPath().equals("")) {
            sb.append(" /");
        } else {
            sb.append(" " + uri.getPath());
        }
        sb.append(" HTTP/1.1\r\n");

        //헤더
        Header header = new Header(uri);
        //header.addHeader("Accept-Language", "en");
        //header.addHeader("Accept-Encoding","identity");
        sb.append(header);
        return sb.toString();
    }
}
