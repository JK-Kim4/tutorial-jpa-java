package com.template.springjpa.service;

import com.template.springjpa.domain.Address;
import com.template.springjpa.domain.Member;
import com.template.springjpa.domain.Order;
import com.template.springjpa.domain.OrderStatus;
import com.template.springjpa.domain.item.Book;
import com.template.springjpa.domain.item.Item;
import com.template.springjpa.exception.NotEnoughStockException;
import com.template.springjpa.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    /**
     * 좋은 단위 테스트에 대하여
     * : 좋은 단위 테스트란 순수 메소드의 동작 여부(올바른 input/output)에 대하여
     * 테스트를 진행하여 성공 하는 것에 초첨을 두는 것이 좋다.
     * 메소드와 SpringBoot/JPA를 엮어 테스트 하는 것은 순수한 의미의 단위 테스트라기 보다는
     * 통합 테스트 개념에 더 가까움
     * */

    @Test
    public void order_item() throws Exception{

        //given
        Member member = createMember("tester");
        Book book = createBook("testBook", 13000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order order = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 Order", OrderStatus.ORDER, order.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, order.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량 이다",13000 * orderCount, order.getTotalPrice());


    }

    @Test(expected = NotEnoughStockException.class)
    public void over_item_stock() throws Exception{
        Member member = createMember("tester");
        Item item = createBook("testBook", 13000, 10);

        int orderCount = 11;

        orderService.order(member.getId(), item.getId(), orderCount);

        fail("제고 수량 부족으로 예외가 발생하여야 테스트 성공");
    }

    @Test
    public void cancel_order() throws Exception{
        Member member = createMember("member");
        Item item = createBook("test Book", 1000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("주문 취소시 상태는 CANCEL이다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 재고가 원복", 10, item.getStockQuantity());
    }



    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울", "마포구", "123-123"));
        em.persist(member);
        return member;
    }

}