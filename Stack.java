import java.util.LinkedList;


public class Stack {
    public int stackPointer = 0;
    public LinkedList<Pointer> callstacks = new LinkedList<>();

    public int maxSize;

    public Stack(int size) {
        maxSize = size;
    }

    public void call(Pointer pointer) {
        callstacks.add(pointer);
        stackPointer += pointer.size;
    }

    public int searchHeap(int address) {
        return callstacks.get(address / 4 - 1).variable.addr;
    }

    public int getSpace() {
        int space = 0;
        for (Pointer pointer : callstacks) {
            space += pointer.size;
        }
        return space;
    }

    public LinkedList<Pointer> getCallstacks() {
        return callstacks;
    }


}
