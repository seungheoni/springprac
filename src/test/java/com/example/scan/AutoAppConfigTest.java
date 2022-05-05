package com.example.scan;

import com.example.Impl.MemberServiceImpl;
import com.example.Impl.OrderServiceImpl;
import com.example.config.AutoAppConfig;
import com.example.role.DiscountPolicy;
import com.example.role.MemberRepository;
import com.example.role.MemberService;

import com.example.role.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    @DisplayName("ComponentScan을 통한 자동 빈 등록")
    public void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);


        /*
           @Autowired private MemberRepository memberRepository;
            @Autowired private DiscountPolicy discountPolicy;
         */
        OrderServiceImpl orderServiceImpl = ac.getBean(OrderServiceImpl.class);

        MemberRepository memberRepository = orderServiceImpl.getMemberRepository();
        DiscountPolicy discountPolicy = orderServiceImpl.getDiscountPolicy();

        System.out.println(memberRepository);
        System.out.println(discountPolicy);

        assertNotNull(memberRepository);
        assertNotNull(discountPolicy);

    }
}
