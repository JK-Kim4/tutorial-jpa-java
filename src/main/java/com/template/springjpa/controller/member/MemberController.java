package com.template.springjpa.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.springjpa.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @RequestMapping("/member/save")
    public void saveTest(){
        Member m = new Member();
        m.setName("name");
        m.setCity("city");
        m.setStreet("street");
        m.setZipcode("zipcode");

        memberService.insert(m);

    }

}
