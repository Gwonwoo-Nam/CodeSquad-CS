import java.util.LinkedList;

public class Memory {

    private Heap heap; //8Byte 단위로 저장됨
    public Stack stack;
    public boolean callBool = false;


    /**
     * Base address를 리턴한다. 나머지 포인터 주소는 base address에 대한 offset으로 나타낸다.
     */
    public int init(int stackSize, int heapSize) {
        stack = new Stack(stackSize);
        heap = new Heap(heapSize);
        return Stack.stackPointer;
    }

    /**
     * setSize type별로 고유한 사이즈를 가지도록 등록
     *
     * @param type
     * @param length
     */

    public void setSize(String type, int length) {
        UserTypes.add(type, length);
    }

    /**
     * malloc heap에는 8바이트 단위로 메모리를 할당 호출 시 heap을 8바이트 단위로 sweep하면서 메모리 할당이 가능한 공간을 탐색
     */

    public int malloc(String type, int count) {

        int heapPointer = heap.pointer;
        int objSize = heap.add(type, count);

        // Stack에 포인터 생성하기
        Stack.call(new Pointer(new Variable(type, objSize), heapPointer));
        return Stack.stackPointer;
    }

    public int free(Pointer pointer) {

    }

    public void heapDump() {
        int stackAddr = 0;
        LinkedList<Pointer> stacks = Stack.getCallstacks();
        for (Pointer pointer : Stack.getCallstacks()) {
            if (pointer.call == false) {
                System.out.println("Type Name : " + pointer.variable.typeName);
                System.out.println("Object Size(Byte) : " + pointer.variable.size);
                System.out.println("Heap Address : " + View.printAddress(pointer.pointingAddr));
                System.out.println("Stack Pointer Address : " + View.printAddress(stackAddr));
                System.out.println("------------------------");

            }
            stackAddr += 32;
        }
    }

    public void call(String name, int paramCount) {
        callBool = true;
        for (int i = 0; i < paramCount; i++) {
            Stack.call(new Pointer(name));
        }
    }

    public void callstack() {
        int stackAddr = 0;
        LinkedList<Pointer> stacks = Stack.getCallstacks();
        for (Pointer pointer : stacks) {
            if (pointer.call == true) {
                System.out.print(pointer.callStackName + "() " + View.printAddress(stackAddr));
                System.out.println(" -> ");
            }
            stackAddr += 32;
        }
    }

    public void returnFrom(String name) {
        if (callBool == false) {
            return ;
        }
        LinkedList<Pointer> stacks = Stack.getCallstacks();
        for (int i = stacks.size() - 1; i>=0; i--) {
            if (stacks.get(i).call == false) {
                Stack.stackPointer -= 32;
                Stack.callstacks.removeLast();
                continue ;
            }
            if (stacks.get(i).callStackName.equals(name)) {
                Stack.stackPointer -= 32;
                Stack.callstacks.removeLast();
                break ;
            }
            throw new IllegalArgumentException("가장 최근의 호출 call이 아닙니다.");
        }
    }


}

