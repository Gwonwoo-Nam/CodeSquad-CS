import java.util.Scanner;

public class InputView {
    public static String readCommand() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
