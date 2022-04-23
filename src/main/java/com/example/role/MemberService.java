package com.example.role;

import com.example.dto.Member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
