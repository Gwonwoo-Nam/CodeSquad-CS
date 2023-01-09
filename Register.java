public class Register {
    private char index; //000(PC) + 1~7 Reg
    public char value = 0; //최대 16 Bit size(4Byte)

    public Register(char index) {
        this.index = index;
    }

    public Register() {
    }

    public char get() {
        return value;
    }

    public char get(int startInclusive, int endInclusive) {
        int temp = value;
        int rightShift = 16 - 1 - endInclusive;
        int leftShift = 16 - 1 - startInclusive;

        int shifter = 1;
        while (leftShift > 0) {
            shifter = shifter*2 + 1;
            leftShift--;
        }
        temp = temp & shifter;
        temp = temp >> rightShift;


        return (char)temp;
    }

    public void set(char value) {
        this.value = value;
    }

}


