import java.net.URI;
import java.util.Scanner;

public class View {
    public static URI readUri() {
        System.out.println("\n> URL을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        URI uri = URI.create(url);
        return uri;
    }

}
