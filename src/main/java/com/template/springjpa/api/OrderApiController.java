package com.template.springjpa.api;

import com.template.springjpa.controller.OrderSearch;
import com.template.springjpa.domain.Order;
import com.template.springjpa.domain.OrderItem;
import com.template.springjpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        for (Order order : orders) {
            //강제 초기화 (to persistent context)
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return orders;
    }
}
