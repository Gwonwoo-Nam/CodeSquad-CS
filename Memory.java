public class Memory {
    /**
     * Memory의 용량이 1024Byte라고 가정 (500 words)
     * 메모리 word 단위는 2Byte(16bit)으로, char 사용해서 나타냄
     * data index는 memory의 Instruction Line Number를 나타냄
     */
    private char[] data = new char[512]; //1kb 메모리

    public char get(char address) {
        return data[address];
    }

    public void set(char address, char value) {
        data[address] = value;
    }

    public char bin2Char(String binary) {
        binary = binary.replaceAll(" ", "");
        char sum = 0;
        for (int i = 0; i < binary.length(); i++) {
            sum = (char)(2 * sum + (binary.charAt(i) - '0'));
        }
        return sum;
    }

    public void print() {
        System.out.println("--- 메모리 영역 출력 --- \nAddr\t:\tData");
        for (int i =0;i<data.length;i++) {
            StringBuffer toHex = new StringBuffer(Integer.toBinaryString(data[i]));
            while (toHex.length() != 16) {
                toHex.insert(0,0);
            }
            System.out.println(i +"\t : \t" + toHex);
        }
    }
}
