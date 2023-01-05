import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mission2Main {

    final static int NUMBER_OF_DATA = 13;

    public static void main(String[] args) throws IOException {
        View.printMessage();
        VideoRepository videoRepository = new VideoRepository(NUMBER_OF_DATA);
        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            View.printResult(videoRepository.getNodeByIndex(i));
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        VideoLinkedList videoLinkedList = new VideoLinkedList();
        while (true) {
            String command = br.readLine();
            try {
                if (command.substring(0, 3).equals("add")) {
                    String id = command.substring(4);
                    videoLinkedList.add(videoRepository.getNodeByID(id));
                    View.printList(videoLinkedList);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
