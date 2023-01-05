import java.util.ArrayList;
import java.util.List;

public class VideoArrayList {
    private static List<String> idList = new ArrayList<>();

    public VideoArrayList(int number) {
        for (int i=0;i<number;i++) {
            View.printResult(new VideoNode());
            //System.out.println(Long.toHexString(System.identityHashCode(new VideoNode())));
        }
    }



    public static void addIdList(String id) {
        idList.add(id);
    }
    public static List<String> getIdList() {
        return idList;
    }
}


