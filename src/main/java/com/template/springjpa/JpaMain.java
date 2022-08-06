package com.template.springjpa;

import com.template.springjpa.controller.member.Member;
import com.template.springjpa.controller.order.Order;
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
            Member member = new Member();
            Order order = new Order();

            member.getOrders().add(order);
            order.setMember(member);
            
        }catch (Exception e){
            log.error("error occur", e);
            tx.rollback();
        }finally {
            em.clear();
        }
        emf.close();
    }
}
