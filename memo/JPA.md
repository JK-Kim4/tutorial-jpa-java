## JPA 실습 코드 정리

---

- JPA 교재 실전 예제 따라 코드 작성

5.연관관계 매핑 기초 챕터 까지 작성 완료 하였으므로 그간 작성한 
코드를 데이터 설계 와 비교하며 복습 진행하기로 한다. ( 2022.08 11 )

- ORM (Object-Relational Mapping) : 데이터베이스를 객체의 형태로 다루는 방식.
  - 기존의 SQL 방식은 데이터베이스를 객체가 아닌 별도의 언어를 통해 다루기 떄문에 객체 지향 언어에서 페러다임의 불일치 문제가 발생
  - SQL insert : 
    - ``` INSERT INTO TABLE ... VALUES (...)```
  - JPA insert : 
    - ``` jpa.persist(object)```
---
  
- @Annotation( javax.persistence 라이브러리에서 제공하는 JPA 관련 어노테이션 정리)
    - @Entity
      - @Id
      - @Column
    - @Table

- JPA 구현체
  - EntityManagerFactory emf = Persistence.createEntityManagerFactory("emfName");
  - EntityManager em = emf.createEntityManager();
  - EntityTransaction et = em.getTransaction();