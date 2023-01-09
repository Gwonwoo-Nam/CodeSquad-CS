import java.util.Arrays;

public class CpuSimulator {
    public Register[] registers; // 8개의 레지스터 생성
    private ProgramCounter programCounter;
    public Register bufferRegister = new Register();
    public Register commandRegister = new Register();
    public Register addressRegister = new Register();
    private Alu alu; //ALU 계산 장치
    public Memory memory; //메모리를 참조
    private ControlUnit controlUnit;

    public CpuSimulator(Memory memory) {
        registers = new Register[8];
        for (int index = 0; index < 8; index++) {
            registers[index] = new Register((char)index);
        }
        programCounter = new ProgramCounter();
        alu = new Alu();
        this.memory = memory;
        controlUnit = new ControlUnit();
    }

    public void reset() {
        for (int index = 1; index < 8; index++) {
            registers[index].set((char)0);
        }
        bufferRegister.set((char)0);
        commandRegister.set((char)0);
        programCounter.set((char)0);
        addressRegister.set((char)0);
    }

    public void runPipeLine() {
        fetch(); //명령을 가져와서 memory buffer 레지스터에 등록
        commandRegister.set(decode(bufferRegister)); //buffer 레지스터의 명령을 해석해서 instruction을 명령어 레지스터에 저장
        execute();
    }

    private void fetch() {
        bufferRegister.set(memory.get(programCounter.get())); //buffer 레지스터에 명령 저장
        programCounter.increment(); //16비트 증가
    }

    private char decode(Register bufferRegister) {
        return bufferRegister.get(0, 3);
    }

    private void execute() {
        if (commandRegister.get() == (char)1) {
            loadByOffset();
        }
        if (commandRegister.get() == (char)2) {
            loadByValue();
        }/*
        if (bufferRegister.get(0, 3) == (char)4) {
            executeAnd();
        }
        if (bufferRegister.get(0, 3) == (char)5) {
            executeOr();
        }*/
    }

    private void loadByOffset() {
        addressRegister.set((char)(bufferRegister.get(7, 9) + bufferRegister.get(13, 15))); // 버퍼 레지스터에 저장된 주소 값을 주소 레지스터에 저장
        bufferRegister.set(memory.get(addressRegister.get())); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
        controlUnit.setAccumulator(bufferRegister.get()); //버퍼 레지스터에 저장된 값을 누산기에 저장
        registers[bufferRegister.get(4, 6)].set(bufferRegister.get()); //dest.reg에 저장
    }

    private void loadByValue() {
        addressRegister.set((char)(bufferRegister.get(7, 9) + bufferRegister.get(11, 15))); // 버퍼 레지스터에 저장된 주소 값을 주소 레지스터에 저장
        bufferRegister.set(memory.get(addressRegister.get())); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
        controlUnit.setAccumulator(bufferRegister.get()); //버퍼 레지스터에 저장된 값을 누산기에 저장
        registers[bufferRegister.get(4, 6)].set(bufferRegister.get()); //dest.reg에 저장
    }

    public int[] dump() {
        int[] values = new int[8];
        //values = Arrays.stream(registers).mapToInt(Register::get).toArray();
        for(int i=0; i< registers.length;i++) {
            values[i]=(char)registers[i].get();
        }
        return values;
    }
}
