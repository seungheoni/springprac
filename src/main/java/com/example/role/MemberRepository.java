package com.example.role;

import com.example.dto.Member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

}
