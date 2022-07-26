package com.template.springjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            tx.begin();
            //.....logic
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }
}
