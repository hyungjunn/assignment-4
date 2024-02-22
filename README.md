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