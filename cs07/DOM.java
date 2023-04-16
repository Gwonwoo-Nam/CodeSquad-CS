import java.util.ArrayList;
import java.util.List;

public class DOM {

    private List<Node> nodes = new ArrayList<>();

    public List<Node> elementByAttribute(String attribute, String value) {
        List<Node> list = new ArrayList<>();
        searchTreeByAttribute(nodes, attribute, value, list);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("일치하는 요소를 찾을 수 없습니다.");
        }
        return list;
    }

    public List<Node> elementByTag(String tag) {
        List<Node> list = new ArrayList<>();
        searchTreeByTag(nodes, tag, list);

        if (list.isEmpty()) {
            throw new IllegalArgumentException("일치하는 요소를 찾을 수 없습니다.");
        }
        return list;
    }

    private void searchTreeByAttribute(List<Node> nodes, String attribute, String value, List<Node> list) {
        for (Node e : nodes) {
            if (e.attribute.contains(attribute) && e.value.contains(value)) {
                list.add(e);
            }

            if (!e.children.isEmpty()) {
                searchTreeByAttribute(e.children, attribute, value, list);
            }
        }
    }

    private void searchTreeByTag(List<Node> nodes, String tag, List<Node> list) {
        for (Node e : nodes) {
            if (e.element.equals(tag)) {
                list.add(e);
            }
            if (!e.children.isEmpty()) {
                searchTreeByTag(e.children, tag, list);
            }
        }
    }

    public void add(Node e) {
        nodes.add(e);
    }

    public String toStr() {
        StringBuffer sb = new StringBuffer();
        draw(nodes, 0, sb);
        return sb.toString();
    }

    private void draw(List<Node> nodes, int depth, StringBuffer sb) {
        for (Node e : nodes) {
            drawNode(e, depth, sb);
            if (!e.children.isEmpty()) {
                draw(e.children, depth + 1, sb);
                append(depth + 1, sb, "]\n");
                append(depth, sb, "}\n");
            }
        }

    }

    private void drawNode(Node node, int depth, StringBuffer sb) {
        append(depth, sb, "{ \n");
        append(depth, sb, "element: \'" + node.element + "\',\n");
        if (!node.text.isEmpty()) {
            append(depth, sb, "text : \'" + node.text + "\' ,\n");
        }
        if (!node.attribute.isEmpty() && !node.value.isEmpty()) {
            append(depth, sb, "attributes: [\n");
            append(depth+1, sb, "{\n");
            for (int i = 0; i < node.attribute.size(); i++) {
                append(depth+1, sb, "name : " + node.attribute.get(i) + ", value : \"" + node.value.get(i) + "\"\n");
            }
            append(depth+1, sb, "}\n");
            append(depth, sb, "]\n");
        }
        if (!node.children.isEmpty()) {
            append(depth, sb, "children : [\n");
        }
    }

    private static void append(int depth, StringBuffer sb, String str) {
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        sb.append(str);
    }
}
