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

    public static void printList(VideoLinkedList videoLinkedList) {
        VideoNode currentNode = videoLinkedList.get(0);
        System.out.print("|---[");
        System.out.print(currentNode.getName()+", "+currentNode.getPlayTime()+"sec"+"]---[");
        while(currentNode.getNext() != null){
            currentNode = currentNode.getNext();
            System.out.print(currentNode.getName()+", "+currentNode.getPlayTime()+"sec"+"]---[");

        }
        System.out.println("end]");
    }

    public static void printMessage() {
        System.out.println(CREATE_CLIP_MESSAGE);
    }
}
