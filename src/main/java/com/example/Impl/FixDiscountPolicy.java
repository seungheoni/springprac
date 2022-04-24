package com.example.Impl;

import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;

public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discountFix(Member member, int price) {
        if(member.getGrade() == Grade.vip) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
