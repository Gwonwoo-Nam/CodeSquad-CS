## 함수형 프로그래밍

## 학습 내용 정리

### 함수형 프로그래밍이란?
- 하나의 프로그래밍 패러다임으로 정의되는 일련의 코딩 접근 방식
- 자료처리를 수학적 함수의 계산으로 취급, 상태와 가변 데이터를 멀리하는 프로그래밍 패러다임을 의미

### 선언형 언어와 명령형 언어
![img](https://media.geeksforgeeks.org/wp-content/uploads/1-344.png)
WHAT(선언형) vs HOW(명령형)

### 2가지 중요 특징
1. 부수효과(Side Effect)
함수 내의 실행으로 함수 외부가 받는 영향이 없다.
예외를 발생시키는 것도 외부에 영향을 주는 것이므로 부수효과가 있는 것이다.
2. 참조 투명성(Referential Transparency)
함수가 외부의 영향을 받지 않는다.
1) 자기 충족적이다.
2) 결정론적이다.
3) 예외를 던지지 않는다.
4) 다른 코드가 실패하는 조건을 만들지 않는다.
5) 데이터베이스, 파일 시스템, 네트워크 등 외부 기기로 동작이 멈추지 않는다.

### 함수형 프로그래밍의 장점
- 결정론적이므로 원인을 찾기 쉽다. 항상 동일한 결과를 반환하므로 많지않은 테스트로 원인을 찾을 수 있다.
- 테스트하기 쉽다. 부수 효과가 없으므로 mock을 만들 필요가 없다.
- 조립하기 쉽다. 부수 효과, 예외가 없고 값의 변경이 없다. 동시성 문제 또한 발생하지 않는다.
- 구성, 재구성이 쉽다. 기반 함수를 작성한 후 조합하기만 하면 된다. 참조 투명하기 때문에, 다른 프로그램을 작성 할 때도 코드 변경이 필요없다.

### 함수형 프로그래밍의 특징
1. 순수함수 (Pure function)
- 동일한 입력에는 항상 같은 값을 반환해야하는 함수
- 함수의 실행이 프로그램의 실행에 영향을 미치지 않아야 하는 함수
- 함수 내부에서 인자의 값을 변경하거나 프로그램 상태를 변경하는 Side Effect이 없는 것

순수 함수는 프로그램의 변화 없이 입력 값에 대한 결과를 예상 가능하다. (테스트 용이)

2. 비상태, 불변성 (Stateless, Immutability)
- 데이터는 변하지 않는 불변성을 유지한다.
- 변경이 필요한 경우, 원본 데이터 구조를 변경하지 않고 사본을 만들어 변경 작업을 진행한다.

3. 선언형 함수 (Expressions)
- 명령형 프로그래밍은 어떻게 할 것인가에 주목
- 선언형 프로그래밍은 무엇을 할 것인가에 주목

if, switch, for 등 명령문을 사용하지 않고 함수형 코드(map, filter, take, reduce) 등을 사용한다.

4. 1급 객체와 고차함수(First-class, Higher-order functions)
- 함수형 프로그래밍에서는 함수가 1급 객체가 되며, 고차 함수의 속성을 갖는다.
1급 객체의 특징
- 변수나 데이터 구조 안에 담을 수 있다.
- 파라미터로 전달할 수 있다.
- 반환값으로 사용할 수 있다.
- 할당에 사용된 이름과 관계없이 고유한 구별이 가능하다.
- 동적으로 프로퍼티 할당이 가능하다.
고차 함수의 특징
- 함수를 인자로써 전달할 수 있어야 한다.
- 함수의 반환 값으로 또 다른 함수를 사용할 수 있다.

5. Strict vs Non-Strict Evaluation
- print(length([2+1,1/0]))
- 위 수식을 eval할 경우 exception(strict) vs 2(non strict)
- Strict는 함수 값을 반환하는데 모든 값을 계산 후 eval
- Non Strict는 필요한 부분만 계산하여 eval


### Lambda Calculus란?
Function Abstraction과 function application using variable binding and substitution을 위한 formal system(기호 체계와 추론 규칙을 가짐)

### Lambda Calculus Terms
1. Variable 변수
2. Abstraction 추상화
- λ parameter.return
- 단순히 함수를 set up(정의)하는 것.
- 정의를 통해서 변수의 연결 관계(binding)을 생성한다.
- M또한 lambda terms여야 유효한 표현이 된다.
- λx.λy.x f(x,y) = x
수학의 Curring 개념을 적용한다.
- 인자를 여러 개 받는 함수를 분리하여 인자를 하나씩만 받는 함수의 체인으로 만드는 방법.
- 하나의 함수를 반복적으로 호출함으로써 재사용성을 높인다.
3. Application 적용
- fuction에 lambda를 대입하여 함수를 실행하는 것을 의미한다.

### Lambda Calculus Operation

1. alpha-conversion
(λx.M[x]) → (λy.M[y])
- 위처럼 Symbol을 교체하는 것이 함수 동작 방식에 영향이 없기에 이름 충돌을 피하기 위해서 변경이 가능하다.

2. beta-reduction
((λx.M) E) → (M[x := E])
x에 E를 대입한 것으로 항을 reduce 시킬 수 있음을 의미

### Lambda와 Closure

클로저란?
- 클래스 내에 정의한 변수를 람다가 직접 사용하는 것을 의미한다.
- 더 일반적으로 자신을 둘러싼 context 내 변수 등에 접근하는 것을 의미한다.
- 자신의 범위 밖의 변수를 사용하면 Closure라고 볼 수 있다.
- 람다는 클로저를 포함하는 큰 개념, 람다가 범위 밖의 변수를 참조하면 람다인 동시에 클로저가 된다.

## JAVA & Lambda

자바에서 어떻게 함수형 프로그래밍을 적용하는지 알아봅니다.

### vs 익명 클래스 
- 익명 클래스는 인스턴스를 생성해야하지만, 함수는 평가될 때마다 새로 생성되지 않는다. 함수를 위한 메모리 할당은 자바 힙의 permenant에 한번 저장된다.
- 객체는 데이터와 밀접하게 연관되어 동작하지만, 함수는 데이터와 분리된다. 상태를 보존하지 않으므로 여러 번 연산해도 결과가 변하지 않는다.(멱등성)
- 클래스의 스태틱 메소드가 함수의 개념과 가장 유사하다.

### 타입 추론과 함수형 인터페이스
- 타입이 정해지지 않은 변수의 타입을 컴파일러가 유추하는 기능
- 자바에서는 Generic과 Lambda에 대한 타입 추론
- 자바는 Type Erasure(컴파일 시 제네릭 타입 제거)를 사용하여, Generic에 대해서는 타입 추론이 되지 않았음

람다를 지원하기 위해 타입 추론을 강화, 함수형 인터페이스를 만들었다. 함수형 인터페이스는 하나의 추상 메소드로 이루어진 인터페이스,
함수의 시그니쳐가 정의되므로 컴파일러가 생략된 정보들을 추론한다.

### Functional Interface
@FunctionalInterface
- 단 하나의 추상 메소드만 가질 수 있는 함수형 인터페이스
- 추상 메소드가 없거나, 2개 이상이면 컴파일 에러 발생

### Predicate<T>
- boolean value function : 하나의 인자를 받아서 boolean value로 반환
- test를 통해서 적용
```
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<Integer> oddNums = (num -> num % 2 == 0);
        Predicate<Integer> positiveNums = (num -> num > 0);

        Integer[] array = IntStream.rangeClosed(-10, 10).boxed().toArray(Integer[]::new);

        filter(array, oddNums);
        filter(array, positiveNums);
    }

    public static <T> List<T> filter(T[] array, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : array) {
            if (predicate.test(t))
                result.add(t);
        }
        return result;
    }
}
```

### BiPredicate<T, U>
- 두 개의 인자를 받아서 boolean value로 반환
- test를 통해서 적용

### Consumer<T>
- 인자를 하나 받되 return 값이 없는 경우

### Function<T,R>
- 인자를 하나 받아서 결과를 반환한다.
- apply를 통해서 적용한다.

### Supplier<T>
- 인자를 받지 않고 Result를 반환하는 경우

### 메소드 참조
- 메소드를 간결하게 지칭할 수 있는 방법
- 람다가 쓰이는 곳 어디서나 사용할 수 있다.
- ex) String::valueOf

### FlatMap
- 중복된 스트림을 1차원으로 평면화시키는 메서드
- 리스트를 하나의 스트림처럼 다룰 수 있다.

### 기능 요구사항
- [O] 각 언어로 만들어진 다음 2개 클래스에서 중복된 코드를 줄이고, 함수형 표현으로 최대한 개선한다.
- [O] 앞서 작성한 자연수 분류 ClassifierAlpha, PrimeAlpha 를 이용해서 2-100까지 자연수 중에서 완전수(perfect), 과잉수(Abundant), 부족수(Deficient), 소수(Prime), 정사각수(Squared) 목록을 출력한다.

### 프로그래밍 요구사항
- [O] map, filter, reduce 고차 함수를 활용한다.
- [O] 출력을 위해서는 반드시 클로저(또는 람다)를 선언하고 반복문 대신 reduce를 활용해서 출력해야 한다.
- [O] 자연수 중에서 다른 자연수의 제곱으로 표현되는 정사각수(squared) 판단 함수를 추가한다

### 구현 결과
```
2 : deficient, prime
3 : deficient, prime
4 : deficient, squared
5 : deficient, prime
6 : perfect,
7 : deficient, prime
8 : deficient,
9 : deficient, squared
10 : deficient,
11 : deficient, prime
12 : abundant,
13 : deficient, prime
14 : deficient,
15 : deficient,
16 : deficient, squared
17 : deficient, prime
18 : abundant,
19 : deficient, prime
...
88 : abundant,
89 : deficient, prime
90 : abundant,
91 : deficient,
92 : deficient,
93 : deficient,
94 : deficient,
95 : deficient,
96 : abundant,
97 : deficient, prime
98 : deficient,
99 : deficient,
100 : abundant, squared
```
