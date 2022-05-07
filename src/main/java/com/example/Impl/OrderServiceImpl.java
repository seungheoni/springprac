package com.example.Impl;

import com.example.dto.Member;
import com.example.dto.Order;
import com.example.role.DiscountPolicy;
import com.example.role.MemberRepository;
import com.example.role.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // 자동으로 final이 붙은 필드에 대한 생성자를 주입해줌 @Autowired와 생성자 모두 생략가능 -> Lombok기
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Member member, String itemName, int itemPrice) {

        Member m = memberRepository.findById(member.getId());
        int fixDiscountPrice = discountPolicy.discountFix(m,itemPrice);

        return new Order(m.getId(), itemName, itemPrice, fixDiscountPrice);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
