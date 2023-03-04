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
            //1.insert
            /*Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);*/

            //2. select
            /*Member member = em.find(Member.class, 1L);
            System.out.println(member.getId());
            System.out.println(member.getName());*/

            //3. remove
            /*Member member = em.find(Member.class, 1L);
            em.remove(member);*/

            //4. update
            /*Member member = em.find(Member.class, 1L);
            member.setName("updatename");*/

            //5. JPQL
            List<Member> members =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            members.stream().forEach(System.out::println);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }

    }
}
