package com.template.springjpa.controller.orderItem;

import com.template.springjpa.controller.item.Item;
import com.template.springjpa.controller.order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ORDER_ITEM")
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;     // 주문 상품

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량
}
