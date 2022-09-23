package com.template.springjpa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.template.springjpa.controller.member.Member;
import com.template.springjpa.utils.Database;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

    private EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    public MemberService(){
        emf = Database.getEntityManagerFactory("jpabook");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
 

    public void insert(Member m){
            tx.begin();
        try{
            em.persist(m);
            tx.commit();
        }catch(Exception e){
            log.error("member insert error", e);
            tx.rollback();
        }finally{
            
        }
    } 

}
