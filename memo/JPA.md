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
    - 엔티티 매니저 팩토리 생성
      - persistence.xml 파일에 명시되어있는 Database 정보를 참조하여 엔티티 매니저 팩토리를 생성한다.
      - 엔티티 매니저 팩토리는 JPA를 사용할 수 있도록 준비하는 역할을 한다.
      - 엔티티 매니저 팩토리는 전체 애플리케이션에서 딱 한 번만 생성되며 이를 공유하여 사용한다.
  - EntityManager em = emf.createEntityManager();
    - 앞서 생성한 엔티티 매니저 팩토리에서 엔티티 매니저를 생성한다. 
    - 개발자가 구현하는 기능에서 JPA와 관련된 기능은 대부분이 엔티티 매니저를 통하여 제공된다. (등록 / 수정 / 삭제 / 조회 등)
    - 엔티티 매니저는 데이터베이스 커넥션과 밀접한 관련이 있으므로, 스레드간 공유하거나 재사용을 하면 안된다. (데이터 오염 위험)
  - EntityTransaction et = em.getTransaction();
    - JPA사용에 있어 트랙잰션 기능을 사용해야 데이터의 변경할 수 있다. (트랜잭션이 없는 데이터 수정은 예외 발생)
  

  - 영속성 컨텍스트 & 엔티티 생명 주기
    - JPA의 올바른 사용을 위해서는 영속성 컨텍스트와 엔티티의 생명 주기에 대한 이해가 필요하다.
    - 영속성 컨텍스트
      - 
    - 엔티티 생명 주기
      - 비영속
      - 영속
      - 준영속
      - 삭제
  

- JPQL