import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

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
        int pointer = 0;
        while (count-- > 0) {
            if (typeSize < 8) {
                objSize += 8;
                continue;
            }
            objSize += typeSize;
            pointer += 8;
        }
        heapMemory.add(new Variable(type, objSize, pointer));
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

    public int getAddress() {
        int addr = 0;
        for (Variable variable : heapMemory) {
            addr += variable.addr;
        }
        return addr;
    }


}
