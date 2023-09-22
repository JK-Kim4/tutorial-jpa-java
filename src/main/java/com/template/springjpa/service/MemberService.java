package com.template.springjpa.service;

import com.template.springjpa.domain.Member;
import com.template.springjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//읽기 전용 Transaction 설정하여 성능 최적화
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //중복 회원 존재할 경우 throw Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //단건 조회
    public Member findMember(Long id){
        return memberRepository.findOne(id);
    }

    @Transactional
    public Long update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);

        return member.getId();
    }
}

