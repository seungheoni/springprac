package com.example.Impl;

import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    @DisplayName("OrderServiceImpl 테스트")
    public void OrderServiceTest() {

        Member member = new Member(1L, "seongheon", Grade.vip);

        MemoryMemberRepositoryImpl memoryMemberRepository = new MemoryMemberRepositoryImpl();
        memoryMemberRepository.save(member);

        OrderService orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());


        orderService.createOrder(member, "Hamberger", 6900);

    }


}