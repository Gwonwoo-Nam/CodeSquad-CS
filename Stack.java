import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Stack {
    private static byte[] stackMemory; //4Byte Pointer + 1,2,4,8,16,32Byte Type
    public static int stackPointer = 0;

    public static LinkedList<Pointer> callstacks = new LinkedList<>();


    public Stack(int size) {
        stackMemory = new byte[size];
    }


    public static void call(Pointer pointer) {
        callstacks.add(pointer);
        stackPointer += pointer.size;
    }

    public static LinkedList<Pointer> getCallstacks() {
        return callstacks;
    }




}
