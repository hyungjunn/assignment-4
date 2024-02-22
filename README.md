## 네 번째 과제 (진도표 4일차)
API 들을 만들어 보며 API 개발에 익숙해져 보기

### 문제 1
우리는 작은 과일 가게를 운영하고 있습니다. 과일 가게에 입고된 "과일 정보"를 저장하는 API를 만들어 봅시다.
- HTTP method : `POST`
- HTTP path : `/api/v1/fruit`
- HTTP 요청 body
```
{
  "name": String, 
  "warehousingDate": LocalDate,
  "price": long
}
```
- 응답 : 성공시 HTTP status code 200

### 문제 2
과일이 팔리게 되면, 우리 시스템에 팔린 과일 정보를 기록해야 합니다. 스펙은 다음과 같습니다.
- HTTP method : `PUT`
- HTTP path : `/api/v1/fruit`
- HTTP 요청 Body
```
{
  "id" : long
}
```
- 응답 : 성공시 200

### 문제 3
특정 과일을 기준으로 팔린 금액, 팔리지 않은 금액을 조회하고 싶습니다. 
예를 들어
1. (1, 사과, 3000원, 판매 O)
2. (2, 사과, 4000원, 판매 X)
3. (3, 사과, 3000원, 판매 O)

와 같은 세 데이터가 있다면 우리의 API는 판매된 금액 : 6000원, 판매되지 않은 금액 : 4000원 이라고 응답해야 합니다.
구체적인 스펙은 다음과 같습니다.
- HTTP method : `GET`
- HTTP path : `/api/v1/fruit/stat`
- HTTP query
  - name : 과일 이름
  - 예시 `GET /api/v1/fruit/stat?name=사과`
- HTTP 응답 Body 예시
```json
{
  "salesAmount": 6000,
  "notSalesAmount": 4000
}
```