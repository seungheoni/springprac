package com.example.config;

import com.example.Impl.FixDiscountPolicy;
import com.example.Impl.MemberServiceImpl;
import com.example.Impl.MemoryMemberRepositoryImpl;
import com.example.Impl.OrderServiceImpl;
import com.example.role.DiscountPolicy;
import com.example.role.MemberRepository;
import com.example.role.MemberService;
import com.example.role.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
      해당 인터페이스(MemberService) 에 해당하는 구현체(MemberServiceImpl)에 해당하는 인자까지 생성자 주입을 한다.
      이런식으로 설계 하면 실제 MemberServiceImpl에 MemberRepository 인터페이스에 직접 구현체를 선언할 필요 없게된다.
      즉 역할에만 집중할 수 있게 되고 구현은 생각하지 않아도 되게 된다. 실행에만 집중하면 된다.
      순수 구현체의 선정은 AppConfig가 해주기 때문이다.
      추후에 구현체가 변경된다고 하더라도 AppConfig에서 구현체만 다른것으로 교체해주면 되기 때문에 OCP 원칙과 DIP 원칙에 위배되지 않는다.

      이러한 것을 생성자 주입이라고 한다.
     */

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepositoryImpl();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
