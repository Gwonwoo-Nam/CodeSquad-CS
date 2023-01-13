import java.util.LinkedList;

public class Heap {

    public LinkedList<Variable> heapMemory = new LinkedList<>(); //4Byte Pointer + 1,2,4,8,16,32Byte Type
    public int maxSize;

    public Heap(int size) {
        maxSize = size;
    }

    public int add(String type, int count) {
        int typeSize = UserTypes.getSize(type);
        if (typeSize == 0) {
            UserTypes.add(type, 4);
        }

        int objSize = 0;
        while (count-- > 0) {
            if (typeSize < 8) {
                objSize += 8;
                continue;
            }
            objSize += typeSize;
        }
        heapMemory.add(new Variable(type, objSize, getAddressAtIndex(heapMemory.size())));
        return objSize;
    }

    public void remove(int pointingAddr) {
        int heapAddr = 0;
        int index = 0;
        while (heapAddr != pointingAddr) {
            heapAddr += heapMemory.get(index).size;
            index++;
        }
        heapMemory.remove(index);
    }

    public int getAddressAtIndex(int index) {
        int addr = 0;
        for (int i=0; i< index;i++) {
            addr += heapMemory.get(i).size;
        }
        return addr;
    }

    public void free(int address) {
        for (Variable variable : heapMemory) {
            if (variable.addr == address) {
                heapMemory.remove(variable);
            }
        }
    }

    public int getSpace() {
        int space = 0;
        for (Variable variable : heapMemory) {
            space += variable.size;
        }
        return space;
    }


}
