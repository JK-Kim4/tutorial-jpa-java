package com.template.springjpa.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DelivaryStatus delivaryStatus;

    @OneToOne(mappedBy = "delivery")
    private Order order;

}
