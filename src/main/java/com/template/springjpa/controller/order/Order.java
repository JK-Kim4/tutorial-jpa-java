package com.template.springjpa.controller.order;

import com.template.springjpa.controller.delivery.Delivery;
import com.template.springjpa.controller.member.Member;
import com.template.springjpa.controller.orderItem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_ID")
    private Long id;

//    @Column(name="MEMBER_ID")
//    private Long memberId;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;      // 주문 회원 (다:1)

    @OneToMany(mappedBy = "order")  // 주문 상품 (1:다)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery; // 배송 정보 (1:1)

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate; // 주문 시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태

    //연관 관계 메소드
    public void setMember(Member member){
        // 기존 관계 제거
        if(this.member != null){
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
