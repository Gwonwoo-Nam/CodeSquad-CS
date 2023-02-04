public class OutputView {

    static final String BOOT_MESSAGE = "(프로그램 실행)";
    static final String INITIALIZER_MESSAGE = "체스 보드를 초기화했습니다.";

    public static void bootProgram() {
        System.out.println(BOOT_MESSAGE);
    }
    public static void initialize() {
        System.out.println(INITIALIZER_MESSAGE);
    }

}
