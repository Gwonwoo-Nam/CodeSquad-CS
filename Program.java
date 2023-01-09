import java.util.Arrays;

public class Program {
    public static void main(String args[]) {
        Memory memory = new Memory();
        memory.setData();
        CpuSimulator cpu = new CpuSimulator(memory);

        cpu.runPipeLine();
        System.out.println(Arrays.toString(cpu.dump()));

    }
}
