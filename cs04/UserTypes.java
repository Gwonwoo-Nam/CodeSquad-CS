import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UserTypes {

    public static final Map<String, Integer> types = new HashMap<>(Map.of("Pointer", 4));

    public static int getSize(String name) {
        for (Entry<String, Integer> type : types.entrySet()) {
            if (type.getKey().equals(name)) {
                return type.getValue();
            }
        }
        return 0;
    }
    public static void add(String name, int size) {
        if (types.containsKey(name)) {
            throw new IllegalArgumentException("이미 존재하는 타입입니다.");
        }
        types.put(name, size);
    }


}
