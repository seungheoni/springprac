package com.example.Impl;

import com.example.dto.Member;
import com.example.role.MemberRepository;
import com.example.role.MemberService;

public class JdbcMemberRepositoryImpl implements MemberRepository {

    @Override
    public void save(Member member) {

    }

    @Override
    public Member findById(Long memberId) {
        return null;
    }
}
