package com.example.springprac;

import com.example.Impl.MemberServiceImpl;
import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberAppTest {

    @Test
    @DisplayName("멤버 테스트 app 구동 테스트")
    public void memberApp() {

        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"skydrive860@gmail.com", Grade.normal);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        assertNotNull(findMember);

        assertEquals(findMember, member);

    }
}
