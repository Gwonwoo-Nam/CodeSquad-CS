public class Program {

    public static void main(String[] args) {

        Memory memory = new Memory();

        int base = memory.init(1024, 1024);
        memory.setSize("short", 4);
        memory.setSize("int", 8);
        memory.setSize("string", 16);
        int arrayPointer = memory.malloc("int", 4);
        int shortPointer = memory.malloc("short", 5);
        memory.heapDump();
        memory.call("foo", 2);
        int string1 = memory.malloc("crong", 1);
        memory.callstack();
        memory.call("bar", 1);

        int string2 = memory.malloc("jk", 2);

        System.out.println(memory.usage());
        memory.returnFrom("bar");
        try {
            memory.free(string1);
            memory.heapDump();
            memory.free(string2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        memory.callstack();

        memory.garbageCollect();

        System.out.println(memory.usage());
        memory.heapDump();

        memory.reset();
        System.out.println(memory.usage());
    }

}
