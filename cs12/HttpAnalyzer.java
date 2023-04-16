import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpAnalyzer {
    private static Summary summary = new Summary();

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String url = scanner.nextLine();
            run(url);
            summary.showSummary();
            summary = new Summary();
        }
        //String url = "https://lucas.codesquad.kr/masters-2023/course/CS16/CS12.HTTP-%EB%B6%84%EC%84%9D%EA%B8%B0/HTTP-%EB%B6%84%EC%84%9D%EA%B8%B0";
        //String url = "https://m.naver.com";
    }

    private static void run(String url) throws IOException, InterruptedException {

        Response response = sendRequest(url);
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
            String url = e.attr("src");
            if (!url.isEmpty()) {
                viewElementName(e);
                analyzeResponseHeaders(sendRequest(url));
            }

        }
    }

    private static void viewElementName(Element e) {
        String str = URI.create(e.attr("src")).getPath();
        if (str != null) {
            int beginIndex = Optional.ofNullable(str.lastIndexOf("/")).orElse(0);
            int endIndex = str.length();
            String result = str.substring(beginIndex + 1, endIndex);
            System.out.println(">> " + result);
        }
    }

    public static void updateSummary(Response response, HttpHeaders headers) {
        String capacityStr = capacityConversion(headers.allValues("content-length"));
        double capacity = 0;
        if (!capacityStr.isEmpty()) {
            capacity = Double.parseDouble(capacityStr);
        }
        String type = getType(headers.firstValue("content-type").orElseGet(() -> ""));
        summary.addDomains(response.getResponse().uri().getAuthority());
        summary.incrementNumberOfRequests();
        if (type.equals("png") || type.equals("gif") || type.equals("jpg")) {
            summary.incrementNumberOfImages();
        }
        if (type.equals("css") || type.equals("js")) {
            summary.incrementNumberOfCodes();
        }
        summary.addTransmissionSize(capacity);//MB 단위
        summary.addTotalLoadingTime(response.getTime());
        summary.updateMaxSize(capacity);
        summary.updateMaxLoadingTime(response.getTime());

    }

    private static void analyzeResponseHeaders(Response response) {
        StringBuilder sb = new StringBuilder();
        HttpHeaders headers = response.getResponse().headers();
        updateSummary(response, headers);

        sb.append("도메인 " + response.getResponse().uri().getAuthority() + "\n")
                .append("스킴 " + response.getResponse().uri().getScheme() + "\n")
                .append("경로 " + response.getResponse().uri().getRawPath() + "\n")
                .append("종류 " + getType(headers.firstValue("content-type").orElseGet(() -> "")) + "\n")
                .append("용량 " + capacityConversion(headers.allValues("content-length")) + "KB\n");
        if (response.getReferenced() > 1) {
            sb.append(">> 캐시됨\n");
            System.out.println(sb);
            return;
        }
        sb.append("다운로드 시간 " + response.getTime() + "ms\n");
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
        if (Cache.getCache(url) != null) {
            //캐시 조회되면 찾은 캐시를 반환, 캐시 참조 횟수 증가
            Response cached = Cache.getCache(url);
            cached.setTime(0);
            cached.isReferenced();
            return cached;
        }
        long before = System.currentTimeMillis();
        Response response = new Response(url, client.send(request, HttpResponse.BodyHandlers.ofString()));
        //Capacity 이상 넘어가면 Cache 하나씩 반환됨
        Cache.addCache(response);
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
