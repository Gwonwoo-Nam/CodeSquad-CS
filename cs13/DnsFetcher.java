import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class DnsFetcher {

    public static int fetchPortNumberOrDefault(URI uri) {
        int port = uri.getPort();
        if (port == -1) {
            port = 80;
            System.out.println("포트 번호가 지정되어 있지 않습니다. 80번 포트를 사용합니다.\n");
        } else {
            System.out.println("지정 포트 번호 : " + port + "\n");
        }
        return port;
    }

    public static InetAddress fetchFirstIpAddress(URI uri) {
        InetAddress[] inetAddresses;
        try {
            System.out.println("(DNS Lookup...)");
            inetAddresses = InetAddress.getAllByName(uri.getAuthority());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }

        for (InetAddress address : inetAddresses) {
            System.out.println("IP 조회 : " + address.getHostAddress());
        }
        System.out.println("\nTCP Connection : " + inetAddresses[0].getHostAddress());
        return inetAddresses[0];
    }

}
