package com.template.springjpa;

import com.template.springjpa.domain.Member;
import com.template.springjpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("MemberA");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("MemberB");
            member2.setTeam(team);
            em.persist(member2);


            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            Member findMember2 = em.find(Member.class, member2.getId());
            findMember2.setTeam(null);
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("member name = " + m.getName());
            }


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }
}
