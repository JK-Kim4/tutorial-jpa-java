package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team1 = new Team();
            team1.setName("team1");
            em.persist(team1);

            Member member1 = new Member();
            member1.setUsername("tester");
            member1.setTeam(team1);
            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());

            //find member = class hellojpa.Team$HibernateProxy$Zx1128IM
            System.out.println("find member = " + m.getTeam().getClass());

            System.out.println("=================================");
            m.getTeam().getName();
            System.out.println("find member = " + m.getTeam().getClass());


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
