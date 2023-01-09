import java.util.Arrays;

public class Calculator extends Program{
    public static void main(String args[]) {
        Memory memory = new Memory();
        CpuSimulator cpuSimulator = new CpuSimulator(memory);
        insertProgram(memory);
        cpuSimulator.runPipeLine();
        memory.print();
        System.out.println(Arrays.toString(cpuSimulator.dump()));
    }

    public static void insertProgram(Memory memory) {
        memory.set((char)0, memory.bin2Char("1011 001 000000001")); //0x0000b MOV R1, 0x0001 - R1에 1를 담는다.
        memory.set((char)1, memory.bin2Char("1000 010 001 1 00010")); //0x0010b ADD R2, R1, #2 - R2에 1+2를 담는다.
        memory.set((char)2, memory.bin2Char("1000 011 010 1 00011")); //0x0020b ADD R3, R2, #3 - R3에 1+2+3을 담는다.
        memory.set((char)3, memory.bin2Char("1000 100 011 1 00100")); //0x0030b ADD R4, R3, #4 - R4에 1+2+3+4을 담는다.
        memory.set((char)4, memory.bin2Char("1000 101 100 1 00101")); //0x0040b ADD R5, R4, #5 - R5에 1+2+3+4+5를 담는다.

        memory.set((char)5, memory.bin2Char("0100 101 100 1 00010")); //0x0050b ADD R5, R4, #5 - 10 + 2 주소에 R5 값을 저장.
    }
}
