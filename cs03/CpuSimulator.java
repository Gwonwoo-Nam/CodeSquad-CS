public class CpuSimulator {
    public Register[] registers; // 8개의 레지스터 생성
    private ProgramCounter programCounter;
    public Register bufferRegister = new Register();
    public Register commandRegister = new Register();
    public Register addressRegister = new Register();
    private Alu alu; //ALU 계산 장치
    public Memory memory; //메모리를 참조

    public CpuSimulator(Memory memory) {
        registers = new Register[7];
        for (int index = 0; index < 7; index++) {
            registers[index] = new Register();
        }
        programCounter = new ProgramCounter();
        alu = new Alu();
        this.memory = memory;
    }

    public void reset() {
        for (int index = 0; index < 7; index++) {
            registers[index].set((short)0);
        }
        bufferRegister.set((short)0);
        commandRegister.set((short)0);
        programCounter.set((short)0);
        addressRegister.set((short)0);
    }

    public void runPipeLine() {
        while (true) {
            fetch(); //명령을 가져와서 memory buffer 레지스터에 등록
            if (!execute()) {
                break;
            }
        }
    }

    /**
     * fetch cycle
     * to : Memory Address Register ← PC
     *      PC에 존재하는 주소 값을 MAR에 전송
     * t1 : Memory Buffer Register ← Memory Address Register 주소 Memory 데이터
     *      PC ← PC + 명령어의 크기 단위
 *     t2 : IR ← Memory Buffer Register
     *      MBR에 일시 저장한 명령어를 실행하기 위해 IR로 전송
 *      t는 CPU 클록 주기를 의미한다.
     */
    private void fetch() {
        addressRegister.value = programCounter.get(); // t0
        bufferRegister.value = memory.get(addressRegister.get()); //t1
        programCounter.increment(); //16비트 증가
        commandRegister.value = bufferRegister.get(); //t2
    }

    private boolean execute() {
        if (commandRegister.get(0, 3) == (short)1) {
            loadByOffset(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)2) {
            loadByValue(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(11, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)3) {
            storeByOffset(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)4) {
            storeByValue(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(11, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)5) {
            and(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)6) {
            or(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)7) {
            addByOffset(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)8) {
            addByValue(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(11, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)9) {
            subByOffset(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(13, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)10) {
            subByValue(commandRegister.get(4, 6), commandRegister.get(7, 9), commandRegister.get(11, 15));
            return true;
        }
        if (commandRegister.get(0, 3) == (short)11) {
            mov(commandRegister.get(4, 6), commandRegister.get(7, 15));
            return true;
        }
        return false;
    }

    /**
     * execution Cycle
     * LOAD
     * t0 : Memory Address Register ← IR
     * t1 : Memory Buffer Register ← Memory(Memory Address Register)
     * t2 : Accumulator(여기에서는 destination Register) ← Memory Buffer Register
     *
     * Memory Address Register를 여기서에서는 Base Register와 Offset Register로 나누어서 배열에 접근함.
     */

    private void loadByOffset(short destReg, short baseReg, short offsetReg) {
        addressRegister.value = (short)(registers[baseReg - 1].value +
                registers[offsetReg - 1].value); // 버퍼 레지스터에 저장된 주소 값을 주소 레지스터에 저장
        bufferRegister.value = memory.get(addressRegister.value); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
        registers[destReg - 1].value = bufferRegister.value; //dest.reg에 저장
    }

    private void loadByValue(short destReg, short baseReg, short offsetVal) {
        addressRegister.value = (short)(registers[baseReg - 1].value + offsetVal); // 버퍼 레지스터에 저장된 주소 값을 주소 레지스터에 저장
        bufferRegister.value = memory.get(addressRegister.value); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
        registers[destReg - 1].value = bufferRegister.value; //dest.reg에 저장
    }

    private void storeByOffset(short srcReg, short baseReg, short offsetReg) {
        addressRegister.value = (short)(registers[baseReg - 1].value + registers[offsetReg - 1].value);
        bufferRegister.value = registers[srcReg - 1].value; // 주소 레지스터에 저장할 데이터의 주소를 저장
        memory.set(addressRegister.value, bufferRegister.value); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
    }

    private void storeByValue(short srcReg, short baseReg, short offsetVal) {
        addressRegister.value = (short)(registers[baseReg - 1].value + offsetVal);
        bufferRegister.value = registers[srcReg - 1].value; // 주소 레지스터에 저장할 데이터의 주소를 저장
        memory.set(addressRegister.value, bufferRegister.value); //주소 레지스터에 해당하는 메모리 값을 불러와 버퍼 레지스터에 저장
    }

    /**
     * t0 : Memory Address Register ← IR
     * t1 : Memory Buffer Register ← M(Memory Address Register)
     * t2 : AC ← AC Operand MBR
     *
     * Basic 컴퓨터는 CPU에서 계산이 이루어지는 원리는 누산기 레지스터 저장 되어있는 값과 메모리 버퍼 레지스터의 값을 로드하여 ALU에서 연산한다.
     * 이 문제에서 누산기를 Register 중 하나로 묶어놓았음
     */

    private void and(short destReg, short opReg1, short opReg2) {
        registers[destReg - 1].value = (short)alu.and(registers[opReg1-1].value, registers[opReg2-1].value);
    }

    private void or(short destReg, short opReg1, short opReg2) {
        registers[destReg - 1].value = (short)alu.or(registers[opReg1-1].value, registers[opReg2-1].value);
    }

    private void addByOffset(short destReg, short opReg1, short opReg2) {
        registers[destReg - 1].value = (short)alu.add(registers[opReg1-1].value, registers[opReg2-1].value);
    }

    private void addByValue(short destReg, short opReg, short opVal) {
        registers[destReg - 1].value = (short)alu.add(registers[opReg-1].value, opVal);
    }
    private void subByOffset(short destReg, short opReg1, short opReg2) {
        registers[destReg - 1].value = (short)alu.sub(registers[opReg1-1].value, registers[opReg2-1].value);
    }

    private void subByValue(short destReg, short opReg, short opVal) {
        registers[destReg - 1].value = (short)alu.sub(registers[opReg-1].value, opVal);
    }

    private void mov(short destReg, short opVal) {
        registers[destReg - 1].value = opVal;
    }

    public int[] dump() {
        int[] values = new int[7];
        //values = Arrays.stream(registers).mapToInt(Register::get).toArray();
        for (int i = 0; i < registers.length; i++) {
            values[i] = registers[i].get();
        }
        return values;
    }
}
