## 미션 1 - 개발 환경

### 기능요구사항

- [O] 단계를 진행하면서 작업한 명령어, 스크린 캡처, 진행 과정, 실행 결과를 체크리스트를 채울때마다 README.md에 마크다운 문법으로 업데이트한다.

- [O] 소스 파일, 문서와 이미지 등을 모두 포함해서 secret gist에 저장한다.

  - [O] 단, 컴파일한 바이너리 실행 파일은 포함하지 않는다.

#### 1.dec2bin() 함수
- 0부터 256 미만의 Int 정수형 10진수를 [Bool] 2진수 배열로 변환하는 dex2bin 함수를 구현한다.
- 기본 연산만으로 변환하는 방식을 구현한다
  - 기존에 진법변환 함수나 라이브러리를 사용하지 않는다
- 만들어지는 비트 순서는 낮은 자리가 배열의 앞쪽에 오도록 표현한다. 배열의 순서대로 보면 이진수가 뒤집혀 있는 것처럼 보인다고 가정한다.

- 이진수 1100 = [ false, false, true, true ], 이진수 0101 = [ true, false, true, false ]

#### 2.bin2dec() 함수
[Bool] 2진수 배열을 Int 정수형 10진수로 변환하는 bin2dec 함수를 구현한다.

- 기본 연산만으로 변환하는 방식을 구현한다.
- 입력하는 비트 순서는 낮은 자리가 배열의 앞쪽에 오도록 표현한다. 배열의 순서대로 보면 이진수가 뒤집혀 있는 것처럼 보인다고 가정한다.

- 이진수 1100 = [ false, false, true, true ] 이진수 0101 = [ true, false, true, false ]

### 프로그래밍 요구사항
- [O] 값을 변환하는 함수를 선언하고, 변환할 값을 전달하는 상위 함수와 변환한 값을 출력하는 함수를 분리해서 작성한다.

### 구현 과정
1. dec2bin 함수
   - 반복문과 나머지 연산을 활용하여 ArrayList에 변환 결과를 저장한다.
   - 함수 시그니쳐에 맞게 배열에 결과를 반환한다.
2. bin2dec 함수
   - Math라이브러리의 pow함수로 반복적으로 더해 변환한 결과를 반환한다.

### 구현 결과

![result1.png](result1.png)

## 미션 2 - 가상머신 리눅스
### 학습 내용
- 
### 기능요구사항
- [O] 가상 환경 설치하기
  - 각자 컴퓨터 환경에서 사용할 수 있는 가상 환경(Virtual Machine)에서 리눅스 운영체제를 설치한다.

- [O] 가상 환경에 원격으로 접속할 수 있도록 ssh 설정을 하고, root 계정 이외에 본인 접속할 계정을 추가한다.
- [O] 본인 계정에 대한 패스워드를 설정한다.
- [O] 로컬 컴퓨터에서 가상 환경 리모트 컴퓨터에 ssh로 접속해서 본인 계정으로 로그인한다.
- [O] 본인 계정에서 /cs16 디렉토리를 생성하고 764 모드로 접근 권한을 바꿔서, 본인 계정으로 쓸 수 있도록 설정한다.
![img_1.png](img_1.png)
- [O] 가상 환경에서 터미널을 열고 /cs16 경로에 대해 권한을 확인하는 화면을 캡처한다.
- [ ] 가상 환경에 오늘 날짜 + 서울 시간대로 지정해서 로컬과 가상 환경이 동일하도록 맞춘다.
- [ ] 가상 환경에서 터미널을 열고 date 명령으로 오늘 날짜를 출력한 상태로 화면을 캡처한다.
- [ ] 가상 환경에 클래스별 컴파일러 또는 개발자도구를 설치하고 버전을 출력한 상태로 화면을 캡처한다.
- [ ] 새로운 개발 환경을 설정하는 것을 경험하는 것이라, 리눅스 사용자이거나 기존에 설치되어 있는 것을 사용하지말고 가상 환경에 직접 설치한다
- [ ] 아래 sumBinary() 함수를 리모트에서 작업하고, 리모트에서 빌드해서 실행할 수 있어야 한다

### 프로그래밍 요구사항
#### 가상 환경에서 작업하기
- [O] 가상 환경에 ssh 리모트로 본인 계정으로 접속한다
- [O] /cs16경로에서 gist를 clone 한다
- [ ] 가상 환경에서 작업하고 commit, push한다
#### 2진법 덧셈 sumBinary(a, b)
- [ ] 이전 단계에서 구현한 dec2bin() 결과를 사용해서 10진수 2개를 각각 2진수로 변환하고 변환한 이진수를 더해서 리턴하는 sumBinary() 함수를 구현한다
- [ ] 계산은 비트 단위로 로직 연산자(and, or, not)를 활용한다
- [ ] a, b 매개변수는 이진수를 나타내는 Array<Boolean>타입이다
- [ ] 리턴타입도 Array<Boolean> 이다.
- [ ] sumBinary() 덧셈 결과를 bin2dec() 함수에 넘겨서 제대로 모든 함수가 동작하는 지 확인한다.

### 추가학습거리
- [ ] binary(2진수 배열)를 16진법 문자열로 리턴하는 함수를 구현해본다
- [ ] VSCode에서 Remote Developer (또는 Remote SSH) 익스텐션으로 가상 환경에 접속해서 작업할 수 있는 개발 환경을 설정해본다
- [ ] binary를 배열에 비트를 저장하는 순서가 지금처럼 뒤집힌 경우와 아닌 경우 구현 방식이 어떻게 달라지는 지 확인한다
