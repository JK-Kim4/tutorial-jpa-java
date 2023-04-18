### fetch join

---

- SQL 조인 종류 X
- JPQL 성능 최적화를 위해 제공하는 기능
- 연관된 엔티티나 컬렉션을 SQL 한 번에 함꼐 조회하는 기능
- join fetch 명령어 사용

---


#### 엔티티 fetch join
- 회원을 조회하면서 연관된 팀도 함꼐 조회
- SQL을 보면 회원 뿐만 아니라 팀도 함께 select
- JPQL
  - select m from Member m join fetch m.team
- SQL
  - select M.*, T.* from Member M inner join Team t on ~~~
- 화면에 도서와 도서를 작성한 저자에 대한 정보를 모두 조회하고자 할 때
- 저자와 저서 목록을 모두 조회하고자 할 때