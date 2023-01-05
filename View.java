public class View {
    public static final String BRACKET_OPEN = "(";
    public static final String BRACKET_CLOSE = "):";
    public static final String SECONDS = "초";
    public static final String CREATE_CLIP_MESSAGE = "---영상클립 생성---";

    public static void printResult(VideoNode videoNode) {
        System.out.println(
                videoNode.getName() + BRACKET_OPEN + videoNode.getId() + BRACKET_CLOSE + videoNode.getPlayTime()
                        + SECONDS);
    }

    public static void printMessage() {
        System.out.println(CREATE_CLIP_MESSAGE);
    }
}
