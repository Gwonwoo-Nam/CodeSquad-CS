import java.util.Arrays;

class Convertor {
    public boolean[] dec2bin(int decimal) {
        boolean[] answer = {};
        return answer;
    }

    public int bin2dec(boolean[] bin) {
        int answer = 0;
        return answer;
    }
}

class TestApplication {
    static int dec2binTest1 = 10;
    static int dec2binTest2 = 173;
    static boolean[] dec2binTestAnswer1 = {false, true, false, true};
    static boolean[] dec2binTestAnswer2 = {true,false,true,true,false,true,false,true};
    static boolean[] bin2decTest1 = {false, true, true, true};
    static boolean[] bin2decTest2 = {true,true,true,true,false,true,false,true};
    static int bin2decTestAnswer1 = 14;
    static int bin2decTestAnswer2 = 175;
    public static void main() {
        Convertor convertor = new Convertor();

        System.out.println(convertor.dec2bin(dec2binTest1)+"정답 :"+dec2binTestAnswer1);
        System.out.println(convertor.dec2bin(dec2binTest2)+"정답 :"+dec2binTestAnswer2);
        System.out.println(convertor.bin2dec(bin2decTest1)+"정답 :"+bin2decTestAnswer1);
        System.out.println(convertor.bin2dec(bin2decTest2)+"정답 :"+bin2decTestAnswer2);
    }
}
