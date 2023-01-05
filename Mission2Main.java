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
                if (command.substring(0, 6).equals("delete")) {
                    String id = command.substring(7);
                    videoLinkedList.delete(videoRepository.getNodeByID(id));
                    View.printList(videoLinkedList);
                }
                if (command.matches("^insert [abcdef]* [0-9]$")) {//regex 확인
                    String id = command.substring(7,11);
                    int index = Integer.parseInt(command.substring(12));
                    videoLinkedList.insert(videoRepository.getNodeByID(id),index);
                    View.printList(videoLinkedList);
                }
                if (command.matches("^render$")) {//regex 확인
                    videoLinkedList.render();
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
