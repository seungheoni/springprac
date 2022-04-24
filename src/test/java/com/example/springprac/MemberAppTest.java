package com.example.springprac;

import com.example.Impl.FixDiscountPolicy;
import com.example.Impl.MemberServiceImpl;
import com.example.Impl.OrderServiceImpl;
import com.example.dto.Member;
import com.example.dto.Order;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;
import com.example.role.MemberService;
import com.example.role.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberAppTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Test
    @DisplayName("멤버 테스트 app 구동 테스트")
    public void memberApp() {

        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"skydrive860@gmail.com", Grade.normal);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        assertEquals(findMember, member);

    }

    @Test
    @DisplayName("오더 기능 app 구동 Mocking테스트")
    public void OrderAppMockingTest(@Mock OrderService orderService) {

        MemberService memberService = new MemberServiceImpl();

        memberService.join(new Member(1L,"skydrive860@gmail.com",Grade.vip));

        Member member = memberService.findMember(1L);

        when(orderService.createOrder(member, "itemA", 10000))
                .thenReturn(createOrderTest(member,"itemA",10000));

        Order orderRes = orderService.createOrder(member, "itemA", 10000);

        System.out.println(orderRes.toString());

        assertEquals(9000, orderRes.calculateDiscountPrice());

    }


    public Order createOrderTest(Member member, String itemName, int itemPrice) {

        memberService.join(new Member(1L,"skydrive860@gmail.com",Grade.vip));

        Member m = memberService.findMember(1L);

        int fixDiscountPrice = discountPolicy.discountFix(m,itemPrice);

        return new Order(m.getId(), itemName, itemPrice, fixDiscountPrice);
    }
}
