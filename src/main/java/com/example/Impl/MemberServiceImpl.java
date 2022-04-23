package com.example.Impl;

import com.example.dto.Member;
import com.example.role.MemberRepository;
import com.example.role.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository = new MemoryMemberRepositoryImpl();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
