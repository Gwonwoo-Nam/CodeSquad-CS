
## Docker Desktop 설치
도커는 리눅스 상에서 호환되는 소프트웨어입니다. 리눅스 운영체제에서 도커를 사용하기 위해서는 도커를 설치하기만 하면됩니다.
그러나 다른 운영체제에서는 도커를 구동하기 위해서는 리눅스 OS가 필요합니다.
윈도우, macOS에서는 리눅스 운영체제를 포함한 소프트웨어 패키지를 Docker Desktop이라고 합니다.
도커 데스크톱은 리눅스 운영체제를 구동시키기 위한 가상환경 지원을 필요로 합니다.
윈도우에서는 WSL2(Window Subsystem for Linux 2)나 프로페셔널에서 지원하는 Hyper-V를 통해서 가상 환경을 지원합니다.

![img.png](img.png)

![img_1.png](img_1.png)

### mySql 컨테이너 생성
$ docker run -dit --name porosql -e MYSQL_ROOT_PASSWORD=(password) mysql:5.7
- Options : Non-blocking Background 실행 / 루트 패스워드 설정 / mySQL 5.7버전 설치
- docker run 명령어는 image pull + create(컨테이너 생성) + start(컨테이너 실행)의 과정을 전부 포함하는 명령어입니다.

### 컨테이너에서 Bash shell로 접속
$ docker exec -it porosql /bash/shell
이미지를 가지고 있는 컨테이너의 Bash Shell에 연결합니다.
(모든 컨테이너는 커널의 일부를 포함하고 있기 때문에 가능)

### POSIX란?
POSIX (Portable Operating System Interface)은 다양한 유닉스(Unix) 기반 운영체제에서 호환성과 이식성을 강화하기 위해 개발된 표준 인터페이스입니다.
POSIX 표준은 미국 표준기술연구소(NIST)와 IEEE에 의해 개발되었습니다. POSIX가 필요한 이유는 유닉스 계열 OS가 굉장히 많은데, 공통 API를 정리해서 유닉스 응용 프로그램 개발 시 이식성을 높이는 것이 목적입니다.
LINUX도 POSIX 규격을 준수하기 때문에 Unix-like(유닉스 호환) 운영체제라고 부릅니다.

POSIX는 유닉스 운영체제에서 사용되는 API 시스템 호출(System Calls)을 규정하며, 프로세스 환경, 파일과 디렉터리, 시스템 데이터베이스(암호 파일 등), tar 압축 포맷 등 다양한 분야를 아우릅니다.
이러한 표준화는 소프트웨어 개발자들이 다양한 운영체제에서 프로그램을 개발하고 이식성을 높일 수 있도록 도와줍니다.

### 로케일이란?
사용자 인터페이스에서 사용되는 언어, 지역 설정, 출력 형식 등을 정의하는 문자열.
유닉스와 리눅스 같은 POSIX 기반 시스템에서는 같은 형식 공유한다.

### 로케일 형식
language[_territory][.codeset][@modifier]
ex) ko_kr.UTF-8

### 로케일 설정
기본적으로 C Locale로 설정되어있다.
- C 로켈(POSIX 로켈이라고도 함)은 모든 POSIX 준수 시스템에 대한 POSIX 시스템 기본 로켈입니다.

export lang='ko_kr.UTF-8'과 같이 export 명령어로 환경 변수 설정을 할 수 있다.
locale -a로 사용가능한 로케일을 조회할 수 있다.
일반적인 리눅스나 맥OS 환경의 경우 자주 사용되는 로케일이 설치되어있습니다. 하지만 도커 이미지와 같이 최소화된 환경에서는 로케일이 기본적으로 설치되어있지 않습니다.
https://www.44bits.io/ko/keyword/locale

### mySQL 이미지가 설치된 컨테이너
라이브러리에서 다운받는 이미지에는 보통 OS가 같이 포함되어있다.

mySQL이 설치되었으나, 컨테이너 이미지 별로 기반하는 OS가 다름. 이 경우 CentOS가 포함되어 있어서, apt-get 등 우분투 기반 명령어가 사용 불가능했다.
Ubuntu 이미지로 컨테이너를 만들고, 컨테이너에 접속해서 mySQL를 설치하는 방법.

### 우분투 컨테이너 만들기

$ docker run --name ubuntu -it ubuntu

![img_2.png](img_2.png)

-d	detached mode 흔히 말하는 백그라운드, 데몬 모드
-p	호스트PC와 컨테이너의 포트를 연결 (포워딩)
-e	컨테이너 내에서 사용할 환경변수 설정
–name	컨테이너 이름 설정
–rm	프로세스 종료시 컨테이너 자동 제거
-i	상호 입출력
–t	tty를 활성화하여 bash 쉘을 사용

우분투 이미지가 정상 생성되었고, -it 옵션을 통해서 표준 입출력으로 Shell을 사용할 수 있게 되었다.

우분투 버전 확인
```
root@5339e90082e5:/# cat /etc/issue
Ubuntu 22.04.2 LTS \n \l
```

### 우분투 패키지 업그레이드 및 한글 설정

```
$ sudo apt update
$ sudo apt upgrade
$ sudo apt install language-pack-ko
$ sudo locale-gen ko_KR.UTF-8
$ sudo update-locale LANG=ko_KR.UTF-8 LC_MESSAGES=POSIX
```

language-pack-ko 패키지를 설치해서 locale-gen 명령어로 원하는 로케일을 생성합니다.
locale -a로 로케일이 생성되었는지 확인합니다.

![img_3.png](img_3.png)

update-locale로 /etc/default/locale을 설정할 수 있지만, 도커에서 셸을 구동하는 경우는 적용이 안되었습니다.
아래 명령어로 일괄적으로 로케일을 적용할 수 있습니다.
```
export LANG=ko_KR.utf8
```


```
root@5339e90082e5:/# locale
LANG=ko_KR.utf8
LANGUAGE=
LC_CTYPE="ko_KR.utf8"
LC_NUMERIC="ko_KR.utf8"
LC_TIME="ko_KR.utf8"
LC_COLLATE="ko_KR.utf8"
LC_MONETARY="ko_KR.utf8"
LC_MESSAGES="ko_KR.utf8"
LC_PAPER="ko_KR.utf8"
LC_NAME="ko_KR.utf8"
LC_ADDRESS="ko_KR.utf8"
LC_TELEPHONE="ko_KR.utf8"
LC_MEASUREMENT="ko_KR.utf8"
LC_IDENTIFICATION="ko_KR.utf8"
LC_ALL=
root@5339e90082e5:/#
```

로케일 설정이 완료되었습니다. 한글 입력을 위해서 셸을 재시작합니다. 환경변수를 입력해서 재시작하면 주어진 환경변수가 적용되어 셸이 실행됩니다.
```
root@5339e90082e5:/# date
2023. 02. 20. (월) 06:12:52 UTC
root@5339e90082e5:/# LANG=ko_KR.utf8 bash
root@5339e90082e5:/# echo "안녕하세요"
안녕하세요
root@5339e90082e5:/#
```

### mysql 설치
$ apt install mysql

$ apt install systemctl

### mysql 환경설정

```
sudo mysql_secure_installation
```
root password 설정하기

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';

### mysql User 만들기, 권한 설정

유저 생성
```
mysql> CREATE USER 'poro'@'localhost' IDENTIFIED BY 'password'; 
```

권한 부여 & 권한 확인
```
mysql> SHOW GRANTS FOR 'poro'@'localhost';
+------------------------------------------+
| Grants for poro@localhost                |
+------------------------------------------+
| GRANT USAGE ON *.* TO `poro`@`localhost` |
+------------------------------------------+
1 row in set (0.00 sec)
```

### exec과 attach의 차이점

도커에서 exec 명령어를 사용하여 우분투를 설치한 컨테이너에 접근하면, 새로운 bash 세션을 시작하고 해당 세션에서 명령을 실행합니다.
이는 기존에 실행중인 프로세스에 영향을 주지 않고, 새로운 프로세스를 실행하게 됩니다. exec 명령어를 사용하면 다음과 같은 이점이 있습니다.

- 컨테이너 내에서 새로운 프로세스를 시작하므로, 컨테이너 내에서 프로세스를 실행할 수 있는 방법이 필요한 경우에 유용합니다.
- 실행중인 프로세스에 영향을 주지 않으므로, 컨테이너를 재시작하지 않고도 작업을 수행할 수 있습니다.
- 
하지만, exec 명령어로 접근한 세션에서 exit 명령어를 사용하면 해당 세션이 종료되고, 컨테이너에서 나와집니다.
이는 해당 컨테이너가 종료되는 것이 아니므로, 다음과 같은 경우에는 문제가 발생할 수 있습니다.

- 컨테이너를 종료해야 하는 경우.
- 다른 사용자나 프로세스가 동시에 컨테이너에 접근하는 경우.
반면에, attach 명령어를 사용하여 우분투를 설치한 컨테이너에 접근하면, 현재 실행중인 프로세스에 직접 연결됩니다.
이는 다음과 같은 이점이 있습니다.

- 기존에 실행중인 프로세스에 연결되므로, 해당 프로세스를 직접 제어할 수 있습니다.
- exit 명령어로 세션을 종료하면 컨테이너 자체가 종료됩니다.
하지만, attach 명령어를 사용하여 접근한 세션에서는 새로운 프로세스를 시작할 수 없으므로, 새로운 프로세스를 실행해야 하는 경우에는 exec 명령어를 사용해야 합니다. 또한, attach 명령어로 접근한 세션에서 ctrl+c를 누르면 해당 프로세스가 종료되므로, 주의해야 합니다.

### mysql.sock 관련 에러
```
ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/var/run/mysqld/mysqld.sock' (111)
```
- 원인
  - MYSQL 데몬이 가동되어 있지 않은 경우
- 해결
  - systemctl start mysql.service - mysql 가동 후 소켓으로 접속

### locale 초기화되는 문제
- 로케일 설정한 컨테이너로 Image 만들기(commit)
```
> docker commit 컨테이너이름 생성할이미지이름 
```
위 경우, export로 설정한 환경 변수 값은 저장되지 않습니다. 환경 변수를 저장하려면 DockerFile 스크립트로 이미지를 만들어야합니다.

- Dockerfile 스크립트로 이미지 만들기

1. 스크립트 작성
```
FROM ubuntu/mysql

#Set the locale
RUN apt-get update
RUN apt-get -y upgrade #-y : Y/N 확인 시 yes를 실행한다.
RUN apt-get install locales
RUN apt-get install language-pack-ko
RUN locale-gen ko_KR.utf8
ENV LANG ko_KR.utf8
ENV LANGUAGE ko_KR.utf
ENV LC_MESSAGES POSIX
RUN update-locale LANG=ko_KR.utf8 LC_MESSAGES=POSIX
```

2. 이미지 빌드하기
```
> docker build -t poroubuntu 스크립트를담고있는파일경로
```

생성된 이미지로 다시 컨테이너를 만들어봅니다.
아래처럼, 컨테이너를 만들자마자 환경변수가 설정되어있음을 확인할 수 있습니다.
```
root@5339e90082e5:/# locale
LANG=ko_KR.utf8
LANGUAGE=
LC_CTYPE="ko_KR.utf8"
LC_NUMERIC="ko_KR.utf8"
LC_TIME="ko_KR.utf8"
LC_COLLATE="ko_KR.utf8"
LC_MONETARY="ko_KR.utf8"
LC_MESSAGES=POSIX
LC_PAPER="ko_KR.utf8"
LC_NAME="ko_KR.utf8"
LC_ADDRESS="ko_KR.utf8"
LC_TELEPHONE="ko_KR.utf8"
LC_MEASUREMENT="ko_KR.utf8"
LC_IDENTIFICATION="ko_KR.utf8"
LC_ALL=
```
도커 실행
```
docker run -d --name final-container -e TZ=UTC -p 30307:3307 -e MYSQL_ROOT_PASSWORD=Skarnjsdn1! final-container
```
3. mysql utf-8 설정
- 리눅스의 경우 초기 설정이 latin1로 설정된다.
- 로케일이 설정되어있는 경우 자동으로 들어가는듯?(혹은 높은 버전을 사용해서 그럴 수도)

```
mysql> status
--------------
mysql  Ver 8.0.32-0ubuntu0.22.04.2 for Linux on x86_64 ((Ubuntu))

Connection id:          8
Current database:
Current user:           root@localhost
SSL:                    Not in use
Current pager:          stdout
Using outfile:          ''
Using delimiter:        ;
Server version:         8.0.32-0ubuntu0.22.04.2 (Ubuntu)
Protocol version:       10
Connection:             Localhost via UNIX socket
Server characterset:    utf8mb4
Db     characterset:    utf8mb4
Client characterset:    utf8mb4
Conn.  characterset:    utf8mb4
UNIX socket:            /var/run/mysqld/mysqld.sock
Binary data as:         Hexadecimal
Uptime:                 7 min 56 sec

Threads: 2  Questions: 5  Slow queries: 0  Opens: 119  Flush tables: 3  Open tables: 38  Queries per second avg: 0.010
--------------
```

### 데이터베이스, 일반 사용자 생성

```
CREATE DATABASE mydb;
 아이디 및 패스워드 설정
CREATE USER 'myuserid'@'%' IDENTIFIED BY 'mypassword';
GRANT ALL ON mydb.* TO 'myuserid'@'%';
FLUSH PRIVILEGES;
```

### SQL?
SQL은 Structured Query Language의 약자로, 관계형 데이터베이스 관리 시스템(RDBMS)에서 사용되는 표준 쿼리 언어입니다.
SQL은 데이터베이스에서 데이터를 저장, 수정, 삭제, 검색하는 데 사용되며, 대부분의 관계형 데이터베이스에서 지원됩니다.
SQL은 다양한 기능을 가지고 있으며, 데이터베이스 스키마 정의, 데이터 검색 및 삽입, 업데이트, 삭제, 데이터베이스 보안, 트랜잭션 관리 등을 지원합니다. 
또한 SQL은 매우 간단하고 직관적인 문법을 가지고 있어, 데이터베이스 작업을 수행하기 위해 프로그래밍 지식이 없는 사람도 쉽게 사용할 수 있습니다.

### SQL 문법 종류
DDL, DML, DCL, TCL은 각각 SQL에서 사용되는 다양한 종류의 명령어 집합입니다.

1. DDL(Data Definition Language)
DDL은 데이터 정의 언어로, 데이터베이스 스키마를 정의하고 수정하는 데 사용됩니다. DDL 명령어는 데이터베이스, 테이블, 뷰, 인덱스 등을 생성, 수정, 삭제하는 데 사용됩니다.
대표적인 DDL 명령어로는 CREATE, ALTER, DROP 등이 있습니다.

2. DML(Data Manipulation Language)
DML은 데이터 조작 언어로, 데이터를 삽입, 수정, 삭제, 검색하는 데 사용됩니다. DML 명령어는 테이블에 저장된 데이터를 검색하고 조작하는 데 사용됩니다.
대표적인 DML 명령어로는 SELECT, INSERT, UPDATE, DELETE 등이 있습니다.

3. DCL(Data Control Language)
DCL은 데이터 제어 언어로, 데이터베이스 사용자에게 권한을 부여하거나 취소하는 데 사용됩니다. DCL 명령어는 데이터베이스 보안 및 권한 관리를 위해 사용됩니다.
대표적인 DCL 명령어로는 GRANT, REVOKE 등이 있습니다.

4. TCL(Transaction Control Language)
TCL은 트랜잭션 제어 언어로, 데이터베이스 트랜잭션을 제어하는 데 사용됩니다. TCL 명령어는 데이터베이스의 일관성과 무결성을 유지하기 위해 사용됩니다.
대표적인 TCL 명령어로는 COMMIT, ROLLBACK, SAVEPOINT 등이 있습니다.

따라서, 각각 DDL, DML, DCL, TCL의 목적은 다르지만, 데이터베이스를 관리하고 조작하는 데 필수적인 기능을 수행합니다.

### 트랜젝션?
트랜잭션(Transaction)은 데이터베이스의 상태를 변화시키기 위한 하나 이상의 데이터베이스 연산들을 모아 놓은 논리적인 작업 단위를 의미합니다. 예를 들어, 데이터베이스에 있는 계좌에서 어떤 금액을 출금하고 다른 계좌에 입금하는 작업을 수행하는 경우, 이러한 출금 및 입금 연산들을 하나의 트랜잭션으로 묶어서 처리할 수 있습니다.

트랜잭션은 다음과 같은 특징을 가지고 있습니다.

원자성(Atomicity): 트랜잭션은 모든 연산들이 전부 수행되거나 전부 수행되지 않는 원자적인 단위로 처리됩니다. 즉, 트랜잭션의 모든 연산들은 모두 성공적으로 완료되거나, 아무것도 수행되지 않은 것처럼 롤백되어야 합니다.

일관성(Consistency): 트랜잭션이 수행되면 데이터베이스의 상태는 일관성을 유지해야 합니다. 즉, 트랜잭션 실행 전과 후에 데이터베이스의 제약 조건이나 규칙 등이 항상 만족되어야 합니다.

격리성(Isolation): 트랜잭션은 다른 트랜잭션의 연산에 영향을 받지 않고 독립적으로 실행되어야 합니다. 즉, 트랜잭션 실행 중에 다른 트랜잭션에서 수행하는 연산들과 상호 간섭이 발생하지 않아야 합니다.

지속성(Durability): 트랜잭션의 결과는 영구적으로 반영되어야 합니다. 즉, 트랜잭션이 성공적으로 완료되면 그 결과는 언제든지 복구할 수 있어야 합니다.

이러한 트랜잭션의 특징은 데이터베이스의 무결성과 일관성을 유지하기 위해 매우 중요합니다. 트랜잭션은 데이터베이스에서 매우 빈번하게 사용되는 개념으로, 데이터베이스의 복구 및 보안 등 다양한 측면에서 중요한 역할을 합니다.

## MYSQL 문법

### CREATE
1. CREATE DATABASE
```
CREATE DATABASE mydb; #mydb라는 데이터 베이스를 생성
```
데이터 베이스 생성 확인 : SHOW DATABASES;
2. USE DATABASE
```
USE mydb; #mydb를 앞으로 사용한다는 의미
```

3. CREATE TABLE
데이터 베이스는 하나 이상의 테이블로 구성됩니다. 새로운 테이블을 생성합니다. 
```
CREATE TABLE user_log
(
  nickname varchar(64),
  money dec(10, 2),
  last_visit datetime
);
```
테이블 조회 : SHOW TABLES, DESC TABLENAME

### INSERT
레코드 추가
```
INSERT INTO user_log(nickname, money, last_visit)
VALUES(appleabc1234, 25000, 2023/02/12);
```

### SQL문 실행
source 명령어 + <파일명>으로 쿼리 일괄 실행이 가능하다.

### 프로시저 만들기
```
USE mydb;

CREATE TABLE words
(
  word VARCHAR(57)
);

# Enable loading local data
SET GLOBAL local_infile=true;


# Load Local Data
LOAD DATA LOCAL INFILE "/rawData.csv"
INTO TABLE mydb.words FIELDS TERMINATED BY ",";
```

클라이언트/서버 load data 허용하기
```
vi /etc/mysql/my.cnf

# 내용 추가
[mysql]
local_infile=1

# 만약 위 내용 추가만으로 되지 않으면 아래 내용도 추가
[mysqld]
local_infile=1
```


단어 목록(Chat GPT Generated)
```
abacus, abhor, abject, abjure, abnegate,
abrade, abrogate, abscond, absolve, abstain,
abstruse, accede, accentuate, acquiesce, acrimonious,
acumen, adamant, addle, adhere, adroit,
advent, adversity, aesthetic, affable, affinity,
aggrandize, alacrity, albeit, allay, allocate,
aloof, altruistic, amalgamate, ambivalent, amiable,
amorphous, anathema, animosity, anomalous, antipathy,
apex, apogee, apostate, apothegm, approbation,
arcane, arduous, articulate, ascendancy, ascetic,
assiduous, assuage, astringent, astute, atavistic,
attenuate, auspicious, austere, avarice, aver,
avuncular, axiom, bacchanalian, baleful, banal,
bane, bathos, bedlam, behemoth, beleaguer,
bellicose, belligerent, benediction, benefactor, benevolent,
benign, berate, bereft, beseech, blasphemy,
blithe, bombastic, boorish, bourgeois, brusque,
bucolic, burnish, cacophony, cajole, calumny,
campanile, candor, capacious, capitulate, capricious,
captivate, carouse, carp, castigate, caustic

```

SQL 스크립트
```
DELIMITER $$
DROP PROCEDURE IF EXISTS createData;
CREATE PROCEDURE createData()
BEGIN
        DECLARE i INT DEFAULT 1;
        DECLARE NAME VARCHAR(64);
        DECLARE MONEY DEC(10, 2);
        DECLARE LAST_VISIT DATETIME;

        DECLARE RANDWORD VARCHAR(64);
        DECLARE RANDSTR VARCHAR(3);
        DECLARE RANDNUM INT;

        WHILE (i <= 1000) DO
                SELECT word FROM words ORDER BY RAND() LIMIT 1 INTO RANDWORD;

                SELECT LEFT(UUID(),3) INTO RANDSTR;
                SELECT FLOOR(RAND()*1000) INTO RANDNUM;
                SET NAME = CONCAT(RANDWORD, RANDSTR, LPAD(RANDNUM,4,'0'));
                SET MONEY = FLOOR(RAND()*100000);
                SET LAST_VISIT = FROM_UNIXTIME(UNIX_TIMESTAMP('2023-01-21 00:00:00')+FLOOR(RAND()*(UNIX_TIMESTAMP('2023-02-21 00:00:00')-UNIX_TIMESTAMP('2023-01-21 00:00:00')+1)));
                INSERT INTO user_log(nickname, money, last_visit) VALUES(NAME, MONEY, LAST_VISIT);
                SET i = i + 1;
        END WHILE;
END $$
DELIMITER ;

CALL createData();
```

### Docker 실행

- [O] 가상 환경을 위해서 docker를 설치한다.
- [O] docker 기반으로 mysql server 5.7 버전을 설치한다.
- [O] docker 명령으로 mysql 컨테이너를 실행한다.
- [O] docker 명령으로 mysql 컨테이너에 bash로 접속한다.
- [O] 셀 환경변수와 locale 설정을 하고, mysql config를 latin1에서 utf8로 변경한다.
- [O] SQL 문법을 학습하고 DDL(Data Definition Language)과 DQL(Data Query Language) 예제를 연습한다.
- [O] docker 기반으로 mysql을 설치했는지 여부를 확인할 수 있도록 터미널 또는 GUI로 접속한 화면 캡처하고 gist에 함께 저장한다.

### DB 요구사항
- [O] Database 이름을 정하고 Database를 생성한다.
- [O] DB에 프로그램에서 연결할 User를 정하고 생성한다. 접속 권한을 설정한다.

### 대용량 데이터 생성
다음과 같은 정보를 포함하는 user_log 테이블을 생성한다.

    nickname varchar(64),
    money dec(10, 2),
    last_visit datetime



- [ ] user_log 테이블에 100만건의 대용량 데이터를 생성해서 넣어야 한다.
- [ ] 다음 조건을 만족하는 데이터를 직접 테이블에 넣거나 또는 INSERT 구문을 작성하는 스크립트를 작성한다.

### 데이터 생성 규칙
- [ ] 사용자 nickname 은 영어 단어 100개 + 랜덤 문자열 3자리 + 랜덤 숫자 4자리로 생성한다.
- [ ] money 는 1부터 100,000 사이 값을 적당하게 분포하게 만든다.
- [ ] last_visit 은 최근 한 달 사이로 랜덤 시각으로 생성한다.

### 추가 요구 사항
(선택) progress bar 만들기
- [ ] 데이터 생성을 할 때 몇 %가 생성되었는지 표시해주는 프로그레스 바를 표시한다.
가능하다면 프로그레스 바는 별도의 함수나 모듈로 만들어서 재사용이 가능한 형태로 만들어 본다.