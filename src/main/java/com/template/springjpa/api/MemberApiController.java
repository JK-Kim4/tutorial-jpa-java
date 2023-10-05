package com.template.springjpa.api;

import com.template.springjpa.domain.Member;
import com.template.springjpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> findMembersV1(){
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result findMembers(){
        List<Member> members = memberService.findMembers();
        List<MemberDto> collect = members.stream().map(m -> new MemberDto(m.getId(), m.getName())).collect(Collectors.toList());

        return new Result(collect);
    }

    @PostMapping("/api/v1/member")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @PostMapping("/api/v2/member")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest member){
        Member member1 = new Member();
        member1.setName(member.getName());
        Long join = memberService.join(member1);

        return new CreateMemberResponse(join);
    }

    @PutMapping("/api/v1/members/{id}")
    public UpdateMemberResponse updateMember(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateMemberRequest request){

        Long update = memberService.update(id, request.getName());
        Member member = memberService.findMember(update);

        return new UpdateMemberResponse(member.getId(), member.getName());
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private Long id;
        private String name;
    }

    @Data
    @Getter
    static class UpdateMemberRequest{
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest{
        private String name;
    }

}
