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

    public void printBin(boolean[] bin) {
        System.out.println(Arrays.toString(bin));
    }

    public void printDec(int dec) {
        System.out.println(dec);
    }
}
