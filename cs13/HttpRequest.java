import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class HttpRequest {

    public static void main(String[] args) throws IOException {
        // URL 입력 및 생성
        URI uri = View.readUri();

        // DNS IP, Port 조회
        InetAddress address = DnsFetcher.fetchFirstIpAddress(uri);
        int port = DnsFetcher.fetchPortNumberOrDefault(uri);

        // Socket Open
        Socket socket = new Socket(address, port);

        // 데이터 쓰기
        writeToSocket(socket, uri);

        // 데이터 읽기
        readFromSocket(socket);

        // Socket Close
        socket.close();
    }



    private static void writeToSocket(Socket socket, URI uri) throws IOException {
        PrintStream out = new PrintStream(socket.getOutputStream());
        Request request = new Request(uri);

        // 요청라인
        System.out.println("> HTTP 요청 메시지\n"+request.getRequestMessage());
        out.println(request.getRequestMessage());

    }

    private static void readFromSocket(Socket socket) throws IOException {
        InputStream input = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        Response response = new Response(reader);
        response.parse();
        System.out.println(response);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
