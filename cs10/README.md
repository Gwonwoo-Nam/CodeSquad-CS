## 프로세스 스케줄링

### 프로세스의 개념

- 프로세스의 문맥
    - CPU 수행 상태를 나타내는 하드웨어 문맥
        - Program Counter - 어디까지 실행했는가?
        - 각종 Register
- 프로세스의 주소 공간
    - stack, data, code 등 메모리에 담겨있는 내용
- 프로세스 관련 커널 자료 구조
    - PCB (Process Control Block)
    - Kernel stack
        - System call 발생 시 프로세스 별 별도의 스택프로세스 관련 커널 자료 구조

### 프로세스 자료구조

1. PCB (Process Control Block)
- 특정 프로세스 관리를 위한 정보를 포함하는 운영체제 커널의 자료구조
- 프로세스에 대한 중요한 정보를 가지고 있으며, 프로세스 생성 시 고유의 PCB가 생성
- Context Switch가 발생할 때 처리하던 작업의 내용을 PCB에 저장하여 다음에 다시 작업을 수행할 때 문맥을 기억할 수 있게 된다.

- PCB에 저장되는 정보들
  - 프로세스 식별자(Process ID)
  - 프로세스 상태(Process state) : 생성, 준비, 실행, 대기, 완료
  - 프로그램 카운터 : 다음에 실행할 명령어 주소
  - CPU 레지스터 및 일반 레지스터
  - CPU 스케줄링 정보 : 우선 순위, 최종 실행 시각, CPU 점유 시간
  - 메모리 관리 정보 : 해당 프로세스의 주소 공간
  - 프로세스 계정 정보 : 페이지 테이블, 스케줄링 큐 포인터, 소유자, 부모
  - 입출력 상태 정보 : 할당된 IO 장치 목록, 열린 파일 목록
  - 포인터 : 부모 프로세스, 자식 프로세스에 대한 포인터, 메모리 주소에 대한 포인터, 할당된 자원에 대한 포인터 등

![img.png](img.png)

2. TCB
- 커널에서 관리하는 스레드 정보를 위한 데이터 구조
  - TCB 저장 정보
  - 스레드 ID
  - 스택 포인터
  - 프로그램 카운터
  - 스레드 상태
  - 스레드별 레지스터 값들
  - PCB 포인터

### 프로세스 상태

프로세스는 상태가 변경되며 수행된다.

- Running
  - CPU를 잡고 instruction을 수행 중인 상태
- Ready
  - CPU를 기다리는 상태(메모리 등 다른 조건을 모두 만족)
- Blocked (wait, sleep)
  - CPU를 주어도 당장 instruction을 수행할 수 없는 상태
  - Process 자신이 요청한 event(예: I/O)가 즉시 만족되지 않아 기다리는 상태
  - (예) 디스크에서 file을 읽어와야 하는 경우
- Suspended (stopped)
  - 외부적인 이유로 프로세스의 수행이 정지된 상태
  - 프로세스는 통째로 디스크에 swap out 된다.
  - (예) 사용자가 프로그램을 일시 정지시킨 경우, 시스템이 메모리 등 이유로 프로세스를 잠시 중단시킴
- New
  - 프로세스가 생성 중인 상태
- Terminated
  - 수행이 끝난 상태, 정리할 것이 남아 있는 상태
- Blocked : 자신이 요청한 event가 만족되면 ready
- Suspended : 외부에서 resume해주어야 active

![img_1.png](img_1.png)

### 문맥 교환

- CPU를 한 프로세스에서 다른 프로세스로 넘겨주는 과정
- CPU가 다른 프로세스에게 넘어갈 때 운영체제는 다음을 수행
  - CPU를 내어주는 프로세스의 상태를 그 프로세스의 PCB에 저장
  - CPU를 새롭게 얻는 프로세스의 상태를 PCB에서 읽어옴
- System Call이나 Interrupt 발생 시 반드시 context switch가 발생하는 것은 아님
  - 사용자 프로세스 A/B간의 전환이 context switch인데, 운영체제에서 특정 프로세스 간 반복은 해당되지 않는다.
- 문맥교환을 하는 경우 메모리 부담이 훨씬 큼(cache memory flush)

### 스케줄러의 종류

- Long-term scheduler (장기 스케줄러 or job scheduler)
  - 시작 프로세스 중(new 상태에서 ready 상태로 만들지) 어떤 것들을 ready queue로 보낼 지 결정
  - 프로세스에 memory(및 각종 자원)을 주는 문제
  - degree of multiprogramming을 제어
  - time sharing system에는 보통 장기 스케줄러가 없음(무조건 ready)
- Short-term scheduler (단기 스케줄러 or CPU scheduler)
  - 어떤 프로세스를 다음에 running할지 결정
  - 프로세스에 CPU를 주는 문제
- Medium-term scheduler (중기 스케줄러 or swapper)
  - 여유 공간 마련을 위해 프로세스를 통째로 메모리에서 디스크로 쫓아냄
  - 프로세스에게서 memory를 빼앗는 문제
  - degree of Multiprogramming을 제어
- 장기 스케줄러는 초기화 시에 어떤 것을 사용할 것인가? 중기 스케줄러는 다 메모리에 올리되 어떤 것을 사용하지 않을 것인가?

### CPU Scheduler & Dispatcher
1. CPU Scheduler
- Ready 상태의 프로세스 중에서 이번에 CPU를 줄 프로세스를 고른다.
- 운영체제 내에서 scheduling하는 프로그램을 scheduler라고 부른다.
2. Dispatcher
- CPU의 제어권을 CPU scheduler에 의해 선택된 프로세스에게 넘긴다.
- 실제 넘겨주는 과정을 관리하는 코드
- 이 과정을 context switch(문맥 교환)라고 한다.

* CPU 스케줄링이 필요한 경우
- Running → Blocked (예 : I/O 요청하는 시스템 콜)
- Running → Ready (예 : 할당시간만료로 timer interrupt)
- Blocked → Ready (예 : I/O 완료 후 인터럽트)
- 스케쥴 우선순위에 따라 실행 중 프로세스가 시간이 남아있어도 I/O 완료된 process를 바로 실행할 수도 있다.

### 스케쥴링 알고리즘의 성능 척도(Performance Index)

1. 시스템 입장의 성능 척도

- CPU utilization(이용률)
  - 전체 시간 대비 CPU가 놀지 않고 일하는 시간의 비율
- Throughput(처리량)
  - 주어진 시간에 얼마나 많은 프로세스를 완료했는지?

2. 프로세스 입장의 성능 척도

- CPU 입장에서의 Process 성능 척도이므로, 프로세스의 전체(I/O 포함)가 아닌 단일 CPU Burst(I/O 이전까지)의 time을 이야기한다.

- Turnaround time(소요시간, 반환시간)
  - Queue에서 서서 실행하고 나갈 때까지의 CPU Burst 시간
- Waiting time(대기 시간)
  - CPU를 쓰기 위해 Ready Queue에서 기다리는 시간
  - 선점형 스케쥴링 동안 발생하는 대기 시간의 총합
- Response time(응답 시간)
  - 선점형 스케쥴링이더라도 최초의 CPU를 얻기까지에 기다리는 시간

### 스케줄링 알고리즘

1. FCFS(or FIFO)
비선점형 스케쥴링으로, CPU를 짧게쓰는 프로세스가 도착해도 오래 기다려야한다는 단점이 있다. 
이를 Convoy Effect라고 하는데, 긴 프로세스가 선점하였을 때, 뒤의 짧은 프로세스가 오래 기다려야하는 상황을 뜻한다.

2. SJF
각 프로세스의 다음번 CPU burst time을 가지고 스케쥴링에 활용하는 방식으로, CPU burst time이 가장 짧은 프로세스를 제일 먼저 스케쥴한다. 전체적인 Queue의 길이가 짧아지는 효과가 있고 또한 주어진 프로세스에 대해 minimum average waiting time을 보장된다는 특징이 있다.

- 선점형, 비선점형
  - Non-preemptive : CPU burst가 완료될 때까지 CPU를 선점 당하지 않음, 한 프로세스가 CPU를 다쓰고 나가는 순간에 스케쥴링 발생
  - Preemptive : 수행중 process의 남은 CPU burst time보다 짧은 CPU burst time을 가진 프로세스가 도착하면 CPU를 빼앗김.
  - 프로세스가 도착할 때마다 스케쥴링이 이루어진다. Shortest-Remaining-Time-First(SRTF)라고도 부른다.
- SJF의 문제점
  - Starvation(기아 현상) : CPU 사용 시간이 긴 프로세스는 영원히 점유를 못할 수도 있다.(형평성)
  - 실제로는 분기, 제어 등에 의해 런타임에서 CPU 사용 시간이 결정되므로, CPU 사용시간을 미리 알 수 없다. 그러나 어느정도 추정은 가능하다.
  - 추정 방법 : Exponential Averaging
    - alpha의 값을 통해서 현재의 가중치와 과거의 가중치 사이에서 조절할 수 있다. 다음 CPU Burst Time의 예측이 가능하다.

3. Priority Scheduling
- 우선순위가 가장 높은 프로세스에게 CPU 할당하는 방식이다. 우선순위는 어느 것이든 정할 수 있다.

- 선점형, 비선점형
  - Preemptive : 우선순위가 더 높은 프로세스가 오면 CPU 권한을 빼앗음
  - Non-preemptive : 한번 권한을 받으면 끝날때까지 사용
  - 우선순위의 표현 : smallest integer = highest priority
  - SJF는 일종의 priority scheduling이다. (Priority = predicted next CPU burst time)
  - Problem
    - Starvation : 우선순위가 낮은 프로세스가 할당 받지 못하는 것(형평성)
  - Solution
    - Aging : 시간이 지나면서 프로세스의 우선순위가 높여주는 것 

4. Round Robin(RR)
- 각 프로세스는 동일한 크기와 할당 시간(time quantum)을 가진다. 
- 할당 시간이 지나면 프로세스는 선점(preempted) 당하고 ready queue의 제일 뒤에 가서 대기한다. 
- 일반적으로 SJF보다 average turnaround time이 길지만 response time이 더 짧다. CPU의 사용시간과 대기시간이 비례한다는 특징이 있다.

- 특징
  - 전체적인 응답 시간이 빨라진다. 예측이 필요없다.
  - n 개의 프로세스가 ready queue에 있고 할당 시간이 q time unit인 경우 각 프로세스는 최대 q time unit 단위로 CPU 시간의 1/n을 얻는다.
  어떤 프로세스도 (n-1)q time unit 이상 기다리지 않는다.
- Performance
  - q large - FCFS
  - q small - context switch 오버헤드가 커진다.
- CPU 사용시간이 모두 동일하다면, 효율이 떨어진다.
- ex) 100초짜리 CPU burst 프로세스 10개인 경우, 1000초가 될때 모든 프로세스가 거의 동시에 끝난다. - turnaround time이 굉장히 길어진다.
- CPU 사용시간이 다양하고 예측할 수 없을 때, 효율이 좋다.
- 전체적인 response가 빠르면서, 짧은 프로세스는 turnaround가 빨리 일어날 수 있다.

### 쓰레드
- 쓰레드란, 프로세스 내부에 있는 CPU 수행 단위

- 프로세스 vs 스레드
  - 프로세스는 운영체제로부터 자원을 할당받는 작업 단위
  - 쓰레드는 프로세스가 할당받은 자원을 이용하는 실행 단위, 프로세스 내에 여러 개 생길 수 있다.

![img_2.png](img_2.png)

- Thread의 구성
  - program counter - 작업 위치
  - register set - CPU의 작업 공간
  - stack space - 작업 위치에 따른 함수 호출 순서
- Thread가 동료 thread와 공유하는 부분(task)
  - code section
  - data section
  - OS resources

#### 장점
- Responsiveness(응답성) : 다중 스레드로 구성된 테스크 구조에서는 하나의 서버 스레드가 blocked 상태인 동안에도 동일한 테스크 내의 다른 스레드가 실행(running)되어 빠른 처리를 할 수 있다. 일종의 비동기식 입출력이 구현된다.
- Resource Sharing : 독자적으로 가져야하는 정보만 thread마다 별도로 가진다.
- Economy
  - Creating & CPU Switching thread는 프로세스 생성 및 문맥 교환보다 훨씬 경제적이다.
  - 같은 일을 하는 작업이라면 프로세스를 여러 개 두기보다 thread를 여러개 두는 것이 효율적
- Utilization of Multi-Processor Architectures
  - 쓰레드가 다른 CPU에서 병렬적으로 일할 수 있음.
  - 동일한 일을 수행하는 다중 스레드가 협력하여 높은 처리율(throughput)과 성능 향상을 얻을 수 있다.
- 커널의 도움 없이 상호 통신 가능
  - Stack 제외 메모리 공간을 공유하므로 스레드간 통신이 간단.

#### 주의점
- 각 스레드가 Stack을 제외한 메모리 공간을 공유하기 때문에 동기화 문제가 발생할 수 있다.
- 하나의 스레드에 문제가 발생하면 프로세스 전체가 영향을 받는다.
- 주의 깊은 설계가 필요하며 디버깅이 까다로움 (프로그래머의 역량)

### Spring 프레임 워크에서 스레드 사용
- Node.js는 싱글 스레드 방식
- 그 외 대부분의 웹 서버는 멀티 스레드
- Spring의 Tomcat은 멀티 스레드 방식, Client 요청이 있을 때 마다 Thread 생성하여 요청을 처리하고 수거한다.
  - 요청 Client 수에 따라 Thread 생성과 수거에 따른 오버헤드가 발생

### 스레드 스케줄링
- 선점형 스레드 스케줄링 방식은 스레드의 우선권을 가지고 우선순위가 높은 스레드를 먼저 수행
- 협력형 스레드 스케줄러는 실행 중 스레드가 CPU 사용권을 다른 스레드에게 넘길 때까지 대기
- JVM은 우선순위에 따른 선점형 스레드 스케줄링 방식 사용

### IPC(InterProcess Communication)

#### 프로세스 간 통신 vs 스레드 간 통신
프로세스 - fork system call로 생성, PC를 포함해서 메모리 공간을 복사하여 별도 자원 할당
스레드 - pthread_create 함수 생성, 메모리 공간과 자원을 스레드끼리 공유

따라서 프로세스는 통신 공간이 없어서 통신을 위한 별도의 공간을 만들어야하기 때문에 스레드 간 통신이 더 어렵다.



1. PIPE
프로세스는 메모리 공간을 복제하기 때문에, 통신을 하기 위해 별도의 메모리 공간 할당이 필요.
통신을 위한 메모리공간(버퍼)를 생성해서 프로세스가 데이터를 주고 받는 것이 가능하게 되었습니다.
PIPE는 단방향의 통신이라는 단점이 존재합니다.
2개의 프로세스가 통신을 할 때는 2개의 파이프를 이용해서 양방향 통신을 한다.
단점 : 프로세스의 개수가 많은 경우 모든 경우의 수에 대해 서로를 이어주는 파이프가 존재해야하므로 버퍼 공간의 낭비가 심하다.

파이프의 종류
- 익명파이프(Anonymous Pipe)
이름이 없기 때문에 외부 프로세스에서 파이프를 사용할 수 없다. 부모 프로세스가 자식 프로세스를 생성하는 경우에는
파일 지정 번호를 상속받아 익명파이프로 통신할 수 있다.(부모 -> 자식만 가능한듯, 단방향 방식)
- 네임드파이프(Named Pipe)
부모 자식간 통신만 가능하므로, 외부 프로세스와 통신을 위한 이름이 있는 파이프.
파이프에 이름과 권한을 부여하여 생성한다. 읽기 쓰기가 모두 가능하지만 방향이 지정된 반이중통신.

2. Unix Domain Socket
소켓을 만들어 통신하는 방법. 소켓 통신은 흔히 네트워크 통신 기법으로 많이 사용되는데, 데이터 교환을 위해 양쪽 PC에서 임의 포트를 정하고 해당 포트 간 대화를 통해 데이터를 주고받는 방식.
각각 PC의 PORT를 담당하는 소켓은 각각 하나의 프로세스이다. 프로세스는 PORT를 맡아 데이터 송수신 역할을 진행하는 프로세스가 되는 것.


3. Shared Memory(공유 메모리)
메모리의 특정 공간을 활용해서 프로세스 간 통신이 진행되는 방식
- 대량의 정보를 다수 프로세스에게 배포 가능하다.
- 공유 메모리 공간에 대한 접근 제어가 필요하다.
- 프로세스가 공유 메모리 할당을 커널에 요청하면 커널은 메모리 공간을 할당하고, 이후 어떤 프로세스건 해당 메모리 영역 접근 가능하다.
- 중개자 없이 직접 메모리 접근이 가능하여 IPC 중 가장 빠른 작동이 가능

4. Memory Map
파일이 오픈되면 해당 파일의 데이터가 메모리에 올라가게 됩니다. 그리고 해당 메모리에 접근을 통해 데이터를 읽고 쓸 수 있습니다. 
메모리 공간에 오픈된 파일을 프로세스 메모리 공간으로 가져와서 읽고 쓴다.
이 경우, 파일을 쓸 때마다 시스템 콜을 할 필요가 없어진다.
기존의 메모리 공간에 같이 접근해서 데이터를 읽고 쓰는 작업을 진행하는데 이러한 방법을 Memory MAP이라고 할 수 있습니다.

5. Message Queue
우편함과 같은 방식, 통신하는 프로세스는 메시지 함을 만들어 해당 메시지 함으로 데이터를 전송, 메시지함에서 메시지를 수신하는 방식

메시지 큐 방식은 메시지를 구분하기 위해 메시지에 태그를 달아 메시지를 구분한다.
송신 함수를 통해 메시지 큐로 송신, 수신 함수를 통해 메시지 큐에서 메모리 공간으로 수신.
### 미션 1 - 프로세스 스케줄링 시각화

#### 기능 요구 사항
- [O] 마치 운영체제가 프로세스를 스케줄링하는 것처럼 프로세스를 일정한 시간동안 실행하는 프로그램을 구현한다.
- [O] 프로세스 종류를 A부터 F까지 6개 정하고, 프로세스 마다 최대 동작 시간을 겹치지 않도록 결정한다.
  - 예) 프로세스A : 3초, 프로세스B : 5초, 프로세스C : 7초, 프로세스D : 10초, 프로세스E : 15초, 프로세스F : 21초
- [O] 우리가 목표로 하는 운영체제는 한 번에 프로세스 하나씩만 1초동안만 실행할 수 있다. 해당 프로세스 외 다른 프로세스는 실행하지 않는다.
- [O] 1초 이후에는 같은 프로세스가 아니라 다른 프로세스를 실행해야 한다. 만약 프로세스가 1개만 남은 경우 반복해서 같은 프로세스를 실행할 수 있다.
- [O] 관리할 프로세스 상태는 준비ready, 실행running, 대기waiting, 종료terminated 4 가지 상태 중에 하나다.

#### 프로그래밍 요구 사항
- [O] 프로그램을 시작하면, 랜덤으로 프로세스 3개를 생성하고 대기 큐에 추가한다.
- [O] 프로세스는 준비 또는 대기 상태에서만 실행 상태로 바뀔 수 있다.
- [O] 누적 동작 시간이 최대 동작 시간만큼 실행한 프로세스는 종료 상태로 바뀐다.
- [O] 누적 동작 시간이 최대 동작 시간보다 작으면, 대기 상태로 바꿨다가 준비 상태로 변경한다
- [O] 대기 상태는 입출력 대기나 다른 응답을 위해서 사용하지만, 입출력을 다루지 않기 때문에 준비 상태로 바로 바꾸지 않고 대기 ⟹ 준비로 변경한다.
- [O] 프로세스마다 작업 정보를 포함하는 데이터 구조 또는 타입을 선언한다.
- [O] 단지 출력을 하기 위한 프로그램을 작성하는 게 아니라, 프로세스 타입을 선언하고 프로세스마다 1초씩 동작하는 구조를 구현해야 한다.
- [O] 프로그램은 1초마다 전체 프로세스 상태와 대기 시간과 누적 실행 시간을 표시한다.
- [O] 프로그램은 모든 프로세스가 종료 상태가 되면 종료한다.
- [O] 프로그램에서 구현해야 하는 스케줄링 방식은 다음과 같다. 3개 중에 1개를 골라서 구현하고 여유가 되면 다른 방식도 구현해본다.
- 기한부 스케줄링 (deadline scheduling)
  - deadline까지 남은 시간을 기반으로하는 선점형 우선순위 스케줄링 방식
  - 우선순위 : (deadline - 현재 시각)을 오름차순 정렬

### 싱글 스레드 구현 결과
```
D(READY), 0 / 6sec , waiting 0 , remaining Time 20 sec
A(READY), 0 / 3sec , waiting 0 , remaining Time 10 sec
B(READY), 0 / 4sec , waiting 0 , remaining Time 15 sec
.
D(WAITING), 0 / 6sec , waiting 1 , remaining Time 19 sec
A(RUNNING), 1 / 3sec , waiting 0 , remaining Time 9 sec
B(WAITING), 0 / 4sec , waiting 1 , remaining Time 14 sec
.
D(WAITING), 0 / 6sec , waiting 2 , remaining Time 18 sec
A(WAITING), 1 / 3sec , waiting 1 , remaining Time 8 sec
B(RUNNING), 1 / 4sec , waiting 1 , remaining Time 13 sec
.
D(WAITING), 0 / 6sec , waiting 3 , remaining Time 17 sec
A(RUNNING), 2 / 3sec , waiting 1 , remaining Time 7 sec
B(WAITING), 1 / 4sec , waiting 2 , remaining Time 12 sec
.
D(WAITING), 0 / 6sec , waiting 4 , remaining Time 16 sec
A(WAITING), 2 / 3sec , waiting 2 , remaining Time 6 sec
B(RUNNING), 2 / 4sec , waiting 2 , remaining Time 11 sec
.
D(WAITING), 0 / 6sec , waiting 5 , remaining Time 15 sec
A(RUNNING), 3 / 3sec , waiting 2 , remaining Time 5 sec
B(WAITING), 2 / 4sec , waiting 3 , remaining Time 10 sec
.
D(WAITING), 0 / 6sec , waiting 6 , remaining Time 14 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(RUNNING), 3 / 4sec , waiting 3 , remaining Time 9 sec
.
D(RUNNING), 1 / 6sec , waiting 6 , remaining Time 13 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(WAITING), 3 / 4sec , waiting 4 , remaining Time 8 sec
.
D(WAITING), 1 / 6sec , waiting 7 , remaining Time 12 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(RUNNING), 4 / 4sec , waiting 4 , remaining Time 7 sec
.
D(RUNNING), 2 / 6sec , waiting 7 , remaining Time 11 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
D(RUNNING), 3 / 6sec , waiting 7 , remaining Time 10 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
D(RUNNING), 4 / 6sec , waiting 7 , remaining Time 9 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
D(RUNNING), 5 / 6sec , waiting 7 , remaining Time 8 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
D(RUNNING), 6 / 6sec , waiting 7 , remaining Time 7 sec
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
D(TERMINATED), 6 / 6sec , waiting 7
A(TERMINATED), 3 / 3sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 4
.
기한부 스케줄링 (deadline scheduling)이 종료되었습니다.
평균 대기시간 = (7 + 2 + 4) / 3 = 4.33sec
평균 반환시간 = (13 + 5 + 8) / 3 = 8.67sec
```

### 미션 2 - 스레드 스케줄링

### 기능 요구사항
- [O] 각 프로세스는 스레드를 만들 수 있고, 스레드가 있으면 스레드마다 병렬로 처리할 수 있으니 실행 시간을 단축하는 효과가 있다고 가정한다. 스레드 2개가 1초 동작하면, 스레드 1개가 2초 동작한 것보다 단축된다.
- [O] 프로그램을 시작할 때 프로세스 별로 최대 작업 시간을 2로 나눴을 때 (소숫점을 버린) 몫만큼 스레드를 생성한다.
- [O] 프로그램이 시작하면 랜덤으로 프로세스 3개를 생성하고, 스레드 개수도 표시한다.
- [O] 프로그램은 모든 프로세스 작업이 끝나면 종료한다.

### 프로그래밍 요구사항
- [O] 각자 언어와 환경에서 스레드를 생성하고 각 스레드는 1초후에 동작을 완료한다.
- [O] 특정 프로세스는 할당된 스레드가 모두 동작을 완료해야 해당 프로세스는 동작을 멈춘다.

### 멀티스레드 구현 결과
```
이 프로그램은
프로세스D(6초) - 스레드 3개
프로세스B(4초) - 스레드 2개
프로세스C(5초) - 스레드 2개
총 스레드는 7개입니다.

D(READY), 0 / 6sec , waiting 0 , remaining Time 20 sec
B(READY), 0 / 4sec , waiting 0 , remaining Time 15 sec
C(READY), 0 / 5sec , waiting 0 , remaining Time 15 sec
.
D(WAITING), 0 / 6sec , waiting 1 , remaining Time 19 sec
B(RUNNING), 4 / 4sec , waiting 0 , remaining Time 14 sec
C(WAITING), 0 / 5sec , waiting 1 , remaining Time 14 sec
.
D(WAITING), 0 / 6sec , waiting 2 , remaining Time 18 sec
B(TERMINATED), 4 / 4sec , waiting 0
C(RUNNING), 4 / 5sec , waiting 1 , remaining Time 13 sec
.
D(RUNNING), 6 / 6sec , waiting 2 , remaining Time 17 sec
B(TERMINATED), 4 / 4sec , waiting 0
C(WAITING), 4 / 5sec , waiting 2 , remaining Time 12 sec
.
D(TERMINATED), 6 / 6sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 0
C(RUNNING), 5 / 5sec , waiting 2 , remaining Time 11 sec
.
D(TERMINATED), 6 / 6sec , waiting 2
B(TERMINATED), 4 / 4sec , waiting 0
C(TERMINATED), 5 / 5sec , waiting 2
.
기한부 스케줄링 (deadline scheduling)이 종료되었습니다.
평균 대기시간 = (2 + 0 + 2) / 3 = 1.33sec
평균 반환시간 = (8 + 4 + 7) / 3 = 6.33sec

---
```
