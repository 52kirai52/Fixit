# Fixit Server

## 프로젝트 소개
카센터(3차 공업사) 전용 정비 관리 시스템입니다.
차량 입고부터 정비 완료까지의 프로세스를 간편하게 관리할 수 있습니다.

---

## 기술 스택
- Java 21
- Spring Boot 3.4.4
- Spring Security + JWT
- Spring Data JPA
- MySQL 8.0
- Flyway

---

## 실행 방법

### 사전 준비
- Java 21
- MySQL 8.0
- Gradle

### application.properties 설정
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fixit
spring.datasource.username=fixit_user
spring.datasource.password=비밀번호
```

### 실행
```bash
gradlew bootRun
```

---

## API 문서

> 모든 API는 `/api/users/register`, `/api/users/login` 을 제외하고 JWT 토큰이 필요합니다.
> 
> `Authorization: Bearer {토큰}` 헤더에 포함해서 요청하세요.

---

### 인증

#### 회원가입
```
POST /api/users/register
```
**Request**
```json
{
    "username": "hong123",
    "password": "1234",
    "name": "홍길동",
    "phone": "010-1234-5678"
}
```
**Response**
```
200 OK
회원가입 성공
```

#### 로그인
```
POST /api/users/login
```
**Request**
```json
{
    "username": "hong123",
    "password": "1234"
}
```
**Response**
```
200 OK
eyJhbGciOiJIUzI1NiJ9...
```

---

### 사업체

#### 사업체 등록
```
POST /api/shops/register
```
**Request**
```json
{
    "name": "홍길동 카센터",
    "address": "서울시 강남구 테헤란로 123"
}
```
**Response**
```
200 OK
사업체 등록 성공
```

---

### 고객

#### 고객 등록
```
POST /api/customers
```
**Request**
```json
{
    "name": "김철수",
    "phone": "010-1234-5678"
}
```
**Response**
```
200 OK
고객 등록 성공
```

#### 전화번호로 고객 검색
```
GET /api/customers/search?phone={전화번호}
```
**Response**
```json
{
    "id": 1,
    "name": "김철수",
    "phone": "010-1234-5678"
}
```

---

### 차종 프리셋

#### 차종 등록
```
POST /api/vehicle-models
```
**Request**
```json
{
    "name": "소나타 2020"
}
```
**Response**
```
200 OK
차종 등록 성공
```

#### 차종 목록 조회
```
GET /api/vehicle-models
```
**Response**
```json
[
    {
        "id": 1,
        "name": "소나타 2020"
    }
]
```

---

### 차량

#### 차량 등록
```
POST /api/vehicles
```
**Request**
```json
{
    "customerId": 1,
    "modelId": 1,
    "plateNumber": "12가3456",
    "lastMileage": 50000
}
```
**Response**
```
200 OK
차량 등록 성공
```

#### 번호판으로 차량 검색
```
GET /api/vehicles/search?plateNumber={번호판}
```
**Response**
```json
{
    "id": 1,
    "plateNumber": "12가3456",
    "modelName": "소나타 2020",
    "customerName": "김철수",
    "lastMileage": 50000
}
```

---

### 정비

#### 정비 접수
```
POST /api/repairs
```
**Request** (vehicleId 또는 plateNumber 중 하나)
```json
{
    "vehicleId": 1
}
```
```json
{
    "plateNumber": "12가3456"
}
```
**Response**
```
200 OK
정비 접수 성공
```

#### 정비 목록 전체 조회
```
GET /api/repairs
```
**Response**
```json
[
    {
        "id": 1,
        "status": "RECEIVED",
        "plateNumber": "12가3456",
        "customerName": "김철수",
        "modelName": "소나타 2020",
        "createdAt": "2026-04-18T08:00:00"
    }
]
```

#### 상태별 정비 목록 조회
```
GET /api/repairs/status?status={상태}
```
> status: RECEIVED / IN_PROGRESS / DONE / CLOSED

#### 정비 상태 변경
```
PATCH /api/repairs/{id}/status?status={상태}
```
**Response**
```
200 OK
상태 변경 성공
```

---

## ERD

https://www.erdcloud.com/d/QtLjMdgKuMNzFW5NJ

---