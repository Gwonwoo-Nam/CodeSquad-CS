import java.util.List;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void run1() {
        String str = "<!DOCTYPE html><HTML lang=\"ko\"><BODY><P>BOOST<IMG SRC=\"codesquad.kr\"></IMG><BR/></P></BODY></HTML>";
        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        Parser.run(nodes);
        System.out.println(Parser.getStr());
    }
    @Test
    void run2() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\"><dict><key>CFBundleDevelopmentRegion</key><string>$(DEVELOPMENT_LANGUAGE)</string><key>CFBundleExecutable</key><string>boost</string><key>CFBundleName</key><string>camp</string></dict></plist>";
        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        Parser.run(nodes);
        System.out.println(Parser.getStr());
    }

}
