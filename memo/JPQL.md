## JPQL

-----

- JPA를 사용하면 Entity Object 중심으로 개발

- '검색' 결과가 필요할 경우 객체를 통하여 검색을 하기 때문에 a.get().get() 등과 같은 처리가 필요
  - DB 내 모든 데이터는 객체화 하여 검색하는 것은 현실적으로 불가능
  
- 검색 조건이 포함된 SQL 문으로 객체를 조회할 필요성
- SQL을 추상화 한 JPQL 제공


## Criteria

-----
- JPQL을 활용할 경우 동적 쿼리문 작성에 어려움이 있음
- 동적 쿼리 생성을 도와주는 Java Lib Criteria


## QueryDsl

-----
- JPAQueryFactory 객체 사용
- SQL 예약어 형태로 작성 가능하여 유지 보수 용이
- 오탈자에 대한 compile error로 runtime 시 발생하는 sql 오류 예방 가능




### 위 기술 외 JPA 영속성 컨텍스트와 무관한 기술들과 함께 사용할 경우 (springJdbcTemplate, mybatis 등) 데이터베이스의 올바른 반영을 위하여 강제적인 flush 가 필요하다