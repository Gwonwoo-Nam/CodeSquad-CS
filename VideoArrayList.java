import java.util.ArrayList;
import java.util.List;

public class VideoArrayList {
    private VideoNode[] videoList;
    private static List<String> idList = new ArrayList<>();

    public VideoArrayList(int number) {
        this.videoList = new VideoNode[number];
        for (int i=0;i<number;i++) {
            videoList[i] = new VideoNode();
        }
    }

    public VideoNode getNode(int index) {
        return videoList[index];
    }

    public static void addIdList(String id) {
        idList.add(id);
    }
    public static List<String> getIdList() {
        return idList;
    }
}


