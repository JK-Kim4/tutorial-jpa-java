package com.template.springjpa.repository;

import com.template.springjpa.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository //현재 클래스를 Component scan 하여 repository bean으로 등록
@RequiredArgsConstructor
public class MemberRepository {

    /*
    @PersistenceContext
    //Spring container에서 생성한 EntityManager를 선언한 객체에 주입
    private EntityManager em;*/
    private final EntityManager em;

    /*
    //직접 EntityManagerFactory를 주입받기 원할 경우 @PersistenceUnit사용
    @PersistenceUnit
    private EntityManagerFactory emf;*/

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return  em.createQuery("select m from Member m", Member.class)
                    .getResultList();
    }

    public Optional<Member> findById(Long id){
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
