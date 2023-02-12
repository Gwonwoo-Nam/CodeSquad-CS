import java.util.Arrays;
import java.util.Scanner;

public class InputView {

    public static Order readMenu() {
        Scanner scanner = new Scanner(System.in);
        String order = scanner.nextLine();
        int[] orderInfo;
        try {
            orderInfo = Arrays.stream(order.split(":"))
                    .filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
            return readMenu();
        }
        return new Order(CoffeeMenu.getMenu(orderInfo[0]), orderInfo[1]);
    }
}
