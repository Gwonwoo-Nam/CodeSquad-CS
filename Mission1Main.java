public class Mission1Main {
    final static int NUMBER_OF_DATA = 18;


    public static void main(String[] args) {
        VideoArrayList videos = new VideoArrayList(NUMBER_OF_DATA);

        View.printMessage();
        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            View.printResult(videos.getNode(i));
        }
    }

}
