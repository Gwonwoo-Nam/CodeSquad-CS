public class Register {
    public short value = 0; //최대 16 Bit size(4Byte)

    public short get(int startInclusive, int endInclusive) {
        String subBin = View.toBinary(value, startInclusive, endInclusive);
        short sum = 0;
        for (int i = 0; i < subBin.length(); i++) {
            sum = (short)(2 * sum + (subBin.charAt(i) - '0'));
        }
        return sum;
    }
    public short get() {
        return value;
    }

    public void set(short value) {
        this.value = value;
    }

}


