HTTP : HYPERTEXT TRANSFER PROTOCOL

HTML, CSS, JS 등 주고 받을 때 필요한 약속
- Request
- Response

### Request
1. Request Line : 첫줄, 통신 방식을 정의, 클라이언트, 통신방식 + 요청 정보 + 브라우저의 HTTP 버전
   - 통신 방식 : 서버가 어떤 방식으로 통신할 것인가?
     - GET : 데이터를 가져올 때 GET 방식
     - HOST : 데이터를 전송
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
HTTPS로 통신하면 암호화되어 정보를 가로채더라도 무슨 내용인지 알 수 없다.

### Cache
- 한 번 접속한 웹사이트는 다시 다운로드 안받도록 내용을 저장해놓는 것
- 내용이 갱신된 경우 웹 브라우저가 어떻게 알아내야할까?
- 캐시를 제어하는 테크닉
  - cache-control
  - pragma 

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


