package hellojpa;

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
            /*Team team1 = new Team();
            team1.setName("team1");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("team2");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("tester");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("tester2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();*/

            /*Member m = em.find(Member.class, member1.getId());*/

            //LAZY Loading => class hellojpa.Team$HibernateProxy$Zx1128IM
            //EAGER Loading => class hellojpa.Team
            /*System.out.println("find member = " + m.getTeam().getClass());

            System.out.println("=================================");
            m.getTeam().getName();
            System.out.println("find member = " + m.getTeam().getClass());*/

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            /*
            Cascade 설정 안했을 경우
            em.persist(child1);
            em.persist(child2);
            em.persist(parent);*/

            /*
            * Cascade  설정
            * em.persist(parent);
            * */


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
