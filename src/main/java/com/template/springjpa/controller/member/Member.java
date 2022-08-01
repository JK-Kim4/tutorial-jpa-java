package com.template.springjpa.controller.member;

import com.template.springjpa.controller.team.Team;
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

    @ManyToOne //Team 과 Member 는 1:N 관계
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    //Member.team field를 Team.TEAM_ID와 맵핑
    //Team 테이블의 값은 Team객체에서만 insert, update 되도록 설정
    private Team team;

    private String city;
    private String street;
    private String zipcode;

    public void setTeam(Team team){
        this.team = team;

        //무한 루프 빠지지 않도록 체크함
        if(!team.getMembers().contains(this)){
            team.getMembers().add(this);
        }
    }
}
