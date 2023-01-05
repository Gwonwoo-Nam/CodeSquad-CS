import java.util.ArrayList;
import java.util.List;

public class VideoArrayList {
    private static List<String> idList = new ArrayList<>();

    public VideoArrayList(int number) {
        for (int i=0;i<number;i++) {
            View.printResult(new VideoNode());
        }
    }

}


