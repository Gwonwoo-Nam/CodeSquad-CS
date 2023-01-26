import java.util.List;

public class Parser {
    public static StringBuffer sb = new StringBuffer();

    public static void run(List<Node> nodes) {
        recur(nodes, 0);
    }

    private static void recur(List<Node> nodes, int depth) {
        for (Node e : nodes) {
            if (e.printed == false) {
                e.printed = true;
                print(e, depth);
                if (!e.children.isEmpty()) {
                    recur(e.children, depth + 1);
                    insertTab(depth + 1);
                    sb.append("}\n");
                    insertTab(depth);
                    sb.append("]\n");
                }
            }
        }
    }

    private static void insertTab(int depth) {
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
    }

    public static void print(Node node, int depth) {
        insertTab(depth);
        sb.append("{ \n");
        insertTab(depth);
        sb.append("element: \'" + node.element + "\',\n");

        if (!node.text.isEmpty()) {
            insertTab(depth);
            sb.append("text : \'" + node.text + "\' ,\n");
        }
        if (!node.attribute.isEmpty() && !node.value.isEmpty()) {
            insertTab(depth);
            sb.append("attributes: [\n");
            insertTab(depth);
            sb.append("{");
            for (int i = 0; i < node.attribute.size(); i++) {
                sb.append("\tname : " + node.attribute.get(i) + ", value : \"" + node.value.get(i) + "\"}\n");
            }
            insertTab(depth);
            sb.append("]\n");
        }

        if (!node.children.isEmpty()) {
            insertTab(depth);
            sb.append("children : [\n");
        }
    }

    public static String getStr() {
        return sb.toString();
    }
}

