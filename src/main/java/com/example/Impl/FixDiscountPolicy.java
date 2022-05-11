package com.example.Impl;

import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
//@Qualifier("mainDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000;

    @Override
    public int discountFix(Member member, int price) {
        if(member.getGrade() == Grade.vip) {
            return  (price - discountFixAmount) > 0 ?  (price - discountFixAmount) : 0;
        } else {
            return 0;
        }
    }
}
