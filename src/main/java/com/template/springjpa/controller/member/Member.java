package com.template.springjpa.controller.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) default
//    @GeneratedValue(strategy = GenerationType.IDENTITY) mysql
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //H2 Database
    @Column(name="MEMBER_ID")
    private Long id;

    @Column
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
