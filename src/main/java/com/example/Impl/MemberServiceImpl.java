package com.example.Impl;

import com.example.dto.Member;
import com.example.role.MemberRepository;
import com.example.role.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Autowired // 마치 applicationContext.getBean(MemberRepository.class); 처럼 구현체를 자동으로 찾아줌
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
