package com.template.springjpa.controller.team;

import com.template.springjpa.controller.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team") //Member.team을 맵핑 필드로 사용
    private List<Member> members = new ArrayList<Member>();

    public void addMember(Member member){
        this.members.add(member);
        if(member.getTeam() != this) {
            member.setTeam(this);
        }
    }

}
