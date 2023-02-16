import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Response {

    private int statusCode;
    private String responseLine;
    private int contentLength;
    private List<String> headers = new ArrayList<>();
    private BufferedReader bufferedReader;
    private String body = "";

    public Response(InputStream inputStream, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void parse() throws IOException {
        int bufferSize = 1024;
        parseResponseLine();
        parseHeaders();

        if (contentLength <= 0) {
            bufferSize = contentLength;
        }
        parseBody(bufferSize);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseResponseLine() throws IOException {
        responseLine = bufferedReader.readLine();
        statusCode = Integer.parseInt(responseLine.split(" ")[1]);
    }

    private void parseHeaders() throws IOException {
        String temp;
        while (!(temp = bufferedReader.readLine()).equals("")) {
            //Body 구분 개행 전까지 헤더를 저장
            if (temp.startsWith("Content-Length")) {
                contentLength = Integer.parseInt(temp.split(" ")[1]);
            }
            headers.add(temp);
        }
    }

    private void parseBody(int bufferSize) throws IOException {
        StringBuilder sb = new StringBuilder();

        char[] buffer = new char[bufferSize];
        int byteRead;

        while ((byteRead = bufferedReader.read(buffer, 0, buffer.length)) != -1) {
            String body = new String(buffer, 0, byteRead);
            sb.append(body);
        }

        body += sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("> HTTP 응답 메시지\n");
        sb.append(responseLine + "\r\n");
        headers.stream().forEach(header -> sb.append(header + "\r\n"));

        sb.append("\r\n> HTTP 메시지 Body\n" + body);

        return sb.toString();
    }
}
