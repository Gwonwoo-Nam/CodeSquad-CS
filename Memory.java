public class Memory {
    /**
     * Memory의 용량이 1024Byte라고 가정 (500 words)
     * 메모리 word 단위는 2Byte(16bit)으로, short 사용해서 나타냄
     * data index는 memory의 Instruction Line Number를 나타냄
     */
    private short[] data = new short[512]; //1kb 메모리

    public short get(short address) {
        return data[address];
    }

    public int size() {
        return data.length;
    }

    public void set(short address, short value) {
        data[address] = value;
    }

    public short bin2Short(String binary) {
        binary = binary.replaceAll(" ", "");
        short sum = 0;
        for (int i = 1; i < binary.length(); i++) {
            sum = (short)(2 * sum + (binary.charAt(i) - '0'));
        }
        if (binary.charAt(0) == '1') {
            sum = (short)(sum * (-1));
        }

        return sum;
    }


}
