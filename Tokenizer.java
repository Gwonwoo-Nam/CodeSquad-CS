import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
    private static List<String> tokens = new LinkedList<>();
    private static int cursor;

    public static void run(String str) {

        while (cursor < str.length()) {
            if (cursor == str.length()) {
                break;
            }
            if (addToken(str, "[a-z]|[A-Z]|[_]|[0-9]|[/($)]")) { //Keywords : alphabet + underbar
                continue;
            }
            if (addStringToken(str)) { //" " 문자열
                continue;
            }
            if (skipToken(str, ' ')) { //공백 skip
                continue;
            }
            addToken(str); //그 외 identifier(특수 문자)
        }
    }

    public static boolean skipToken(String str, char target) {
        if (str.charAt(cursor) == target) {
            cursor++;
            return true;
        }
        return false;
    }

    public static void addToken(String str) {
        tokens.add(String.valueOf(str.charAt(cursor)));
        cursor++;
    }

    public static boolean addToken(String str, String regex) {
        int offset = 0;
        while (str.substring(cursor + offset, cursor + offset + 1).matches(regex)) {
            offset++;
        }
        if (offset != 0) {
            tokens.add(str.substring(cursor, cursor + offset));
            cursor += offset;
            return true;
        }
        return false;
    }

    public static boolean addStringToken(String str) {
        int offset = 0;
        if (str.charAt(cursor) == '\"') { //따옴표가 나오면
            cursor++;
            while (str.charAt(cursor + offset) != '\"') //따옴표가 나올 때 까지
            {
                offset++;
            }
            tokens.add(str.substring(cursor, cursor + offset));
            cursor += offset + 1;
            return true;
        }
        return false;
    }

    public static List<String> getTokens() {
        return tokens;
    }
}
