## 학습 계획서

### 학습 목표
- 컴퓨터를 구성하는 3가지 요소 중에서 CpuSimulator 부분에 대해 이해하고, 구현하는 것이 목표다.
- CpuSimulator 구성 요소에 대해 이해하고, CpuSimulator 명령어를 처리하도록 구현한다

### 학습 내용


### 기능 요구사항
객체 구현
- CpuSimulator Class : 기본 처리단위는 16비트(2Byte)
  - 레지스터 Class
    - 범용 사용 레지스터 (R1 ~ R7)
    - 프로그램 카운터 레지스터 (R0)
    - Operation Register는 문제에서 별도의 구분이 없음
  - Alu Class : 기초 연산만 존재
    - ADD
    - SUB
    - AND
    - OR
- Memory Class : 자료구조를 포함한 저장 클래스, Key(address)와 Value로 대응되는 자료구조

기능 구현
- [ ] LOAD : (base + offset) 주소 메모리 값을 읽어서 dst.Reg에 저장한다.
- [ ] STORE : src.Reg 값을 (base + offset) 주소 메모리에 저장한다. 
- [ ] AND : 논리 AND 연산해서 dst.Reg에 저장한다.
- [ ] OR : 논리 OR 연산해서 dst.Reg에 저장한다.
- [ ] ADD : 덧셈 (+) 연산해서 dst.Reg에 저장한다. (AND와 OR을 이용해 구현)
- [ ] SUB : 뺄셈 (-) 연산해서 dst.Reg에 저장한다.
- [ ] MOV : op.Value 값을 dst.Reg에 저장한다.

Cpu Class 메서드 구현
- [ ] reset() : 레지스터 값을 모두 지우고 PC 값도 0으로 초기화한다.
- [ ] fetch() : 현재 PC 값에 해당하는 메모리에서 프로그램 명령어를 가져와서 리턴한다. PC 카운트를 +1 증가시킨다.
  - fetch에서 리턴한 명령을 execute로 넘겨준다.
- execute(Int16 IR) : 전달한 명령어를 어떤 명령인지 분석해서 계산하거나 처리
- dump() : REGISTER들 값을 배열에 넣어서 리턴한다.
