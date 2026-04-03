# Fixit
26.04.02 start

# 작업 시작 전 (항상)
git pull

# 작업 끝난 후 (항상)
git add .
git commit -m "작업 내용 간단히"
git push
```

## 프로젝트 구조 제안

Fixit의 핵심 기능을 정리하면 이렇게 될 것 같아요.

| 도메인 | 기능 |
|---|---|
| **업체(Shop)** | 회원가입, 로그인, 업체 정보 관리 |
| **고객(Customer)** | 고객 등록, 연락처, 이력 조회 |
| **수리 접수(Repair)** | 접수 생성, 상태 관리 (접수→진행→완료) |
| **명세서(Invoice)** | 부품/공임 항목, 금액 계산, PDF 출력 |

---

## 기술 스택 추천
```
Spring Boot 3.x
Spring Security + JWT     # 업체별 로그인
Spring Data JPA           # DB 연동
MySQL or PostgreSQL        # 메인 DB
