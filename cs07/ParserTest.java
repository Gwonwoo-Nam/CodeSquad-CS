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
        DOM dom = Parser.run(nodes);
        System.out.println(dom.toStr());
    }

    @Test
    void run2() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\"><dict><key>CFBundleDevelopmentRegion</key><string>$(DEVELOPMENT_LANGUAGE)</string><key>CFBundleExecutable</key><string>boost</string><key>CFBundleName</key><string>camp</string></dict></plist>";
        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        DOM dom = Parser.run(nodes);
        System.out.println(dom.toStr());
    }

    @Test
    void run3() {
        String str = "<!doctype html>\n"
                + "<html dir=\"ltr\" lang=\"ko\">\n"
                + "  <head>\n"
                + "    <meta charset=\"utf-8\"></meta>\n"
                + "    <title>newtab</title></head>\n"
                + "  <body>\n"
                + "    <iframe id=\"backgroundImage\" src=\"chrome-untrusted://new-tab-page/custom_background_image?url=https%3A%2F%2Flh4.googleusercontent.com%2Fproxy%2FUOhQwfclsAK8TnXZqoTkh9szHvYOJ3auDH07hZBZeVaaRWvzGaXpaYl60MfCRuW_S57gvzBw859pj5Xl2pW_GpfG8k2GhE9LUFNKwA%3Dw3840-h2160-p-k-no-nd-mv\"></iframe>\n"
                + "    <ntp-app></ntp-app>\n"
                + "    <script type=\"module\" src=\"new_tab_page.js\"></script>\n"
                + "    <link rel=\"stylesheet\" href=\"chrome://resources/css/text_defaults_md.css\"></link>\n"
                + "    <link rel=\"stylesheet\" href=\"chrome://theme/colors.css?sets=ui,chrome\"></link>\n"
                + "    <link rel=\"stylesheet\" href=\"shared_vars.css\"></link>\n"
                + "  </body>\n"
                + "</html>";
        Tokenizer.run(str);
        System.out.println(Tokenizer.getTokens());
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        DOM dom = Parser.run(nodes);
        System.out.println(dom.toStr());
    }

    @Test
    void elementByAttribute() {
        String str = "<!DOCTYPE html><HTML lang=\"ko\"><BODY><P>BOOST<IMG SRC=\"codesquad.kr\"></IMG><BR/></P></BODY></HTML>";
        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        DOM dom = Parser.run(nodes);
        List<Node> node = dom.elementByAttribute("lang", "ko");
        for (Node e : node) {
            System.out.println(e.toStr());
        }
    }

    @Test
    void elementByTag() {
        String str = "<!DOCTYPE html><HTML lang=\"ko\"><BODY><P>BOOST<IMG SRC=\"codesquad.kr\"></IMG><BR/></P></BODY></HTML>";
        Tokenizer.run(str);
        List<String> tokens = Tokenizer.getTokens();

        Lexer.run(tokens);
        List<Node> nodes = Lexer.getNodes();
        DOM dom = Parser.run(nodes);
        List<Node> node = dom.elementByTag("P");
        for (Node e : node) {
            System.out.println(e.toStr());
        }
    }

}
