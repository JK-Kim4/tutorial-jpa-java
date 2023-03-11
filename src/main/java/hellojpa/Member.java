/*
package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Member") // Default: Class Name
@Table // 맵핑 될 database 테이블 이름 별도 설정
public class Member {
    @Id
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Member(){

    }
}
*/
