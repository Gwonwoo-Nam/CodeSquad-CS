## 함수형 프로그래밍

## 학습 내용 정리

### 함수형 프로그래밍이란?
- 하나의 프로그래밍 패러다임으로 정의되는 일련의 코딩 접근 방식
- 자료처리를 수학적 함수의 계산으로 취급, 상태와 가변 데이터를 멀리하는 프로그래밍 패러다임을 의미

### 선언형 언어와 명령형 언어
![img](https://media.geeksforgeeks.org/wp-content/uploads/1-344.png)
WHAT(선언형) vs HOW(명령형)


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


