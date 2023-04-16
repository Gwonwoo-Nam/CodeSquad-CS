import java.util.Arrays;

public class View {
    public static void printDump(CpuSimulator cpuSimulator) {
        System.out.println(Arrays.toString(cpuSimulator.dump()));
    }

    public static void printMemory(Memory memory) {
        System.out.println("--- 메모리 영역 출력 --- \nAddr\t:\tData");
        for (int i = 0; i < memory.size(); i++) {
            StringBuffer binary = new StringBuffer(toBinary(memory.get((short)i),0,15));
            while (binary.length() != 16) {
                binary.insert(0, 0);
            }
            System.out.println(i + "\t : \t" + binary);
        }
    }

    public static String toBinary(int value, int startInclusive, int endInclusive) {
        StringBuffer binary = new StringBuffer();
        if (value >= 0) {
            binary.append(Integer.toBinaryString(value));
            while (binary.length() != 16) {
                binary.insert(0,0);
            }
        }
        if (value < 0) {
            binary.append(Integer.toBinaryString(value*(-1)));
            while (binary.length() != 15) {
                binary.insert(0,0);
            }
            binary.insert(0,1);
        }
        return binary.substring(startInclusive, endInclusive + 1).toString();
    }
}

