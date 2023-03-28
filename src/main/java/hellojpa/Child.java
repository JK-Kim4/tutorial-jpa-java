package hellojpa;

import javax.persistence.*;

@Entity
public class Child {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    public void setParent(Parent p){
        parent = p;
    }
}
