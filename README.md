피씨방 관리하기

### JDBC

JDBC(Java Database Connectivity)는 자바 프로그램에서 데이터베이스와 연결하고 데이터를 조회하거나 수정하는 기능을 제공하는 자바 API입니다.

JDBC를 사용하면 자바 프로그램에서 데이터베이스와 손쉽게 연동할 수 있습니다.
JDBC는 데이터베이스에 대한 연결을 설정하고 SQL 쿼리를 실행하며, 결과를 가져오는 등의 작업을 처리할 수 있도록 다양한 인터페이스와 클래스를 제공합니다.

JDBC는 데이터베이스 제조사와 독립적으로 개발되었기 때문에, 자바 언어를 사용하는 모든 데이터베이스 시스템과 호환됩니다.
이러한 특징 덕분에, JDBC는 대부분의 자바 애플리케이션에서 데이터베이스 연동에 널리 사용되고 있습니다.

그렇지만, 사용하기 위해서는 데이터베이스에 맞는 데이터베이스 제조사에서 제공하는 JDBC 드라이버를 패스에 추가해야 합니다.
대부분의 JDBC 드라이버는 JAR 파일 형태로 제공되며, 이를 다운로드 받아 자바 클래스 패스에 추가하면 JDBC를 사용할 수 있습니다. 또한, 일부 데이터베이스 제조사는 자체적으로 JDBC 드라이버를 제공하는 대신 JDBC 표준을 따르는 특별한 드라이버를 제공하기도 합니다.

JDBC는 JDBC 드라이버, Connection, Statement, ResultSet 등 다양한 클래스와 인터페이스로 구성되어 있으며, 이를 활용하여 데이터베이스와의 연결을 설정하고 SQL 쿼리를 실행할 수 있습니다.

### JDBC 프로그래밍 흐름
1. JDBC 드라이버 로드: Java 애플리케이션은 먼저 데이터베이스 연결을 위해 JDBC 드라이버를 로드해야 합니다. 이는 Class.forName() 메서드를 사용하여 로드할 수 있습니다.
2. 데이터베이스 연결: DriverManager.getConnection() 메서드를 사용하여 데이터베이스에 연결합니다. 이 때, 데이터베이스 URL, 사용자 이름, 비밀번호 등의 정보를 제공해야 합니다.

3. SQL 쿼리 실행: PreparedStatement 객체를 사용하여 SQL 쿼리를 생성하고 실행합니다. PreparedStatement 객체는 미리 컴파일된 SQL 문을 나타내며, 매개 변수를 사용하여 동적으로 생성할 수 있습니다.

4. 결과 처리: SQL 쿼리가 실행되면 ResultSet 객체를 사용하여 결과를 가져옵니다. ResultSet 객체는 결과 집합을 나타내며, 다양한 메서드를 사용하여 데이터를 처리할 수 있습니다.

5. 데이터베이스 연결 종료: 데이터베이스 작업이 완료되면 Connection, PreparedStatement, ResultSet 등의 객체를 종료하고, 데이터베이스 연결을 닫아야 합니다. 이는 finally 블록을 사용하여 구현할 수 있습니다.

### Docker SQL 컨테이너 생성
```
docker run -d --name mysql-container -e TZ=UTC -p 30306:3306 -e MYSQL_ROOT_PASSWORD=My:S3cr3t/ ubuntu/mysql:8.0-22.04_beta
```
<옵션>
-d : daemon 옵션으로, 도커가 백그라운드에서 계속 실행되도록 한다.
-p : 포트 옵션, <로컬 접속 포트>:<도커에서 열리는 포트>로, 보통 mySQL을 로컬에서 사용할 때 3306을 사용하므로 로컬에서는 30306 포트로 열리도록 한다.
-TZ : 시간대 지정

### Intellij GUI로 DB 연동하기 - DB Navigator
GUI로 DB를 확인할 수 있습니다.
커뮤니티 버전의 경우 IntelliJ DB Navigator 플러그인 사용
https://suzyalrahala.tistory.com/45

### DB 설계하기

- PC방 사용자 목록
  - 사용자들을 구분할 수 있는 키값이 있어야 한다. null 불가능.
  - PC를 사용한 시작 시간이 있어야 한다. null 불가능.
  - PC를 사용한 종료 시간이 있어야 한다. null 가능
  - 선택한 PC 자리 번호를 지정할 수 있어야 한다. null 불가능.
- PC 자리 목록
  - PC 자리 키 값
  - 사용 중인 사용자 키 값

### 기본 용어
- 데이터베이스 : 테이블, 테이블과 관련된 SQL 구성요소를 담고 있는 저장소
- 쿼리 : 데이터베이스로 정보에 접근하는 것
- 테이블 : 데이터를 열과 행으로 구조화하여 보관하고 있는 데이터베이스의 구성 요소
- 행(레코드) : 한 객체에 대한 속성들을 나타내는 열의 집합
- 열(필드) : 테이블에 저장된 하나의 데이터

### 쿼리를 mySQL에 전달 - Statement/PreparedStatement 클래스
두 클래스 모두 JDBC에서 쿼리를 실행하기 위한 방법입니다. 차이점은, 컴파일 방법에 있습니다.
Statement는 실행될 때마다 SQL 쿼리를 컴파일합니다.
이것은 쿼리가 실행될 때마다 반복적으로 수행되므로, 쿼리 실행 시간이 길어질 수 있습니다.
또한, Statement는 쿼리의 매개 변수를 직접 문자열로 연결하므로, 쿼리의 가독성과 유지보수성이 떨어질 수 있습니다.

반면에 PreparedStatement는 SQL 쿼리를 미리 컴파일하여 재사용이 가능한 객체를 생성합니다.
이렇게 하면 실행 시간이 단축되며, 쿼리 실행 시마다 새로운 SQL 문을 컴파일하지 않으므로 성능이 개선됩니다.
또한, PreparedStatement는 쿼리의 매개 변수를 사용하여 쿼리를 작성할 수 있으므로, 가독성과 유지보수성이 높아집니다.

### DB 데이터 타입
VARCHAR (n) : 길이 255개까지 문자 데이터 저장
DEC (n,n) : 십진수의 자릿수 할당 (전체 자릿수, 소수점 이하 자릿수) DEC(4,2) = 12.34
CHAR (CHARACTER) : 정해진 길이의 데이터
INT (INTEGER) : 정수
BLOB : 큰 덩어리의 문자 데이터
DATE : 날짜 (시간은 없음)
DATETIME (TIMESTAMP) : 날짜 및 시간
TIME : 시간 (날짜는 없음)

### 테이블에 데이터 추가하기
INSERT INTO 테이블이름 (필드1, ...) VALUES (값1, ...)
1. 열 순서 변경 가능
2. 열과 같은 순서로 입력 시, 열 이름 생략 가능
3. 몇 개 열 생략 가능

### 리팩토링 : Data Access Object로 분리하기
DAO : 데이터베이스의 data에 접근하기 위한 객체를 별도로 생성
데이터베이스 접근 로직과 비즈니스 로직을 분리한다.
DAO는 일반적으로 DB와 연결하는 Connection까지 설정되어 있는 경우가 많다.


### user list 테이블 생성
```
+-------------+----------+------+-----+---------+-------+
| Field       | Type     | Null | Key | Default | Extra |
+-------------+----------+------+-----+---------+-------+
| id          | int      | NO   | PRI | NULL    |       |
| start_time  | datetime | NO   |     | NULL    |       |
| finish_time | datetime | YES  |     | NULL    |       |
| seat_number | int      | NO   |     | NULL    |       |
+-------------+----------+------+-----+---------+-------+
```

### pc list 테이블 생성
```
+-------------+------+------+-----+---------+-------+
| Field       | Type | Null | Key | Default | Extra |
+-------------+------+------+-----+---------+-------+
| user_id     | int  | YES  |     | NULL    |       |
| seat_number | int  | NO   | PRI | NULL    |       |
+-------------+------+------+-----+---------+-------+
```

### 구현 결과
```
mysql> select * from user_list;
+---------+---------------------+---------------------+-------------+
| user_id | start_time          | finish_time         | seat_number |
+---------+---------------------+---------------------+-------------+
|       1 | 2023-02-24 05:51:06 | 2023-02-24 05:51:11 |           7 |
|       2 | 2023-02-24 05:51:07 | 2023-02-24 05:51:13 |           8 |
|       3 | 2023-02-24 05:51:08 | 2023-02-24 05:51:14 |          16 |
|       4 | 2023-02-24 05:51:09 | NULL                |           6 |
|       5 | 2023-02-24 05:51:15 | 2023-02-24 05:51:24 |          15 |
|       6 | 2023-02-24 05:51:20 | 2023-02-24 05:51:25 |           9 |
|       7 | 2023-02-24 05:51:21 | 2023-02-24 05:51:27 |           5 |
|       8 | 2023-02-24 05:51:22 | 2023-02-24 05:51:56 |           7 |
|       9 | 2023-02-24 05:51:28 | NULL                |           3 |
|      10 | 2023-02-24 05:51:29 | NULL                |          14 |
|      11 | 2023-02-24 05:51:30 | NULL                |           2 |
|      12 | 2023-02-24 05:51:30 | NULL                |          16 |
|      13 | 2023-02-24 05:51:31 | 2023-02-24 05:51:35 |          11 |
|      14 | 2023-02-24 05:51:32 | 2023-02-24 05:51:36 |           4 |
|      15 | 2023-02-24 05:51:33 | 2023-02-24 05:51:38 |          10 |
+---------+---------------------+---------------------+-------------+
16 rows in set (0.00 sec)

mysql> select * from pc_list;
+---------+-------------+
| user_id | seat_number |
+---------+-------------+
|    NULL |           1 |
|      11 |           2 |
|       9 |           3 |
|    NULL |           4 |
|    NULL |           5 |
|       4 |           6 |
|    NULL |           7 |
|    NULL |           8 |
|    NULL |           9 |
|    NULL |          10 |
|    NULL |          11 |
|    NULL |          12 |
|    NULL |          13 |
|      10 |          14 |
|    NULL |          15 |
|      12 |          16 |
+---------+-------------+
```

```
DB Connection [성공]
> 빈 자리는 다음과 같습니다.
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> new
7번 자리에 앉으세요 : #1
[1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> new
8번 자리에 앉으세요 : #2
[1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15, 16]
> new
16번 자리에 앉으세요 : #3
[1, 2, 3, 4, 5, 6, 9, 10, 11, 12, 13, 14, 15]
> new
6번 자리에 앉으세요 : #4
[1, 2, 3, 4, 5, 9, 10, 11, 12, 13, 14, 15]
> stop 1
이제 7번 자리가 비었습니다.
[1, 2, 3, 4, 5, 7, 9, 10, 11, 12, 13, 14, 15]
> stop 2
이제 8번 자리가 비었습니다.
[1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15]
> stop 3
이제 16번 자리가 비었습니다.
[1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> new
15번 자리에 앉으세요 : #5
[1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 16]
> new
9번 자리에 앉으세요 : #6
[1, 2, 3, 4, 5, 7, 8, 10, 11, 12, 13, 14, 16]
> new
5번 자리에 앉으세요 : #7
[1, 2, 3, 4, 7, 8, 10, 11, 12, 13, 14, 16]
> new
7번 자리에 앉으세요 : #8
[1, 2, 3, 4, 8, 10, 11, 12, 13, 14, 16]
> stop 5
이제 15번 자리가 비었습니다.
[1, 2, 3, 4, 8, 10, 11, 12, 13, 14, 15, 16]
> stop 6
이제 9번 자리가 비었습니다.
[1, 2, 3, 4, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> stop 7
이제 5번 자리가 비었습니다.
[1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> new
3번 자리에 앉으세요 : #9
[1, 2, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16]
> new
14번 자리에 앉으세요 : #10
[1, 2, 4, 5, 8, 9, 10, 11, 12, 13, 15, 16]
> new
2번 자리에 앉으세요 : #11
[1, 4, 5, 8, 9, 10, 11, 12, 13, 15, 16]
> new
16번 자리에 앉으세요 : #12
[1, 4, 5, 8, 9, 10, 11, 12, 13, 15]
> new
11번 자리에 앉으세요 : #13
[1, 4, 5, 8, 9, 10, 12, 13, 15]
> new
4번 자리에 앉으세요 : #14
[1, 5, 8, 9, 10, 12, 13, 15]
> new
10번 자리에 앉으세요 : #15
[1, 5, 8, 9, 12, 13, 15]
> stop 13
이제 11번 자리가 비었습니다.
[1, 5, 8, 9, 11, 12, 13, 15]
> stop 14
이제 4번 자리가 비었습니다.
[1, 4, 5, 8, 9, 11, 12, 13, 15]
> stop 15
이제 10번 자리가 비었습니다.
[1, 4, 5, 8, 9, 10, 11, 12, 13, 15]
> stop 8
이제 7번 자리가 비었습니다.
[1, 4, 5, 7, 8, 9, 10, 11, 12, 13, 15]
> 
시스템 종료.
```
