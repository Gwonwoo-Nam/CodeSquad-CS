import java.util.List;

public class Controller {

    public static void run() {
        String str = "<!DOCTYPE html><HTML lang=\"ko\"><BODY><P>BOOST<IMG SRC=\"codesquad.kr\"></IMG><BR/></P></BODY></HTML>";

        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();
        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        Parser.run(nodes);
    }


}
