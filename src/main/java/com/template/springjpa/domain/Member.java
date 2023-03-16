package com.template.springjpa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    /*@ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
