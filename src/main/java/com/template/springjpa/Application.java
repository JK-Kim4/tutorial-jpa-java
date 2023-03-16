package com.template.springjpa;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }

}
