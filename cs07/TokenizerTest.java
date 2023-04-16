import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import jdk.jfr.Percentage;

class TokenizerTest {

    //@ParameterizedTest()
    @Test
    void run1() {
        String str = "<!DOCTYPE html><HTML lang=\"ko\"><BODY><P>BOOST<IMG SRC=\"codesquad.kr\"></IMG><BR/></P></BODY></HTML>";
        Tokenizer.run(str);

        System.out.println(Tokenizer.getTokens());
    }
    @Test
    void run2() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\"><plist version=\"1.0\"><dict><key>CFBundleDevelopmentRegion</key><string>$(DEVELOPMENT_LANGUAGE)</string><key>CFBundleExecutable</key><string>boost</string><key>CFBundleName</key><string>camp</string></dict></plist>";
        Tokenizer.run(str);
        System.out.println(Tokenizer.getTokens());
    }

    @Test
    void addToken() {
    }

    @Test
    void testAddToken() {
    }
}
