package com.template.springjpa.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;


}
