package com.template.springjpa.utils;

import javax.persistence.*;
import javax.persistence.Persistence;
public class Database {

    public static EntityManagerFactory getEntityManagerFactory(String perName){

        EntityManagerFactory emf = null;

        if(perName == "" || perName == null){
            throw new NullPointerException("생성 될 persistence 의 이름을 입력해 주세요");
            
        }else{
            return emf = Persistence.createEntityManagerFactory(perName);
        }
    }
}
