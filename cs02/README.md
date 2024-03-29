# 학습 계획

## 미션 1 - 영상 데이터 구조

### 학습 목표

- 영상 정보를 보관하는 데이터 구조를 선언하고, 연속 배열에 데이터 인스턴스를 생성한다

- 링크드 리스트 구조(Linked List)를 구현하기 위해서 데이터 구조에는 다음 영상 정보를 참조할 수 있어야 한다.

## 학습 내용

## Data Structure(List)

- 데이터 스트럭쳐를 배우는 이유 : 자료 구조를 통한 메모리의 효율적 사용
- RAM(Random Access Memory) : 데이터 주소를 통해 데이터에 접근하는 시간이 동일하다.
- 배열 리스트와 연결 리스트의 특징 비교
    - Array List : 메모리 주소 상에 연속으로 붙어있다.
        - 한 번 할당을 하면 배열의 크기를 변경하기 어렵다. 더 많은 배열의 할당이 필요하면 메모리 상의 다른 주소를 확보한 후 전체가 옮겨가야한다.(재할당)
        - 위치를 알고있다면 데이터의 접근은 매우 빠르다.
    - Linked List : 각 데이터들이 메모리 상에 흩어져 있지만 연결되어 있다.
        - 서로 떨어져있기 떄문에 가변적으로 데이터를 관리하기 용이하다.
        - 엘리먼트의 index에 따라서 데이터를 탐색하는데 걸리는 시간이 상이하다.(비효율적)

## Linked List의 구현

- 연결 리스트의 구조
  ![img.png](img.png)
    - Node, Vertex(정점) : 연결 리스트의 요소를 나타내는 표현, 연결성을 강조한 표현임
    - 객체 지향 언어에서는 객체를 사용해서 연결 리스트를 구현한다.
    - 헤드 필드 : 첫번째 노드의 위치를 가지고 있는 필드
    - 데이터 필드 : 데이터가 저장되는 필드
    - 링크 필드 : 다음 노드를 가리키는 필드
- 연결 리스트의 상세 구현
    - 연결 리스트 객체
        - node 참조 객체
            - next 필드() : 다음 노드의 주소를 저장
            - value 필드 : 현재 노드의 값을 저장
        - head 필드 : node 참조 변수를 저장한다.

## 메모리 구조(code, data, heap, stack)

- code : 코드가 보관되는 영역
- data : 전역변수, static 변수
  - main이 호출되기 전에 데이터 영역에 할당되어 프로그램 종료 시까지 존재
- heap : 동적 할당 - 프로그래머가 할당하는, 따라서 관리해야하는 메모리
  - 동적으로 할당되므로 컴파일타임이 아닌 런타임에서 결정되는 메모리
    - new를 통해 할당하는 경우 인스턴스는 heap에 저장된다.
    - ex) String은 초기화 시 new String / String = "" 두가지 경우의 인스턴스가 저장되는 위치가 다르다.
  - JAVA의 경우 GC를 통해 자동으로 관리되는 영역
  - C의 경우 malloc / free로 직접 할당 및 해제가 필요하다.
  - 보통 stack보다 큰 메모리를 할당 받기 위해 필요
- stack : 지역변수, 매개변수 - 함수 호출 시에 생성되며 호출이 완료되면 사라진다. 
  - 끝에서부터 데이터를 건초더미처럼 쌓아 올리는 역할을 한다.
  - 정적 할당이므로 필요한 메모리 공간을 컴파일 타임에 알 수 있다.

따라서, 객체의 배열의 경우 객체를 참조하는 변수(4byte)는 stack 영역에 생성되며, 동적 할당되는 객체의 인스턴스 배열은 heap 영역에 연속적으로 생성된다.

## 연속 메모리를 차지하게 생성하고 조회할 수 있을까?

### 연속 메모리 상에 생성하기
처음에는 정보를 가지는 각 인스턴스들의 배열로 선언하려고 했는데, 문제의 제약 조건 중 배열에 저장하지 말라는 조건이 있어, 저장하지 않고 반복문으로 인스턴스 생성 후 바로 출력하도록 처리하였다. 반복적으로 생성했을
때 메모리 상에서 연속적으로 존재할 것이며, 배열을 저장하지 않는다는 조건도 만족할 수 있었다.

```
public VideoArrayList(int number) {
        for (int i=0;i<number;i++) {
            View.printResult(new VideoNode());
        }
    }
```


### 연속 메모리 상에 있는지 조회하기
그리고, 아래 hash code 확인하는 라이브러리로 객체의 메모리가 과연 연속으로 존재하는지 조회하였는데 일단 불연속적으로 존재한다는 결론을 내렸다.

```
// 객체의 주소 확인 명령어 : System.identityHashCode();
// 결과 값이 10진법으로 출력되어 long.toHexString으로 16진법 변환

7291c18f
34a245ab
7cc355be
6e8cf4c6
12edcd21
34c45dca
52cc8049
5b6f7412
27973e9b
312b1dae
7530d0a
27bc2616
3941a79c
```

왜 연속적으로 주소가 생성되지 않는걸까? 고민하다 알게된 두가지 사실.

1. 위 함수는 Hash Code를 조회하는 함수인데, 해쉬코드와 메모리 주소는 대응되지만 다른 개념이다.

- 메모리 주소와 hashcode의 관계
    - hashCode는 고유한 개체에 고유한 정수를 반환하는 개념이다.
    - 따라서 메모리 주소로 무조건 구현되지는 않는다.
    - hashCode 구현은 JVM에 달려있다.
    - 즉, hashCode를 생성하는 방법은 JDK의 버전에 따라 상이하다.
        - 난수 / 메모리 주소의 함수 / 메모리 주소의 int Casting 등

2. JVM의 작동 원리 상 메모리 주소를 조회하는 것은 의미가 없다.
    - JVM은 객체의 주소를 고정 값으로 가지고 있지 않다.
    - 가비지 컬렉터가 객체의 위치를 바꾸기 때문에 주소값이 의미 없고 제공을 원하지 않는다.
    - 보안 문제로 또한 직접 접근을 막아두었다. 그렇기 때문에 메모리 주소와 대응되는 해쉬 코드를 암호화하여 사용하는 것.
    - 해쉬코드를 얻는 메서드로 얻은 HashCode는 초기 선언 시의 값에 불과하며 GC에 따라 계속 바뀐다.

그래도 여기까지 온 이상 오기로 초기 메모리 주소가 배열에서는 연속적으로 생성되는 것인지 확인하고 싶었다. 그래서 Stackoverflow 구글링으로 자바의
printAddress를 구현해놓은 함수를 찾아 실행했으나.. 결과는 아래처럼 실패했다. 이유는 JVM이 어떻게 메모리를 사용하는지(32-bit, 64-bit)에 따라 오브젝트의 인덱스가 영향을 받고 주소를 찾는
방식이 영향을 받기 때문이다.
여기까지의 삽질을 마무리로 자바에서 메모리 주소 추적을 마쳤다.

```
---영상클립 생성---
cdfa: 0x8
제목1: 0x8
facd: 0x8
제목2: 0x8
baee: 0x8
제목3: 0x8
aafe: 0x8
```

## ArrayList는 어떻게 동적으로 늘어날까?

### Array와 ArrayList의 차이
  - Array는 크기가 고정되어있는 정적배열, arrayList는 동적 배열이다.
  - Array는 Object와 primitive를 다 담을 수 있지만, arrayList는 object만 담을 수 있다.
  - Array는 제네릭을 사용할 수 없고, arrayList는 사용할 수 있다.
  - Array는 길이에 대해 배열은 length 변수, arrayList는 size() 메서드를 사용한다.
  - Array는 element를 할당(대입)하고, arrayList는 add()로 삽입한다.

### 동적으로 할당시키는 원리
- Array의 index 마지막에 요소를 삽입하는 경우 O(1)라고 했는데, 배열의 크기를 바꿔야하는데도 불구하고 한번의 연산으로 어떻게 add할 수 있는지 의문점이 생겼다.
- 결론은, array는 클라이언트에 보여주는 size와 내부 저장 array의 length를 다르게 가지고 있었다.
  - 예를 들면, 3개의 원소를 가지고 있는 ArrayList numbers가 있다고 가정해보자. {1,2,3}
  - numbers.size()를 호출하면 size=3이 클라이언트에게 보이지만 실제로 생성자는 최소 Capacity인 10의 length 배열을 생성한다.
  - 그리고 size가 length와 같아질 때 현재 Capacity + 1/2 현재 Capacity만큼의 새로운 배열을 생성하고 원래 원소를 복사해 옮긴다.
 
코드를 보자. add 메서드를 호출하면 size와 내부 array의 length를 비교한다. 같은 경우 grow 메서드를 호출해서 동적으로 용량을 키울 것이다.
```
public boolean add(E e) {
        modCount++;
        add(e, elementData, size);
        return true;
    }
    
private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        size = s + 1;
    }
```
newCapacity = size+1 + oldCapacity >> 1로 계산되는 것을 확인할 수 있다.

```
    private Object[] grow() {
        return grow(size + 1);
    }
    
    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = ArraysSupport.newLength(oldCapacity,
                    minCapacity - oldCapacity, /* minimum growth */
                    oldCapacity >> 1           /* preferred growth */);
            return elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }
```

### 프로그래밍 요구사항

- [O] 영상 데이터 구조를 13개 생성한다
- [O] 각 영상 데이터에는 고유한 id가 할당된다
- [O] 데이터 구조를 생성하고 저장하지 않고 출력만 한다
- [O] 제목은 "제목01" 부터 시작해서 "제목13" 까지로 할당한다
- [O] 재생 시간은 랜덤하게 1초에서 15초 내로 지정한다
- [O] 다음 영상 정보는 우선 지정하지 않는다
- [O] 생성한 데이터는 연속된 메모리 공간을 차지하도록 한꺼번에 생성한다
- [O] 이 단계에서는 연속 메모리를 관리해야 하며, 연결리스트를 구현해야 하는 건 아니다
- [O] 13개 모두 생성하고 나면 정해진 크기단위로 접근해서 탐색할 수 있어야 한다
- [O] 생성한 영상 순서대로 제목(id):재생시간 형식으로 출력한다.

### 구현 결과

![img_2.png](img_2.png)

## 미션 2 - 영상 목록 편집하기

### 학습 목표

- [O] 영상 데이터 구조를 연결하는 연결리스트LinkedList를 구현한다.
- [O] 링크드 리스트에 영상 데이터를 원하는 위치에 넣거나 삭제하는 동작을 구현한다.
- [O] 전체 링크드 리스트를 탐색해서 최종 영상 정보를 표시한다.
- [O] Array 나 List 를 사용하지 않고 직접 데이터 구조만 사용해서 구현해야 한다.

### 학습 내용

### List Operation 구현하기

- 연결 리스트의 삽입
    - array list는 삽입 시 뒤의 모든 엘리먼트의 자리 이동이 필요해 느린 반면, linked list는 참조값 2개만 변경시키면 되어서 빠르다.
    - temp1는 삽입하려는 전 인덱스의 node를 참조
    - temp2는 삽입하려는 후 인덱스의 node를 참조
    - temp1.next는 새로운 노드를 참조
    - 새로운 노드의 next는 temp2를 참조
- 연결 리스트의 삭제
    - 삭제할 노드의 이전 노드를 cur 참조변수가 참조하도록 한다.
    - 삭제할 노드를 temp 참조 변수를 생성해서 참조하도록 한다.
    - 삭제할 노드의 뒷 노드를 cur.next가 참조하도록 한다.
- 연결 리스트의 조회
    - 참조변수를 counter만큼 next로 넘긴다.

### 점근 표기법(시간 복잡도, 공간 복잡도)

- 시간 복잡도를 나타내기 위한 표기법 : 알고리즘의 효율성을 표기해주는 표기법, 기본 연산의 횟수
  - Big-O : 최대의 시간이 걸리는 경우를 가정한다.(최악의 경우)
  - Big-Omega : 최소의 시간이 걸리는 경우
  - Big-Theta : 평균적인 시간을 계산한다.
  - 빅오 표기법이 알고리즘 효율성을 상한을 기준으로 표기하기 때문에 필요한 최소한의 리소스 파악에 유리하다.
  - 최악 이상이라는 의미이지, 최악의 경우와 같은 의미는 아니다.

- Big O의 수학적 정의
  - ![img_1.png](img_1.png)
  - 어떤 n 이상의 값과 k값에 대해서 사용자 함수의 f(n)이 작거나 같게 만들수 있으면 빅오 표기법을 사용할 수 있다.
- Big O의 특징
  - 상수항 무시 : n이 충분히 크다는 가정 하에 사용하므로 가장 큰 차수 제외 무시한다.
  - 1 < log(n) < n < nlog(n) < n^2 < 2^n 등
  - 이진 탐색 : log n - 데이터의 개수 n = 2^(탐색 수 x) -> 탐색 수 x = log n
  - 단순 탐색 : n
  - 퀵 정렬 : nlogn
  - 선택 정렬 : n^2
  - 외판원 문제 : n!

### Big O 확인하기

- add, insert
  - 반복문은 n개의 요소가 있다고 했을 시 tail의 요소를 찾는 경우가 발생하고 시간 복잡도는 O(n)이다.
```
public void add(VideoNode videoNode) { //node만 입력하는 경우 addLast 실행
        if (addIfNull(videoNode))
            return;
        VideoNode currentNode = head;
        while(currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        currentNode.setNext(videoNode);
    }
```

- delete
  - 마찬가지로 끝에 있는 요소에 접근할 시 O(n)만큼의 시간 복잡도가 발생한다.
```
while(currentNode.getNext() != null) {
            String nextNodeId = currentNode.getNext().getId();
            if (nextNodeId.equals(videoNode.getId())) {//다음 노드가 삭제하려는 노드의 이름과 같다면
                currentNode.setNext(currentNode.getNext().getNext());//다다음 노드를 다음으로 설정
                return ;
            }
            currentNode = currentNode.getNext();
        }
```


### 기능요구사항

- [O] 이전 단계에서 만든 영상 데이터가 메모리 공간에 저장되어 있고 이번 단계에서는 영상 데이터들을 참조해서 가상으로 목록으로 연결하는 구조를 만들어야 한다

```
* 여러 영상 데이터를 보관하는 곳이 있다.
* 보관중인 영상 데이터 중에서 원하는 데이터를 선택해서 타임라인을 채우는 구조를 구현해야 한다.
  ```

- [O] 영상 데이터를 재생하려는 순서에 맞춰서 연결 리스트를 관리한다.
    - 노드 추가하기
    - 노드 삭제하기
    - 노드 탐색하기

- [O] 영상편집 데이터를 보관하는 구조를 Array 나 List (또는 Vector 등)를 사용하지 않고 직접 구현한 데이터 구조만 사용해서 구현해야 한다.
- [O] 이전 단계에서 구현한 영상데이터를 보관하는 연결 배열과 구분해서 직접 구현해야 한다

### 프로그래밍 요구사항

- [O] 앞 단계에서 생성한 데이터를 선택해서 편집하려는 순서대로 링크드 리스트로 연결하는 방식이다.
- [O] 메뉴는 명령어 + 공백 + 영상id 또는 명령어 + 공백 + 영상id + 공백 + 위치형식을 갖는다.
- [O] add abab를 입력하면 맨 뒤에 abab 영상을 추가한다.
- [O] insert cafe 0를 입력하면 맨 앞에 0번째에 cafe 영상을 추가한다.
    - [O] 만약 맨 뒤에 순서값이 현재 링크드 리스트에 개수보다 같거나 크면 맨 뒤에 입력한다.
    - [O] add 나 insert 시에 이미 만들어진 영상id가 없으면 추가하지 않고 node not exist를 출력한다
    - [O] add나 insert로 영상 노드를 중복해서 추가할 수도 있다
- [O] delete abcd를 입력하면 링크드 리스트 시작 위치부터 abcd를 찾아서 삭제한다.
  - [O] 만약 뒤에 다른 영상 데이터 정보가 있으면 이전 정보를 연결한다.
    [cafe]---[abcd]---[bdfa] 에서 [abcd]를 삭제하면 [cafe]---[bdfa] 상태가 되어야 한다
  - [O] 해당 node가 없을 경우 node not found를 출력한다
  - [O] 매칭되는 노드를 삭제하고 나면 탐색을 중지하고 뒤에 있는 노드를 삭제하지 않는다
- [O] render를 입력하면 링크드 리스트를 전체를 탐색하면서 재생 시간을 더한다.

### 구현 결과

![img_3.png](img_3.png)
