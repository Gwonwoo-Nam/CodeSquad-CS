import java.util.Scanner;


public class InputView {

    final String INPUT_SIZE_ERROR_MESSAGE = "1 이상 80 이하의 Size를 입력해주세요.";

    final String SIZE_REGEX = "^[1-9]{1}$|^[1-7]{1}[0-9]{1}$|^[8]{1}[0]{1}$";

    public int readSize() {
        while (true) {
            String inputString = readInput(SIZE_REGEX, INPUT_SIZE_ERROR_MESSAGE);
            if (inputString != null) {
                return Integer.parseInt(inputString);
            }
        }
    }

    private String readInput(String regex, String errorMessage) {
        Scanner input = new Scanner(System.in);

        try {
            String inputString = input.nextLine();
            validate(inputString, regex, errorMessage);

            return inputString;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void validate(String inputString, String regex, String errorMessage) {
        if (!inputString.matches(regex)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
