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

    public String toStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("element: \'" + element + "\',\n");
        if (!text.isEmpty()) {
            sb.append("text : \'" + text + "\' ,\n");
        }
        if (!attribute.isEmpty() && !value.isEmpty()) {
            sb.append("attributes: [\n");
            sb.append("\t {");
            for (int i = 0; i < attribute.size(); i++) {
                sb.append("name : " + attribute.get(i) + ", value : \"" + value.get(i) + "\"\n");
            }
            sb.append("}\n");
            sb.append("]\n");
        }
        if (!children.isEmpty()) {
            sb.append("child : [");
            for (int i = 0; i < children.size(); i++) {
                sb.append(children.get(i).element);
                if (i != children.size() - 1 && children.size() != 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}

