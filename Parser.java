import java.util.List;

public class Parser {

    public static DOM run(List<Node> nodes) {
        DOM dom = new DOM();
        for (Node e : nodes) {
            if (e.printed == false) {
                e.printed = true;
                dom.add(e);
                recur(e.children, 1);
            }

        }
        return dom;
    }

    private static void recur(List<Node> nodes, int depth) {
        for (Node e : nodes) {
            if (e.printed == false) {
                e.printed = true;
                if (!e.children.isEmpty()) {
                    recur(e.children, depth + 1);
                }
            }
        }
    }
}

