import java.util.Arrays;

public class Program {

    public static void main(String args[]) {
        Memory memory = new Memory();
        CpuSimulator cpuSimulator = new CpuSimulator(memory);
        insertProgram(memory);
        cpuSimulator.runPipeLine();
        memory.print();
        System.out.println(Arrays.toString(cpuSimulator.dump()));
    }

    public static void insertProgram(Memory memory) {
        memory.set((char)0, memory.bin2Char("1011 100 010100000")); //0x0000b MOV R4, 0x00A0 - R4에 160을 담는다.
        memory.set((char)1, memory.bin2Char("1011 101 000000010")); //0x0010b MOV R5, 0x0002 - R5에 2를 담는다.
        memory.set((char)2, memory.bin2Char("0001 001 100 000 101")); //0x0020b LOAD R1, R4, R5 - 162번째에서 데이터를 가져와서 R1
        memory.set((char)3, memory.bin2Char("1000 010 001 1 00100")); //0x0030b ADD R2, R1, #4 - R2에 (0+4)
        memory.set((char)4, memory.bin2Char("1001 011 001 000 010")); //0x0040b SUB R3, R1, R2 - R3에 (0 - 4) = -4 저장
        memory.set((char)5, memory.bin2Char("0100 011 100 1 00100")); //0x0050b STORE R3, R4, #4 (65532)를 164에 저장.
    }
}
