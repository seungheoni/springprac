package com.example.Impl;

import com.example.dto.Member;
import com.example.role.MemberRepository;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepositoryImpl implements MemberRepository {

    private Map<Long,Member> userStore = new HashMap();

    @Override
    public void save(Member member) {

        userStore.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {

        return userStore.get(memberId);
    }
}
