import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class InputView {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public int[] readLine() throws IOException {
        return Arrays.stream((bufferedReader.readLine().split("\\(|\\,|\\)|\\-")))
            .filter(number -> !number.isEmpty())
            .mapToInt(number -> {
                try {
                    return Integer.parseInt(Optional.ofNullable(number).get());
                }   catch (Exception exception) {
                    throw new IllegalArgumentException("유효하지 않은 입력 값");
                }
            })
            .toArray();
    }

}
