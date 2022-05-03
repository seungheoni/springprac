package com.example.springprac;

import com.example.Impl.MemberServiceImpl;
import com.example.Impl.OrderServiceImpl;
import com.example.config.AppConfig;
import com.example.role.MemberRepository;
import com.example.role.MemberService;
import com.example.role.OrderService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingleTonTest {


    /*
      빈이 생성될때
          memberService() -> new MemberServiceImpl
          OrderService() -> new MemberServiceImpl
          이렇게 새로 할당하는데
          이러면 스프링 빈의 싱글톤이 꺠질까?
    */
    @Test
    @DisplayName("스프링 싱글톤이 깨지는지 테스트")
    public void configSingleTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberServiceImpl = ac.getBean("memberService",MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean("orderService",OrderServiceImpl.class);
        MemberRepository memberRepositoryOrg = ac.getBean("memberRepository",MemberRepository.class);

        MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
        MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();

        // 결과는 3개다 같다.
        System.out.println("memberService() -> " + memberRepository1);
        System.out.println("orderService() -> " + memberRepository2);
        System.out.println("orginal -> " + memberRepositoryOrg);

        assertEquals(memberRepository1, memberRepositoryOrg);
        assertEquals(memberRepository2, memberRepositoryOrg);
    }

    @Test
    public void ConfigurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println(bean);
    }
}
