package com.template.springjpa.controller;

import com.template.springjpa.domain.Address;
import com.template.springjpa.domain.Member;
import com.template.springjpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberFrom", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * @Valid
     *  -> @NotNull(entity)
     *  필수값 검증
     *
     *  BindingResult
     *  -> 오류가 발생한다면 오류 정보를 BindingResult 객체에 담은 후 로직 실행
     * */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){

        //오류 발생 시
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
