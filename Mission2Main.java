import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mission2Main {

    final static int NUMBER_OF_DATA = 13;

    public static void main(String[] args) throws IOException {
        View.printMessage();

        VideoRepository videoRepository = new VideoRepository(NUMBER_OF_DATA);
        showResources(videoRepository);

        VideoLinkedList videoLinkedList = new VideoLinkedList();
        while (true) {
            editList(videoLinkedList, videoRepository);
        }

    }

    private static void showResources(VideoRepository videoRepository) {
        for (int i = 0; i < NUMBER_OF_DATA; i++) {
            View.printResult(videoRepository.getNodeByIndex(i));
        }
    }

    public static void editList(VideoLinkedList videoLinkedList, VideoRepository videoRepository) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        try {
            if (addVideo(videoLinkedList, videoRepository, command))
                return;
            if (deleteVideo(videoLinkedList, videoRepository, command))
                return;
            if (insertVideo(videoLinkedList, videoRepository, command))
                return;
            if (renderVideo(videoLinkedList, command))
                return;
            throw new IllegalArgumentException(View.INVALID_INPUT_ERROR);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean renderVideo(VideoLinkedList videoLinkedList, String command) {
        if (command.matches(View.RENDER_REGEX)) {//regex 확인
            videoLinkedList.render();
            return true;
        }
        return false;
    }

    private static boolean insertVideo(VideoLinkedList videoLinkedList, VideoRepository videoRepository, String command) {
        if (command.matches(View.INSERT_REGEX)) {
            String id = command.substring(7,11);
            int index = Integer.parseInt(command.substring(12));
            videoLinkedList.add(videoRepository.getNodeByID(id),index);
            View.printList(videoLinkedList);
            return true;
        }
        return false;
    }

    private static boolean deleteVideo(VideoLinkedList videoLinkedList, VideoRepository videoRepository, String command) {
        if (command.matches(View.DELETE_REGEX)) {
            String id = command.substring(7);
            videoLinkedList.delete(videoRepository.getNodeByID(id));
            View.printList(videoLinkedList);
            return true;
        }
        return false;
    }

    private static boolean addVideo(VideoLinkedList videoLinkedList, VideoRepository videoRepository, String command) {
        if (command.matches(View.ADD_REGEX)) {
            String id = command.substring(4);
            videoLinkedList.add(videoRepository.getNodeByID(id));
            View.printList(videoLinkedList);
            return true;
        }
        return false;
    }

}
