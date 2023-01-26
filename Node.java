import java.util.LinkedList;
import java.util.List;

/**
 * ex) <div lang="ko"></div>에서 attribute : lang, value : ko
 * attribute(속성), value(값)을 저장
 */
class Node {
    String element;
    List<String> attribute = new LinkedList<>();
    List<String> value = new LinkedList<>();
    String text = "";
    boolean attributeModifiable = true;
    boolean valueModifiable = true;
    boolean printed = false;
    List<Node> children = new LinkedList<>();

    Node(String element) {
        this.element = element;
    }

    public boolean isAttributeEmpty() {
        if (attributeModifiable) {
            return true;
        }
        return false;
    }

    public boolean isValueEmpty() {
        if (valueModifiable) {
            return true;
        }
        return false;
    }

    public void unmodify() {
        attributeModifiable = false;
        valueModifiable = false;
    }
}

