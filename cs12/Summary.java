import java.util.ArrayList;
import java.util.List;

public class Summary {

    private List<String> domains = new ArrayList<>();
    private int numberOfRequests = 0;
    private int numberOfImages = 0;
    private int numberOfCodes = 0;
    private double transmissionSize = 0;
    private int numberOfCached = 0;
    private long totalLoadingTime = 0;
    private double maxSize = 0;
    private long maxLoadingTime = 0;

    public void showSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====\n\n")
                .append("도메인 개수 : " + domains.size() + "개\n")
                .append("요청 개수 : " + numberOfRequests + "개\n")
                .append("이미지(png, gif, jpg) 개수 : " + numberOfImages + "개\n")
                .append("코드(css, js) 개수 : " + numberOfCodes + "개\n")
                .append("전송 용량 : " + String.format("%.4f",transmissionSize) + "MB\n")
                .append("캐시 데이터 개수 : " + Cache.getCacheSize() + "개\n")
                .append("전체 로딩 시간 : " + totalLoadingTime + "ms\n\n")
                .append("가장 큰 용량 : " + String.format("%.1f",maxSize) + "KB\n")
                .append("가장 오랜 대기 시간 : " + maxLoadingTime + "ms\n");

        System.out.println(sb);
    }


    public void addDomains(String domain) {
        if (!domains.contains(domain)) {
            domains.add(domain);
        }
    }


    public void incrementNumberOfRequests() {
        numberOfRequests++;
    }


    public void incrementNumberOfImages() {
        numberOfImages++;
    }

    public void incrementNumberOfCodes() {
        numberOfCodes++;
    }


    public void addTransmissionSize(double transmissionSize) {
        this.transmissionSize += transmissionSize*0.001;
    }

    public void addTotalLoadingTime(long loadingTime) {
        totalLoadingTime+=loadingTime;
    }

    public void updateMaxSize(double maxSize) {
        this.maxSize = Math.max(this.maxSize,maxSize);
    }


    public void updateMaxLoadingTime(long maxLoadingTime) {
        this.maxLoadingTime = Math.max(this.maxLoadingTime,maxLoadingTime);
    }
}
