package com.example.Impl;

import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentDiscountPolicyTest {

    private DiscountPolicy discountPolicy = new PercentDiscountPolicy();

    @Test
    @DisplayName("vip는 10퍼센트 할인율 적용")
    public void vip_O() {
        Member member = new Member(1L, "skydrive860@gmail.com", Grade.vip);

        assertEquals(1000,discountPolicy.discountFix(member, 10000));
    }

    /*
     실패 테스트도 꼭 만들어봐야한다.
     */

    @Test
    @DisplayName("vip가 아니면 10퍼센트가 적용되면 안됨")
    public void vip_x() {
        Member member = new Member(1L, "ued_123@gmail.com", Grade.normal);

        assertEquals(1000,discountPolicy.discountFix(member, 10000));
    }
}