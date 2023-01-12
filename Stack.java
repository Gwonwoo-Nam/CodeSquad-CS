import java.util.LinkedList;


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

    public static int searchHeap(int address) {
        return callstacks.get(address / 8).pointingAddr;
    }

    public static LinkedList<Pointer> getCallstacks() {
        return callstacks;
    }


}
