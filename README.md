## XML Parser

### 학습 내용
<details>
<summary>
XML이란?
</summary>

Extensible Markup Language는 공유 가능한 방식으로 데이터를 정의하고 저장하는 언어. 
Markup Language의 예로는 대표적으로 HTML이 있다.

HTML은 이미 약속된 태그들만 사용 가능하다. 그러나 XML은 임의로 생성하여 사용할 수 있다.
이를 통해서 정보들을 태그로 마크하여 필요한 내용을 함께 저장할 수 있다는 장점이 있다.

- 텍스트 기반, 간결한 데이터 형
- 마크업 언어가 아닌 마크업 언어를 정의하기 위한 언어이다.
- 자신의 어플리케이션에 적합하게 작성 가능
</details>

<details>
<summary>
DOM이란?
</summary>

DOM(Document Object Model)은 XML이나 HTML 문서에 접근하기 위한 API로 W3C 표준 권고안이다.
DOM은 문서 내의 모든 요소를 정의하고, 해당 요소에 접근하는 방법까지 정의한다.

W3C DOM 표준의 3가지 구분
- Core DOM : 모든 문서 타입을 위한 DOM 모델
- HTML DOM : HTML 문서를 위한 DOM 모델
- XML DOM : XML 문서를 위한 DOM 모델

</details>

<details>
<summary>
추상 구문 트리(AST)란?
</summary>

AST는 프로그래밍 언어로 작성된 소스 코드의 추상 구문 구조의 트리이며, 이 트리의 각 노드는 소스코드에서 발생되는 구조를 나타낸다.
쉽게 말하면, 우리가 작성한 소스코드를 문법에 맞게 노드들로 쪼개서 만든 트리이다.
- 추상인 이유?
추상적인 형태이기 때문이 아닌, 코드에 필연적으로 포함되는 괄호, 기타 부호와 같은 코딩 구문을 포함하므로 추상이라고 한다.

컴파일러가 필요한 프로그래밍 언어에서 컴파일 단계 중 구문 분석(Syntax Analyzing) 단계의 결과물.

- JS의 예시
자바 스크립트 코드 - Abstract Syntax Tree - 인터프리터 - 바이트 코드

자바 스크립트 코드를 Scanner(Lexer)를 통해 token 단위로 분리되며,
parser(Syntax Analyzer)를 거쳐 구문을 확인해보고 AST로 변환된다.
</details>

<details>
<summary>
JSON이란?
</summary>

Javascript Object Notation, 자바스크립트 객체 표기법

- 자바스크립트 객체 표기법으로부터 파생된 경량화된 파일 형식, 복잡한 파일 형식보다 파일 교환에 유리하다. 
</details>
<details>
<summary>
컴파일러 이론(Tokenizer, Lexer, Parser)
</summary>

#### 토크나이저
어떤 구문에서 의미있는 요소들을 토큰으로 쪼개는 역할을 한다.
토큰이란, 어휘 분석의 단위를 의미하는 컴퓨터 용어로 일반적으로 의미있는 단위로 지정된다.

- 토큰 종류
  - identifier : 식별자
  - keyword : 예약어
  - separator : 글자를 구분하는 문자
  - operator : 연산을 위한 심볼
  - literal : 숫자, 논리, 문자
  - comment : 줄, 블록 주

#### 렉서
분해된 토큰의 의미를 분석하는 역할, 토크나이저와 렉서의 역할을 합하여
Lexical Analyze라고 한다. 의미있는 조각을 검출하여 토큰을 생성하는 것이다.
토큰 단위로 생성된 데이터를 parser에게 넘겨준다.

#### 파서
  - 파싱(구문 분석)은 프로그래밍 언어의 문법에 맞게 작성된 텍스트 문서를 읽어 들여 실행하기 위해 텍스트 문서의 문자열을 토큰으로 분해(어휘 분석), 의미와 구조를 반영해 트리 구조의 자료구조인 파스 트리를 생성하는 일련의 과정.
  - 데이터를 구조적으로 바꾸는 과정에서 데이터가 올바른지 검증하는 역할도 수행한다.
  - 일반적으로 파싱이 완료된 후에는 파스 트리를 기반으로 중간 언어인 바이트 코드를 생성하고 실행한다.
</details>


<details>
<summary>브라우저의 렌더링 과정</summary>

위의 개념들을 결합해서 브라우저가 어떻게 렌더링을 수행하는지 알아보자.
![img](https://velog.velcdn.com/images%2Fhang_kem_0531%2Fpost%2Fee083565-ee8f-46c3-a8d1-1e78f65e70b5%2Fimage.png)
1. 브라우저는 HTML, CSS, JS 등 렌더링에 필요한 리소스를 요청하고 서버로부터 응답을 받는다.
2. 브라우저 렌더링 엔진은 서버로부터 응답된 HTML과 CSS를 파싱하여 DOM(HTML), CSSOM(CSS)를 생성하고 결합하여 렌더 트리를 생성한다.
3. 브라우저의 JS 엔진은 서버로부터 응답된 JS 코드를 파싱하여 AST를 생성, 바이트코드로 변환하여 실행한다.
  - 이 때, DOM API를 통해 DOM, CSSOM을 변경할 수 있다. 변경된 DOM, CSSOM은 렌더 트리로 결합된다.
4. 렌더 트리를 기반으로 HTML 레이아웃을 계산하고 화면에 페인팅한다.

https://velog.io/@hang_kem_0531/TIL-%EB%AA%A8%EB%8D%98-%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8-Deep-Dive-%EB%B8%8C%EB%9D%BC%EC%9A%B0%EC%A0%80%EC%9D%98-%EB%A0%8C%EB%8D%94%EB%A7%81-%EA%B3%BC%EC%A0%95
</details>

<details>
<summary>
오토마타 표현
</summary>


</details>

<details>
<summary>
Stack 구현하기
</summary>

</details>
