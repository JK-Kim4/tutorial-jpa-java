package com.template.springjpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {
    @Id
    @Column(name ="ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    //JAVA의 Enum사용 사 @Enumerated 선언 필요
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    //JAVA의 날짜 타입 사용시, @Temporal 선언
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    //길이 제한이 없는 필드 (CLOB, BLOB) 필드 선언시 @Lob 선언
    private String description;

}
