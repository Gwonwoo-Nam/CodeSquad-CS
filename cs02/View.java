public class View {
    public static final String BRACKET_OPEN = "(";
    public static final String BRACKET_CLOSE = "):";
    public static final String SECONDS = "초";
    public static final String CREATE_CLIP_MESSAGE = "---영상클립 생성---";
    public static final String EMPTY = "empty";
    public static final String LIST_START_SIGN = "|---[";
    public static final String COMMA = ", ";
    public static final String SEC = "sec";
    public static final String LIST_SEP_SIGN = "]---[";
    public static final String NUMBER_OF_CLIPS = "영상클립: ";
    public static final String COUNT = "개";
    public static final String TOTAL_PLAY_TIME = "전체길이: ";

    public static final String NODE_NOT_EXIST = "node not exist";
    public static final String INVALID_INPUT_ERROR = "잘못된 입력입니다.";
    public static final String ADD_REGEX = "^add [abcdef]{4}$";
    public static final String DELETE_REGEX = "^delete [abcdef]{4}$";
    public static final String INSERT_REGEX = "^insert [abcdef]{4} \\d*$";
    public static final String RENDER_REGEX = "^render$";

    public static void printResult(VideoNode videoNode) {
        System.out.println(
                videoNode.getName() + BRACKET_OPEN + videoNode.getId() + BRACKET_CLOSE + videoNode.getPlayTime()
                        + SEC);
    }

    public static void printList(VideoLinkedList videoLinkedList) {
        VideoNode currentNode = videoLinkedList.get(0);
        if (currentNode == null) {
            System.out.println(EMPTY);

            return;
        }
        System.out.print(LIST_START_SIGN);
        System.out.print(currentNode.getName() + COMMA + currentNode.getPlayTime() + SEC + LIST_SEP_SIGN);
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
            System.out.print(currentNode.getName() + COMMA + currentNode.getPlayTime() + SEC + LIST_SEP_SIGN);

        }
        System.out.println("end]");
    }

    public static void printMessage() {
        System.out.println(CREATE_CLIP_MESSAGE);
    }

    public static void printRendering(int numberOfClip, int totalPlayTime) {
        System.out.println(NUMBER_OF_CLIPS + numberOfClip + COUNT);
        System.out.println(TOTAL_PLAY_TIME + totalPlayTime + View.SECONDS);
    }


}
