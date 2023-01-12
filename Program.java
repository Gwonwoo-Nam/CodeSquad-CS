public class Program {

    public static void main(String[] args) {


        Memory memory = new Memory();

        int base = memory.init(1024, 1024);
        //base.printAddress();
        memory.setSize("short", 4);
        memory.setSize("int", 8);
        memory.setSize("string", 16);
        int arrayPointer = memory.malloc("int", 4);
        int shortPointer = memory.malloc("short", 5);
        memory.heapDump();
        memory.call("foo", 2);
        memory.call("bar", 2);
        memory.call("dap", 2);
        int string1 = memory.malloc("crong", 1);
        memory.returnFrom("dap");
        memory.callstack();
        memory.heapDump();

        //arrayPointer.printAddress();
        //shortPointer.printAddress();
    }

}
