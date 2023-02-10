import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    public static Order readLine() {
        Scanner scanner = new Scanner(System.in);
        String order = scanner.nextLine();
        int[] orderInfo;
        try {
            orderInfo = Arrays.stream(order.split(":"))
                    .filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return readLine();
        }
        return new Order(CoffeeMenu.getMenu(orderInfo[0]), orderInfo[1]);
    }
}
