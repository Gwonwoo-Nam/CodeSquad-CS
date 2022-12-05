## 구현과정 상세 설명

### 1단계

#### 기능 구현 요구사항

- 콘솔로 원 그리기
- 입력: 1 이상 80 이하의 정수를 콘솔을 통해 입력받는다.
- 출력: 터미널 화면에 해당 크기의 원을 "멋지게" 출력한다.

#### 풀이 과정
- 원의 출력 결과가 원과 비슷하게 출력하는 것에 초점을 둠.
- 자료 구조 상에서 x 좌표, y 좌표와 원의 중심과의 거리를 계산한다.
- 계산한 길이가 원의 반지름과 비슷하면 원 위의 점이라고 mark한다.
  - 기준은 임의로 거리와 원의 반지름의 차이가 0.5 이내일때로 하였다.
- 입력 받은 값이 정규표현식에 어긋나는 경우 다시 입력받을 수 있도록 구현했다.

#### 기능 구현 목록
- SolarCircle : 태양계의 원의 객체를 구현
  - 사이즈를 인자로 받아 원의 이미지를 자료구조로 저장해서 반환한다.
    - 한 점에서 원의 중심으로부터 거리를 계산할 수 있어야 한다.
    - 계산한 거리를 원의 반지름 길이와 비교한다. 
- InputView : 입력
  - 입력받은 값이 유효한 값인지 검사한다.
- OutputView : 출력
  - 원을 출력한다.

#### 구현 결과
```
원의 크기는?
e
1 이상 80 이하의 Size를 입력해주세요.
7
  ---  
 -   -
-     -
-     -
-     -
 -   -
  ---  
```
### 2단계

#### 기능 구현 요구사항
- 1단계 구현한 코드를 기반으로 지구 태양 달의 위치를 표시하는 프로그램을 작성한다.
- 입력 : 프로그램을 실행하면 1월 1일부터 12월 31일까지 날짜를 입력받는다.
  - 단 1월 1일에 태양 - 지구 - 달은 순서대로 일직선상에 위치한다고 가정한다.
  - 문제의 단순화를 위해 태양 지구 달은 같은 평면상에서 공전하며, 공전궤도는 완전한 원이라고 가정한다.
- 출력 : 해당 날짜에 태양 지구 달의 상대적인 위치를 콘솔에 "멋지게" 출력한다.

#### 풀이 과정
- 태양과 지구, 달의 거리의 비율 계산
  - 태양과 지구 사이의 거리 : 1 AU(astronomical unit)
  - 지구와 달 사이의 거리 : 0.0026 AU
- 태양과 지구, 달의 크기 비율(지름 기준)
  - 태양 : 지구의 109배
  - 달 : 지구의 1/4배

- 콘솔 화면에 출력하기에는 너무 비율 차가 크므로 임의의 비율 적용하여 구현한다.
  - 태양, 지구, 달의 거리 비율 - 5:1 
  - 태양, 지구, 달의 크기 비율 - 5:3:1 (예시 참조)

- 달은 지구를 27.3일을 주기로 공전한다.(항성월 - 실 공전주기 기준)
- 지구는 태양을 365.25일을 주기로 공전한다. 

#### 기능 구현 목록
- 날짜를 인자로 받아 SolarCircle 객체의 현재 공전 위치를 계산한다.

### 3단계

### 4단계