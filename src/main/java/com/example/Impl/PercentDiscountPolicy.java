package com.example.Impl;

import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;
import org.springframework.stereotype.Component;

@Component
public class PercentDiscountPolicy implements DiscountPolicy {

    private int percentDiscount = 10;

    @Override
    public int discountFix(Member member, int price) {

        if (member.getGrade() == Grade.vip) {
            int resPrice = price * percentDiscount / 100;
            return resPrice;
        } else {
            return price;
        }
    }
}
