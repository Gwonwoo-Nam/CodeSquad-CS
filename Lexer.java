import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.Stack;

public class Lexer {

    /**
     * tokenSet
     * key : 이름(element), value : attribute으로 저장
     */
    public static final Stack<Node> tokenSet = new Stack();
    public static final Stack<String> bracket = new Stack();
    public static final List<Node> nodes = new LinkedList<>();

    /**
     * Stack
     * token을 차례대로 stack에 저장
     * Stack의 맨 위의 예약어에 따라 동작을 구현
     * ! : 다음 Bracket까지의 keywords를 skip한다.
     * Keyword Token : 새로운 토큰이 연속으로 오는 경우
     *  - 첫번째 토큰을 node에 저장
     *  - 등호(=)가 오는지 확인
     *  - 두번째 토큰을 value에 저장
     */

    public static void run(List<String> tokens) {
        Iterator tokenList = tokens.iterator();
        while (tokenList.hasNext()) {
            String token = (String)tokenList.next();

            // 주석 처리
            if (token.equals("!") || token.equals("?")) {
                while (!token.equals(">")) {
                    token = (String)tokenList.next();
                }
                bracket.pop();
                continue;
            }

            /**
             * 여는 괄호 처리 - Bracket Stack에 추가
             */
            if (token.equals("<")) {
                bracket.push("<");
                continue;
            }
            /**
             * 닫는 괄호 처리 - Bracket Stack에서 제거
             * 가장 위의 토큰의 특성을 더 이상 수정할 수 없는 상태로 만든다.
             */
            if (token.equals(">")) {
                if (!bracket.isEmpty() && bracket.peek().equals("<")) {
                    bracket.pop();
                }
                if (!tokenSet.isEmpty())
                    tokenSet.peek().unmodify();
                continue;
            }

            /**
             * 예외 처리 : BR은 바로 노드에 추가(Closing Tag를 생략)
             */
            if (token.equals("BR/")) {
                nodes.add(new Node("BR"));
                continue;
            }

            /**
             * Closing Tag 처리 : token Stack의 최근 값과 일치하면 pop
             */

            if (!tokenSet.isEmpty()) {
                String name = tokenSet.peek().element;
                if (token.substring(1).equals(name)) {
                    tokenSet.pop();
                    continue;
                }
            }

            /**
             * text element 처리 : Bracket stack이 비어 있는 경우
             * node의 text 요소에 추가
             */

            if (bracket.isEmpty()) {
                tokenSet.peek().text = token;
                continue;
            }

            /**
             * 속성 처리 : bracket stack에 "<"이 있는 경우
             * attribute, value 값을 순차적으로 추가
             */

            if (!bracket.isEmpty() && bracket.peek().equals("<")) {
                if (!tokenSet.isEmpty() && tokenSet.peek().isAttributeEmpty()) {
                    tokenSet.peek().attribute.add(token);
                    token = (String)tokenList.next();
                }
                if (token.equals("=")) {
                    token = (String)tokenList.next();
                }
                if (!tokenSet.isEmpty() && tokenSet.peek().isValueEmpty()) {
                    tokenSet.peek().value.add(token);
                    continue;
                }
            }

            /**
             * 그 외의 항목은 새로운 Keyword로 node에 추가
             */

            Node e = new Node(token);
            if (!tokenSet.isEmpty()) {
                tokenSet.peek().children.add(e); //아직 안닫혀있으면 children에 추가
            }
            tokenSet.push(e);
            nodes.add(e);
        }
    }

    public static List<Node> getNodes() {
        return nodes;
    }

    public static String toStr() {
        StringBuffer sb = new StringBuffer();

        for (Node a : nodes) {
            sb.append("element: \'" + a.element + "\',\n");
            if (!a.text.isEmpty()) {
                sb.append("text : \'" + a.text + "\' ,\n");
            }
            if (!a.attribute.isEmpty() && !a.value.isEmpty()) {
                sb.append("attributes: [\n");
                sb.append("\t {");
                for (int i = 0; i < a.attribute.size(); i++) {
                    sb.append("name : " + a.attribute.get(i) + ", value : \"" + a.value.get(i) + "\"\n");
                }
                sb.append("}\n");
                sb.append("]\n");
            }
            if (!a.children.isEmpty()) {
                sb.append("child : [");
                for (int i = 0; i < a.children.size(); i++) {
                    sb.append(a.children.get(i).element);
                    if (i != a.children.size() - 1 && a.children.size() != 1) {
                        sb.append(", ");
                    }
                }
                sb.append("]\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

