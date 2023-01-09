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

    public void setData() {
        data[0] = 4739;
        data[1] = 15044;
        data[2] = 11966; //LOAD R7 R2 #30
        data[5] = 11;

    }
}
