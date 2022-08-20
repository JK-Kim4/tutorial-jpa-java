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
  - 매핑 정보를 등록하는 방식은 xml과 annotation 방식이 있으며 annotation 방식이 일반적으로 사용된다. 
      - @Entity(필수) : 테이블과 매핑할 클래스를 지정하며, @Entity가 붙은 클래스는 JPA 에서 관리한다.
        - 속성
          - name : JPA 에서 사용할 엔티티 이름 설정
        - 특징
          - 기본 생성자는 필수
          - final 클래스, enum, interface, inner 클래스에는 사용할 수 없다.
          - 저장할 필드에 final 사용하면 안됨
      - @Id
      - @Column
      - @Table : Entity와 매핑할 테이블을 지정. 생략이 가능하며 생략시 엔티티의 이름을 테이블명으로 사용한다.
        - 속성
          - name : 매핑할 테이블의 이름
          - catalog : catalog 기능이 있는 데이터베이스에서 catalog를 맵핑
          - schema : schema 기능이 있는 데이터베이스에서 schema를 맵핑
          - uniqueConstraints : DDL 생성 시 유니크 제약 조건을 추가로 생성 (2개 이상 복합 유니크 제약조건 생성 가능~~)
  

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
      - 엔티티를 영구 저장하는 환경
      - ```entityManager.persist(object);```
      - 엔티티 매니저를 통해 특정 객체를 영속성 컨텍스트에 저장
      - 여러 엔티티 매니저가 같은 영속성 컨텍스트에 접근할 수 있음.
    - 엔티티 생명 주기
      - 비영속 : 영속성 컨텍스트와 전혀 관계 없는 상태
        - persist()호출 전, 객체를 생성한 상태
      - 영속 : 영속성 컨텍스트에 저장된 상태 em.persist();
        - entityManager를 통해 엔티티를 영속성 컨텍스트에 저장, 관리하는 상태.
        - em.find() / JPQL로 저장된 엔티티 조회 및 관리 가능
      - 준영속 : 영속성 컨텍스트에 저장되었다가 분리된 상태
        - em.detach(object); 를 호출하여 영속성 컨텍스트에서 엔티티가 분리된 상태
      - 삭제 : 삭제된 상태
        - em.remove(object); 영속성 컨텍스트와 데이터베이스 에서 데이터를 삭제
  

- JPQL