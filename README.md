# 🎉 Spring User-Curreny

## 📖 소개
Spring을 이용해 구현한 간단한 User - Curreny 환전 CRUD 입니다. 

## 🛠️ 개발환경
- **IDE**: IntelliJ
- **DATABASE**: mySql
- **API**: Postman


## API 명세서
![image](https://github.com/user-attachments/assets/d3f3f720-b7c5-483b-aea4-1ac2df905bfb)
![image](https://github.com/user-attachments/assets/14e0a21f-0511-4547-b3ef-5bee72e04d82)
![image](https://github.com/user-attachments/assets/b6139074-a3ce-46d1-b292-dfae9ab0beeb)





## ERD
![image](https://github.com/user-attachments/assets/e365853d-2091-4f5b-9eb5-9737351e2eab)





## 📋 요구사항 정의
### - Lv 1. 고객(User)과 통화(Currency) 복잡한 연관관계🔢
- [✅]  환전 요청 중간 테이블 생성
- [✅]  고객과 통화 테이블 간 연관관계

### - Lv 2. 환전 요청 CRUD 🔢
- [✅]  CRUD
  
### - Lv 3. 예외 처리 🔢
- [✅]  Validation
- [❌]  API 예외처리

### - Lv 4. PostConstruct 적용 🔢
- [❌]  스프링이 구동될 때 통화 테이블에 있는 환율이 0이거나 음수이거나 지정된 범위를 벗어나는 경우
    
### - Lv 5. JPQL 🔢
- [✅]  고객의 모든 환전 요청을 그룹화하여 조회

### - Lv 6. 달러 이외 통화를 환전할 수 있도록 수정 🔢
- [✅]  Currency 테이블에 다른 통화에 대한 데이터를 추가

## 🐞 Troubleshooting
--

https://velog.io/@dirtbags/TroubleShooting-24.11.29
