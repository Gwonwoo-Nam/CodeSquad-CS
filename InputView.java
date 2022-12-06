import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class InputView {

    final String INPUT_SIZE_ERROR_MESSAGE = "[ERROR] 1 이상 80 이하의 Size를 입력해주세요.";
    final String DATE_SIZE_ERROR_MESSAGE = "[ERROR] 유효한 날짜를 입력해주세요.(예 : 12월 2일)";
    final String SIZE_REGEX = "^[1-9]{1}$|^[1-7]{1}[0-9]{1}$|^[8]{1}[0]{1}$";
    final String DATE_REGEX = "^([1][0-2]|[1-9])[월][ ]*([1-9]|[12][0-9]|[3][01])[일]$";
    // 30일, 31일 인 경우 확인
    public int readSize() {
        while (true) {
            String inputString = readInput(SIZE_REGEX, INPUT_SIZE_ERROR_MESSAGE);
            if (inputString != null) {
                return Integer.parseInt(inputString);
            }
        }
    }

    public LocalDate readDate() {
        while (true) {
            String inputString = readInput(DATE_REGEX, DATE_SIZE_ERROR_MESSAGE);
            if (inputString != null) {
                List<String> monthDay = new ArrayList<>(List.of(inputString.split("월| |일")));
                monthDay.removeIf(String::isEmpty);

                try {
                    LocalDate date = LocalDate.of(2022, Integer.parseInt(monthDay.get(0)),
                            Integer.parseInt(monthDay.get(1)));
                    return date;
                } catch (DateTimeException dateTimeException) {
                    System.out.println(DATE_SIZE_ERROR_MESSAGE);
                }
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
