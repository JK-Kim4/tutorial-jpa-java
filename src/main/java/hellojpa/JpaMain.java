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

            Movie movie = new Movie();
            movie.setActor("tester");
            movie.setDirector("tester");
            movie.setName("test");
            movie.setPrice(12344);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findmovie = em.find(Movie.class, movie.getId());

            System.out.println(findmovie == movie);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
