import java.util.ArrayList;
import java.util.List;

public class VideoRepository {

    private static List<String> idList = new ArrayList<>();
    private VideoNode[] videoList;

    public VideoRepository(int number) {
        videoList = new VideoNode[number];
        for (int i = 0; i < number; i++) {
            videoList[i] = new VideoNode();
        }
    }

    public static void addIdList(String id) {
        idList.add(id);
    }

    public static List<String> getIdList() {
        return idList;
    }

}




