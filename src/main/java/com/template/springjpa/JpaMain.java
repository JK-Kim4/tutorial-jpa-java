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
            member.changeTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("MemberB");
            member2.changeTeam(team);
            em.persist(member2);

            /*team.getMembers().add(member);
            team.getMembers().add(member2);
            em.flush();
            em.clear();*/

            Team findTeam = em.find(Team.class, team.getId());

            List<Member> members = findTeam.getMembers();
            System.out.println("========================");
            System.out.println("to string " + findTeam);
            System.out.println("========================");


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }
}
