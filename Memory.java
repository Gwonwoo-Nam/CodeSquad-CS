import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
        return stack.stackPointer;
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

        int heapAddress = heap.getAddressAtIndex(heap.heapMemory.size());
        int objSize = heap.add(type, count);

        // Stack에 포인터 생성하기
        stack.call(new Pointer(new Variable(type, objSize, heapAddress)));
        return stack.stackPointer;
    }

    public int free(int pointer) {
        if (pointer > stack.stackPointer) {
            throw new IllegalArgumentException("unthrowable");
        }
        int heapAddress = stack.searchHeap(pointer);
        heap.free(heapAddress);

        return heapAddress;
    }

    public void heapDump() {
        int stackAddr = 0;

        System.out.println("Heap 영역 Dump");
        for (Variable variable : heap.heapMemory) {
            System.out.println("Type Name : " + variable.typeName);
            System.out.println("Object Size(Byte) : " + variable.size);
            System.out.println("Heap Address : " + View.printAddress(variable.addr));
            System.out.println("Stack Pointer Address : " + View.printAddress(stackAddr));
            System.out.println("------------------------");

            stackAddr += 32;
        }
        System.out.println("\n\n");
    }

    public void call(String name, int paramCount) {
        callBool = true;
        for (int i = 0; i < paramCount; i++) {
            stack.call(new Pointer(name));
        }
    }

    public void callstack() {
        int stackAddr = 0;
        LinkedList<Pointer> stacks = stack.getCallstacks();
        System.out.println("Stack 영역 Dump");
        for (Pointer pointer : stacks) {
            if (pointer.call == true) {
                System.out.print(pointer.callStackName + "() " + View.printAddress(stackAddr));
                System.out.println(" -> ");
            }
            stackAddr += 32;
        }
        System.out.println("\n\n");
    }

    public void returnFrom(String name) {
        if (callBool == false) {
            return;
        }

        for (int i = stack.callstacks.size() - 1; i >= 0; i--) {
            if (stack.callstacks.get(i).call == false) {
                stack.stackPointer -= 4;
                stack.callstacks.removeLast();
                continue;
            }
            if (stack.callstacks.get(i).callStackName.equals(name)) {
                stack.stackPointer -= 4;
                stack.callstacks.removeLast();
                break;
            }
            throw new IllegalArgumentException("가장 최근의 호출 call이 아닙니다.");
        }
    }

    public List<String> usage() {
        List<Integer> usage = new ArrayList<>(List.of(stack.maxSize,stack.getSpace(),stack.maxSize-stack.getSpace(),heap.maxSize,heap.getSpace(),heap.maxSize-heap.getSpace()));
        return usage.stream().map(value -> Integer.toString(value)).collect(Collectors.toList());
    }

    public void garbageCollect() {
        for (Pointer pointer : stack.callstacks) {
            for (Variable variable : heap.heapMemory) {
                if (pointer.call == false && pointer.variable.addr == variable.addr) { //stack의 포인터가 가리키는 주소와 힙의 주소가 같을 때
                    variable.mark = true;
                }
            }
        }
        for (Variable variable : heap.heapMemory) {
            if (variable.mark == false) { //stack의 포인터가 가리키는 주소와 힙의 주소가 같을 때
                heap.heapMemory.remove(variable);
            }
        }
    }

    public void reset() {
        heap.heapMemory.clear();
        stack.callstacks.clear();

    }


}

