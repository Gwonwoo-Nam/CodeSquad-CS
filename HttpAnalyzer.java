import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpAnalyzer {

    public static void main(String[] args) throws IOException, InterruptedException {

        //String url = "https://lucas.codesquad.kr/masters-2023/course/CS16/CS12.HTTP-%EB%B6%84%EC%84%9D%EA%B8%B0/HTTP-%EB%B6%84%EC%84%9D%EA%B8%B0";
        String url = "https://m.naver.com";
        HttpResponse<String> request = sendRequest(url).getResponse();
        Response response = new Response(request);
        System.out.println("> " + URI.create(url).getAuthority());

        analyzeResponseHeaders(response);
        Document dom = Jsoup.parse(response.getResponse().body());

        Elements scripts = dom.select("script");
        analyzeSubElements(scripts);

        Elements images = dom.select("img");
        analyzeSubElements(images);
    }

    private static void analyzeSubElements(Elements scripts) throws IOException, InterruptedException {
        for (Element e : scripts) {
            if (!e.attr("src").isEmpty()) {
                viewElementName(e);
                analyzeResponseHeaders(sendRequest(e.attr("src")));
            }

        }
    }

    private static void viewElementName(Element e) {
        String str = URI.create(e.attr("src")).getPath();
        int beginIndex = str.lastIndexOf("/");
        int endIndex = str.length();
        String result = str.substring(beginIndex + 1, endIndex);
        System.out.println(">> " + result);
    }

    private static void analyzeResponseHeaders(Response response) {
        StringBuilder sb = new StringBuilder();
        HttpHeaders headers = response.getResponse().headers();

        sb.append("도메인 " + response.getResponse().uri().getAuthority() + "\n")
                .append("스킴 " + response.getResponse().uri().getScheme() + "\n")
                .append("경로 " + response.getResponse().uri().getRawPath() + "\n")
                .append("종류 " + getType(headers.firstValue("content-type").orElseGet(() -> "")) + "\n")
                .append("용량 " + capacityConversion(headers.allValues("content-length")) + "KB\n")
                .append("다운로드 시간 " + response.getTime() + "ms\n");
        System.out.println(sb);
    }

    private static Response sendRequest(String url) throws IOException, InterruptedException {
        /**
         * Send 메소드 (GET 방식)
         * @param
         * 1. HttpRequest request : 요청하는 형태, 데이터를 정의
         * 2. BodyHandler<T> responseBodyHandler : 응답 받을 형태를 관리
         */
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        long before = System.currentTimeMillis();
        Response response = new Response(client.send(request, HttpResponse.BodyHandlers.ofString()));
        long after = System.currentTimeMillis();
        response.setTime(after - before);
        return response;
    }

    private static String capacityConversion(List<String> capacity) {
        if (capacity.isEmpty()) {
            return "";
        }
        double converted = Integer.parseInt(capacity.get(0)) * 0.001;
        return String.format("%.2f", converted);
    }

    private static String getType(String type) {
        return type.split("/")[1];
    }

}
