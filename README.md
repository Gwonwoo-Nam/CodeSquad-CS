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

