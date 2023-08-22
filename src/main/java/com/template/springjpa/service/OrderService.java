package com.template.springjpa.service;

import com.template.springjpa.controller.OrderSearch;
import com.template.springjpa.domain.Delivery;
import com.template.springjpa.domain.Member;
import com.template.springjpa.domain.Order;
import com.template.springjpa.domain.OrderItem;
import com.template.springjpa.domain.item.Item;
import com.template.springjpa.repository.ItemRepository;
import com.template.springjpa.repository.MemberRepository;
import com.template.springjpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //order
    @Transactional
    public Long order(Long memberId, Long itemId, int quantity){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //(sample)배송 정보 생성 -> Delivery Repository 생성하는게 더 좋아
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), quantity);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();

        /*
        * Order.orderItems cascade 옵션을 설정 함에 따라 Order persist 시 orderItem 동시에 persist 실행
        * Order.delivery 동일
        * */
    }

    //cancel
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    //search
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }
}
