package com.template.springjpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    /**
     * 좋은 단위 테스트에 대하여
     * : 좋은 단위 테스트란 순수 메소드의 동작 여부(올바른 input/output)에 대하여
     * 테스트를 진행하여 성공 하는 것에 초첨을 두는 것이 좋다.
     * 메소드와 SpringBoot/JPA를 엮어 테스트 하는 것은 순수한 의미의 단위 테스트라기 보다는
     * 통합 테스트 개념에 더 가까움
     * */

    @Test
    public void order_item() throws Exception{

    }


    @Test
    public void cancel_order() throws Exception{

    }

    @Test
    public void over_item_stock() throws Exception{

    }

}