import java.io.IOException;

public class ApplicationLine {

    public static void main(String[] args) throws IOException {
        while (true) {
            try {
                Controller controller = new Controller(); //JVM에 의해 제거되므로 안터짐
                controller.run();
                return ;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
