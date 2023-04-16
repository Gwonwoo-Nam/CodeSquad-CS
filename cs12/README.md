### Web이란
1. Web Client와 Server
- 클라이언트 : 서버에 고유한 URL로 리소스를 요청
- 서버 : 클라이언트의 요청에 대해 적당한 리소스를 응답으로 제공
- 프록시 : 클라이언트와 서버 사이에 존재, 캐싱, 필터링, 로드 밸런싱, 인증, 로깅 등 다양한 역할 수행

2. HTTP : HYPERTEXT TRANSFER PROTOCOL
- WWW 상에서 정보를 주고 받는 프로토콜(HTML뿐만 아니라 CSS, JS 등 모든 파일을 주고 받을 때 필요한 약속)
- TCP/UDP(전송 프로토콜)로 서버 80번 포트에 요청을 보낸다.
- Request
- Response

3. Web Browser 동작 방식
- 최초에 HTML을 요청해서 응답을 받는다.
- HTML에서 CSS, JS, 이미지 등 필요한 링크 정보를 추출한다.
- 추출 정보의 URL로 새로운 요청을 보낸다.
- 모든 웹 자원을 받아와서 렌더링을 시작한다.
- 1.1은 파이프라인, 2.0은 병렬처리로 성능 개선

### HTTP 특징
- 비교적 간단하며 확장 가능(모든 것이 HTTP)
- 상태가 없다.
- 클라이언트 - 서버 구조
- 비 연결성

### URI, URL, URN
URI는 리소스를 식별하는 주소이다.
URL 표준으로 시작해서 확장된 개념이 URI
- 스킴:사용자이름:비번@호스트:포트/경로;패러미터?쿼리#프래그먼트
- 프래그먼트 : 클라이언트에서만 사용되며 북마크 등 용도, 서버에 영향 없음

### 리소스 포맷(미디어 타입)
- MIME(Multipurpose Internel Mail Extensions) 타입으로 파일의 포맷 분류
- 전자우편을 위한 표준에서 유래, 웹에서 활용
- 브라우저들은 리소스를 내려받았을 때 해야 할 기본 동작이 무엇인지를 결정하기 위해 대게 MIME 타입을 사용
  - 확장자는 모든 운영체제에서 의미있게 적용되지 못하고, 정확하다는 보장이 없다.
  - 일부 타입은 매직 넘버로 시작되어 식별가능하지만 모든 타입이 그렇지는 않다.
- MIME 타입은 주타입/부타입으로 이루어진다.
  - text/html
  - text/plain
  - image/jpeg 등


## URL을 입력하면 일어나는 일
URL 주소를 입력하면, DNS 서버에서 URL 주소 <-> IP 주소로 변환을 받아 HTTP Request 요청 메시지를 만들어 서버에 보낸다.
서버는 HTTP Response 메시지를 만들어서 내용을 담아 회신한다. 필요한 내용은 다시 요청하고, 브라우저는 렌더링한다.
### DNS?
1. 사용자가 웹 브라우저를 열어 주소 표시줄에 www.example.com을 입력하고 Enter 키를 누릅니다.
2. www.example.com에 대한 요청은 일반적으로 케이블 인터넷 공급업체, DSL 광대역 공급업체 또는 기업 네트워크 같은 인터넷 서비스 제공업체(ISP)가 관리하는 DNS 해석기로 라우팅됩니다.
3. ISP의 DNS 해석기는 www.example.com에 대한 요청을 DNS 루트 이름 서버에 전달합니다.
4. ISP의 DNS 해석기는 www.example.com에 대한 요청을 이번에는 .com 도메인의 TLD 이름 서버 중 하나에 다시 전달합니다. .com 도메인의 이름 서버는 example.com 도메인과 연관된 4개의 Amazon Route 53 이름 서버의 이름을 사용하여 요청에 응답합니다.
5. ISP의 DNS 해석기는 Amazon Route 53 이름 서버 하나를 선택해 www.example.com에 대한 요청을 해당 이름 서버에 전달합니다.
6. Amazon Route 53 이름 서버는 example.com 호스팅 영역에서 www.example.com 레코드를 찾아 웹 서버의 IP 주소 192.0.2.44 등 연관된 값을 받고 이 IP 주소를 DNS 해석기로 반환합니다.
7. ISP의 DNS 해석기가 마침내 사용자에게 필요한 IP 주소를 확보하게 됩니다. 해석기는 이 값을 웹 브라우저로 반환합니다. 또한, DNS 해석기는 다음에 누군가가 example.com을 탐색할 때 좀 더 빠르게 응답할 수 있도록 사용자가 지정하는 일정 기간 example.com의 IP 주소를 캐싱(저장)합니다.
8. 웹 브라우저는 DNS 해석기로부터 얻은 IP 주소로 www.example.com에 대한 요청을 전송합니다. 여기가 콘텐츠가 있는 곳으로, 예를 들어 웹 사이트 엔드포인트로 구성된 Amazon S3 버킷 또는 Amazon EC2 인스턴스에서 실행되는 웹 서버입니다.
9. 192.0.2.44에 있는 웹 서버 또는 그 밖의 리소스는 www.example.com의 웹 페이지를 웹 브라우저로 반환하고, 웹 브라우저는 이 페이지를 표시합니다.

### HTTP 메시지와 동작방식

- 메시지 구조
  - 첫 줄
  - Headers
  - 줄바꿈
  - Body

### Request
1. Request Line : 첫줄, 통신 방식을 정의, 클라이언트, 통신방식 + 요청 정보 + 브라우저의 HTTP 버전
   - 메서드(통신 방식) : 서버가 어떤 방식으로 통신할 것인가?
     - GET : 데이터를 가져온다. URL 뒤에 쿼리 스트링으로 필요한 인자 전달, 필요한 데이터 or 웹페이지를 받아올 때 사용 
     - POST : 데이터를 전송, Body에 값을 붙여서 보내며 서버의 상태변화를 일으킨다.
     - PUT, PATCH, DELETE 등
     - 안전한 메서드(Safe Method) : HTTP가 정의하는 서버에 작용을 가하지 않는 안전한 메서드의 집합(GET, HEAD)
2. Request Headers : 헤더들
   - Host 헤더 : 인터넷에 연결된 웹 서버의 주소
   - UserAgent : 웹브라우저의 다른 이름
   - Accept-Encoding : 압축하여 전송 시 브라우저가 압축해제, 네트워크 리소스 절약 브라우저가 지원하는 압축방식 나열
   - If-Modified-Since : 수정되지 않은 경우 다운로드 받은 파일을 그대로 사용
3. Request Message body : 서버에게 보내야할 내용, 빈 줄로 구분
1,2를 합쳐서 Request Message Header라고 한다.

### Response
1. Status : 첫줄, version + status code + phrase(code를 풀어서)로 표현
- 404 NOT FOUND
- 200 Success
- 403 Forbidden
- 500 Internal Server Error

2. Response Header
- Content-type : text/html 등 해석 방법을 알려줌
- Content-Length : 콘텐츠의 길이, 사이즈를 알려줌(Byte 단위)
- Content-Encoding : 압축 방식을 알려줌
- Last-Modified : 최종 수정 날짜

### HTTP / HTTPS, SSL
- HTTP의 암호화된 버전
- 클라이언트와 서버 간 모든 커뮤니케이션을 암호화하기 위해 SSL, TLS를 사용
- 커넥션을 이용해서 클라이언트가 민감한 정보를 서버와 안전하게 주고받도록 도움

### Cache
#### 캐시란?
- 한 번 접속한 웹사이트는 다시 요청을 보내지 않도록 내용을 저장해놓는 것
#### 캐시의 분류
- Private Cache
  - 한 사용자에 의해서만 재활용, 브라우저 캐시 등
- Shared Cache
  - 여러 사용자에 의해 재활용, 프록시 캐시(ISP 측에서 로컬 네트워크 인프라 일부로 구축, 트래픽 완화)

#### 유효성 검증
캐시의 수명을 결정하는 로직(Lifetime)
1. Cache-Control 헤더의 max-age=N 디렉티브가 존재하면, 수명은 N과 같다.
2. Expires 해더가 존재하면, 수명은 Expires 헤더에서 Date 값을 뺀 것과 같다.
3. Last-Modified 헤더가 존재하면, 수명은 Date 헤더 값에서 Last-Modified 헤더 값을 뺀 것을 10으로 나눈 것(휴리스틱 알고리즘)

1. 수명이 남은 경우 - Fresh / 다한 경우 - Stale -> 남은 경우 요청을 보내지 않고 캐시 사용
2. 수명이 다한 경우 브라우저에 유효성을 검증하기 위해 HTTP 요청 전달 -> Validation
3. 유효한 경우 304(Not Modified)를 반환하고, 유효하지 않으면 새로운 자원을 Body에 담아 200을 반환한다.
   - 전자의 경우 기존 캐싱의 Age를 초기화 or 만료 시각 갱신


- 캐시를 제어하는 테크닉
  - cache-control : HTTP 프로토콜의 캐시 동작과 관련된 헤더
  - pragma : Cache 헤더, 1.1에서 한 가지 경우만 사용, 캐시 서버의 동작을 거부하는 것

#### 캐시 알고리즘
- LRU 알고리즘(Least Recently Used)
- 참조된 시간을 기준으로 교체될 페이지를 선정
- 가장 오래동안 참조되지 않은 페이지를 교체
- 주 기억장치에 접근할 때마다 참조 페이지에 대한 시간을 기록해야한다.

- LFU 알고리즘 (Least Frequently Used)
- 참조된 횟수를 기준으로 교체될 페이지 선정
- 가장 적게 참조된 페이지를 교체
- 참조 횟수가 동일하면 시간이 오래된 것을 교체
- 단점 : 최근 사용 시작한 프로그램을 교체시킬 수도 있다.

### 개인화
- 이전 처리 정보, 기록들을 웹사이트가 기억하도록 하는 것
- 쿠키 : 사용자의 상태 유지, 식별하도록 함
- web storage : 쿠키보다 많은 정보를 저장, 보안 우수

### proxy
- proxy : 웹 브라우저 웹 서버 사이의 중개 서버, 캐시를 대신해주거나 보안 공격을 막아줌, 사용자 요청을 여러대 서버로 분산 요청

### 네트워크 모니터링
- 웹 브라우저 Developer tools
- wireshark : http 외 모든 트래픽 감시 가능


## HttpAnalyzer 외부 라이브러리(JSoup)

1. 파싱
- Document 클래스에 저장
- parse() 메서드로 parse하며 Document 타입 객체 리턴
- URL로부터 파싱하는 방법 - GET 방식의 호출
  - http와 https만을 지원
```
Document doc = Jsoup.connect("http://naver.com/").get();
String title = doc.title();
```

2. 요소 탐색
- getElementById
- getElementsByTag
- getElementsByClass 

3. 편집
* attr(String key) 로 속성의 값을 얻습니다. attr(String key, String value)로 속성의 각을 설정할 수 있습니다.
* id(), className() 은 id와 class속성의 값을 가져옵니다. class는 여러개 지정되면 하나의 문자열로 반환됩니다.  예로 요소가 <div class="center red"> 라면 className() 은 "center red" 를 반환합니다. 하나씩 구하기 위해서는 classNames() 메소드를 사용합니다. Set<String> 타입으로 반환합니다.
* text()로 순수 텍스트만 구합니다. text(String value)로 요소의 텍스트를 설정할 수 있습니다.
* html()로 html 문자열을 구합니다. html(String value) 메소드로 inner HTML 을 설정합니다.
* outerHtml() 요소의 outer html을 반환합니다.

### HttpRequest, HttpUrlConnection

- URLConnection 클래스는 일반적인 URL에 대한 API 제공
- HttpURLConnection은 HTTP 고유 기능에 대한 추가 지원

1. URL 객체 만들기
2. URL에서 URLConnection 객체 얻기
3. URL 연결 구성
4. 헤더 필드 읽기
5. 입력 스트림 가져오기 및 데이터 읽기
6. 출력 스트림 가져오기 및 데이터 쓰기
7. 연결 닫기

## HTTP 분석기 구현

### 프로그래밍 요구사항
- 요청 처리 관련
- [O] 웹 브라우저처럼 URL을 입력하면 해당 주소로 요청을 보낸다.
- [O] HTTP 또는 HTTPS 처리를 위한 모듈 또는 라이브러리를 이용해서 요청을 보내고 응답을 받아서 처리한다.
- [O] 자바는 HttpURLConnection 또는 HttpRequest 클래스를 활용
- [O] 멀티 스레드 환경이 아니라 서버와 연결을 하나만 사용한다고 가정하고 순차적으로 진행한다
- [O] HTTP 또는 DOM Parser 모듈을 활용해서 응답에 포함된 HTML 을 분석한다.
- [O] HTML에 포함된 요소들 중에서 script 태그의 src 속성, img 태그의 src 속성에 있는 주소도 다시 요청을 보내서 받는다
- [O] HTML 파서 역할과 실제 요청을 모두 다 하는 모듈을 사용하기 보다는 파서 역할만 하는 모듈을 사용하기 권장한다
- [ ] 모든 요청을 보낸 시각, 요청에 대한 응답을 받은 시각, 다운로드가 끝난 종료 시각을 모두 기록한다. //다운로드 시간 구분 필요
- [O] 요청을 보내고 응답을 받을 때까지 시간을 응답 대기 시간으로 표기한다.
- [O] 응답 시작부터 마지막 바이트까지 다 받은 시간을 다운로드 시간으로 표기한다.

- 메모리 캐싱 관련
- [O] 요청 URL을 기준으로 이미지(png, gif, jpg)와 코드(css, js) 파일들은 메모리에 캐싱한다.
- [O] 동일한 URL을 다시 요청하는 경우는 메모리에 있는 캐시 데이터를 표시한다.
- [O] 캐시 데이터를 사용하면 로딩이 얼마나 빨라지는 지 비교한다.
- [O] 캐시 방식에 대해서는 LRU 캐시 말고 다른 방식으로 스스로 판단하고 결정한다.

### 구현 결과
```
> m.naver.com
도메인 m.naver.com
스킴 https
경로 
종류 html; charset=UTF-8
용량 KB
다운로드 시간 446ms

>> gfp-core.js
도메인 ssl.pstatic.net
스킴 https
경로 /tveta/libs/glad/prod/gfp-core.js
종류 javascript
용량 49.63KB
다운로드 시간 90ms

>> index_head.9ac14169.js
도메인 mm.pstatic.net
스킴 https
경로 /js/build/index_head.9ac14169.js
종류 javascript
용량 66.33KB
다운로드 시간 110ms

>> upload_1674188500084tM9vG.jpg
도메인 s.pstatic.net
스킴 https
경로 /static/www/mobile/edit/20230120_1095/upload_1674188500084tM9vG.jpg
종류 jpeg
용량 1.76KB
>> 캐시됨

=====

도메인 개수 : 4개
요청 개수 : 49개
이미지(png, gif, jpg) 개수 : 26개
코드(css, js) 개수 : 0개
전송 용량 : 2.9246MB
캐시 데이터 개수 : 35개
전체 로딩 시간 : 2692ms

가장 큰 용량 : 748.5KB
가장 오랜 대기 시간 : 446ms

```
