public class Alu {
    public Register accumulator = new Register();

    public int add(int accVal, int bufferVal) {
        return accVal + bufferVal;
    }

    public int sub(int accVal, int bufferVal) {
        return accVal - bufferVal;
    }

    public int and(int accVal, int bufferVal) {
        return accVal & bufferVal;
    }

    public int or(int accVal, int bufferVal) {
        return accVal | bufferVal;
    }
}
