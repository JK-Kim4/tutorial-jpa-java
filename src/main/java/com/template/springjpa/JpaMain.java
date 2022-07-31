package com.template.springjpa;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성 -> persistence.xml의 name 사용
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        //엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();
        //트래잭션 획득
        EntityTransaction tx = em.getTransaction();

        //start
        try{
            System.out.println("logic start!");
            tx.begin();
            //.....logic
            logic(em);
            tx.commit();
        }catch (Exception e){
            log.error("error occur", e);
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }

    private static void logic(EntityManager em){
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("아이디");
        member.setAge(2);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //단건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("find member = " + findMember.getUsername() + ", age = " + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        System.out.println("member.size = " + members.size());

        //삭제
        em.remove(member);
    }

    public void testDetached(EntityManager em){
        Member member = new Member();
        member.setId("memberA");
        member.setUsername("memberA");

        em.persist(member);

        em.detach(member);




    }
}
