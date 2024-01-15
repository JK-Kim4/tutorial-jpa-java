package com.tutorial.jpa;

import com.tutorial.jpa.entity.Member;
import com.tutorial.jpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member("test1");
        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId()).get();
        assertThat(saveMember.getId()).isEqualTo(findMember.getId());
        assertThat(saveMember.getName()).isEqualTo(findMember.getName());
    }
}
