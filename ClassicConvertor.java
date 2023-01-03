import java.util.LinkedList;
import java.util.List;

public class ClassicConvertor {
    final static int MAX_ARRAY_SIZE = 9;
    final static int MAX_NUMBER_SIZE = 8;
    final static int CARRY = 1;
    final static int SUM = 0;

    public boolean[] sumBinary(boolean[] a, boolean[] b) {
        boolean[] result = new boolean[MAX_ARRAY_SIZE];
        a = init(a);
        b = init(b);

        List<Boolean> output = halfAdder(a[0], b[0]);
        boolean carryIn = output.get(CARRY);
        result[0] = output.get(SUM);

        for (int index = 1; index < MAX_NUMBER_SIZE; index++) {
            output = fullAdder(a[index], b[index], carryIn);
            result[index] = output.get(SUM);
            carryIn = output.get(CARRY);
            result[index + 1] = carryIn;
        }

        return result;
    }

    private boolean[] init(boolean[] input) {
        boolean[] inputNew = new boolean[MAX_ARRAY_SIZE];

        for (int index = 0; index < input.length; index++) {
            inputNew[index] = input[index];
        }
        return inputNew;
    }

    private List<Boolean> halfAdder(boolean a, boolean b) {
        final boolean sum = a ^ b; //SUM은 XOR
        final boolean carry = a & b; //CARRY는 AND
        List<Boolean> output = new LinkedList<>();
        output.add(sum);
        output.add(carry);
        return output;
    }

    private List<Boolean> fullAdder(boolean a, boolean b, boolean cin) {
        final boolean sum = a ^ b ^ cin; //SUM은 XOR
        final boolean carry = (a & b) | (b & cin) | (cin & a); //CARRY는 (A&B) OR (B&C) OR (C&A)
        List<Boolean> output = new LinkedList<>();
        output.add(sum);
        output.add(carry);
        return output;
    }

}
