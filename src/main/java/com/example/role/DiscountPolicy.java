package com.example.role;

import com.example.dto.Member;

public interface DiscountPolicy {

    int discountFix(Member member,int price);
}
