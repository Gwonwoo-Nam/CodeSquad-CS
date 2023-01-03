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

    public boolean[] sumBinary(boolean[] a, boolean[] b) {
        boolean[] sum = xorBooleans(a, b);
        boolean[] carry = shiftRight(andBooleans(a, b));
        if (isZero(carry)) {
            return sum;
        }
        return sumBinary(carry, sum);
    }

    private boolean[] andBooleans(boolean[] a, boolean[] b) {
        int minLength = Math.min(a.length, b.length);
        boolean[] result = new boolean[minLength];
        for (int i = 0; i < minLength; i++) {
            result[i] = a[i] & b[i];
        }
        result = trim(result);
        return result;
    }

    private boolean[] xorBooleans(boolean[] a, boolean[] b) {
        int minLength = Math.min(a.length, b.length);
        int maxLength = Math.max(a.length, b.length);
        boolean[] result = new boolean[maxLength];
        for (int i = 0; i < minLength; i++) {
            result[i] = a[i] ^ b[i];
        }
        sumRest(a, minLength, result);
        sumRest(b, minLength, result);

        result = trim(result);
        return result;
        }

    /**
    * 사이즈가 다른 경우 나머지 XOR 연산
    */

    private void sumRest(boolean[] a, int minLength, boolean[] result) {
        if (a.length > minLength) {
            for (int i = minLength; i < a.length; i++) {
                result[i] = a[i];
            }
        }
    }

    /**
    * 비트 빈 값 Trim
    */

    private boolean[] trim(boolean[] bin) {
        int trimCount = 0;
        for (int index = bin.length - 1; index >= 0; index--) {
            if (bin[index] == true) {
                break ;
            }
            trimCount++;
        }
        boolean[] result = new boolean[bin.length - trimCount];
        for (int index = 0; index < bin.length - trimCount; index++) {
            result[index] = bin[index];
        }
        return result;
    }
    
    /**
    * 비트 Shift 연산 구현
    */

    private boolean[] shiftRight(boolean[] bin) {
        boolean[] result = new boolean[bin.length + 1];
        result[0] = false;
        for (int i = 0; i < bin.length; i++) {
            result[i+1] = bin[i];
        }
        return result;
    }

    private boolean isZero(boolean[] bin) {
        int sum = 0;
        for (int index = 0; index < bin.length; index++) {
            sum += power(index) * toInt(bin[index]);
        }
        return (sum == 0);
    }
    /**
         * 출력 관련 함수
    */

    public void printBin(boolean[] bin) {
        System.out.println(Arrays.toString(bin));
    }

    public void printDec(int dec) {
        System.out.println(dec);
    }
}
