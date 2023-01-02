import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Convertor {
    public boolean[] dec2bin(int decimal) {
        List<Boolean> booleans = new ArrayList<>();

        int dividend = decimal;
        while (dividend >= 2) {
            booleans.add(toBoolean(dividend % 2));
            dividend = dividend / 2;
        }
        booleans.add(toBoolean(dividend % 2));

        boolean[] answer = new boolean[booleans.size()];
        for (int i = 0; i < booleans.size(); i++) {
            answer[i] = booleans.get(i);
        }
        return answer;
    }

    private boolean toBoolean(int decimal) {
        if (decimal == 1) {
            return true;
        }
        return false;
    }

    public int bin2dec(boolean[] bin) {
        int answer = 0;

        for (int index = 0; index < bin.length; index++) {
            answer += power(index) * toInt(bin[index]);
        }
        return answer;
    }

    private int toInt(boolean bin) {
        if (bin == true) {
            return 1;
        }
        return 0;
    }

    private int power(int index) {
       return (int)Math.pow(2,index);
    }
}

class TestApplication {
    static int dec2binTest1 = 10;
    static int dec2binTest2 = 173;
    static boolean[] dec2binTestAnswer1 = {false, true, false, true};
    static boolean[] dec2binTestAnswer2 = {true, false, true, true, false, true, false, true};
    static boolean[] bin2decTest1 = {false, true, true, true};
    static boolean[] bin2decTest2 = {true, true, true, true, false, true, false, true};
    static int bin2decTestAnswer1 = 14;
    static int bin2decTestAnswer2 = 175;

    public static void main(String[] args) {
        Convertor convertor = new Convertor();

        System.out.println(Arrays.toString(convertor.dec2bin(dec2binTest1)) + " 정답 : " + Arrays.toString(dec2binTestAnswer1));
        System.out.println(Arrays.toString(convertor.dec2bin(dec2binTest2)) + " 정답 : " + Arrays.toString(dec2binTestAnswer2));
        System.out.println(convertor.bin2dec(bin2decTest1) + " 정답 : " + bin2decTestAnswer1);
        System.out.println(convertor.bin2dec(bin2decTest2) + " 정답 : " + bin2decTestAnswer2);
    }
}
