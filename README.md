## 체스 게임

### 학습 내용 정리

### 객체 개별성(Identity)와 동등성(Equality)

- 인스턴스가 다르면 개별성 Identity가 다르다.
- 속성이 모두 같으면 동등한 값을 가지므로 동등성(Equality)은 같다.
- 개별성, 동질성을 통해 인스턴스를 비교한다.
- 언어마다 개별성, 동질성을 구분하며 다르게 비교하는 비교연산자를 제공하기도 한다.

### Java equals와 hashCode

- 모든 객체는 Object 클래스의 equals와 hashCode를 상속받는다.
- equals : 2개의 객체의 동일성(Identity)를 비교한다.
  - 동일한 메모리 주소일 경우에만 동일한 객체가 된다.
- 동등성을 비교해주기 위해 equals를 오버라이딩 할 수 있다.
- String의 equals는 동등성을 비교하도록 Overriding된 메소드이다.
- HashCode 메소드는 런타임 객체의 유일한 integer 값을 반환한다.
- hashCode는 hashTable과 같은 자료 구조를 사용할 때 데이터가 저장되는 위치를 결정하기 위해 사용된다.
- 동일한 객체는 동일한 해시코드를 가져야한다. 따라서 equals 메소드를 오버라이드할 때, hashCode 메소드도 함께 오버라이드되어야한다.

### equals와 hashCode
- equals한 두 객체는 hashCode값도 일치해야한다.
- Java 프로그램 실행 중에 equals에 사용된 정보가 수정되지 않으면 hashCode는 항상 동일한 값을 반환해야한다.

### UML 통합 모델링 언어
UML(Unified Modeling Language)?
산출물의 명세화, 시각화, 문서화할 때 사용하는 모델링 언어로 시스템을 표현하기 위한 표준적인 방법을 제공

- 구조 다이어그램
- Class diagram : 클래스 속성, 함수, 변수 타입들로 구성된 다이어그램
- Object diagram : 클래스 인스턴스, 값이 매겨진 행동을 가지고 있는 독립된 객체정보를 표현
- Package Diagram : 모델 요소들을 그룹화한 다이어그램
- Component Diagram
- Deployment diagram
- 행위 다이어그램
- Use Case Diagram : 사용자 관점에서 바라본 시스템을 표현한 다이어그램
- Activity Diagram : 여러 활동들이 순차, 병행 방식 등을 수행하는 상황을 표현하는 다이어그램, 시스템 전체의 흐름을 표현
- State Diagram : 하나의 객체가 다른 객체와 상호작용에 따라 어떻게 변화하는지 표현, 하나의 객체의 활동변화를 묘사
- Sequence Diagram : 여러 대상 간의 상호작용을 시간 순서에 따라 표현
- Communication Diagram : 동작에 참여하는 객체들 간 메시지를 표현, 관계까지 표현

### 타입 시스템

1. Static Type System
컴파일 시점에 타입이 정해지고, 타입에 적합한 값만 지정 가능
2. Dynamic Type System
런타임에 타입이 (변경 가능) 정해진다.
3. Strong Type System
컴파일러가 타입 검사를 강하게 하는 경우, 컴파일 과정마다 타입이 결정된다.
4. Weak Type System
컴파일러가 타입 검사를 약하게 하고, 런타임에서 에러가 발생한다.
5. Duck Type System
동적 타입 시스템의 일종으로 타입 고유의 행위를 구현하면 그 타입으로 간주하는 시스템
6. Untype System
타입 구분 없이 모든 데이터 구조가 하나의 타입으로 인지되는 시스템.

### 메타 타입
타입에 대한 타입, 타입 자체를 값으로 다룰 수 있는 타입
java - getClass 등으로 타입 비교

### 접근

- OutputView
- InputView
- Controller
  - runChessGame()
- Board
  - initPiece(type, position) 
  - calculateScore()
  - display()
  - setPiece(type, position)
  - move(from, to)
- Piece
  - Color
  - Type
  - Position
  - possiblePositions()
- Position
  - file : enum
  - rank : enum
- Movable
  - move()
- Pawn
- Bishop
- Rook
- Queen
- Knight