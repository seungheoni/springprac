package com.example.springprac;

import com.example.Impl.*;
import com.example.config.AppConfig;
import com.example.role.DiscountPolicy;
import com.example.role.MemberRepository;
import com.example.role.MemberService;
import com.example.role.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

public class SpringContextBeanTest {


    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("spring 컨테이너 빈 등록 테스트")
    public void ContextBeanTest() {


        String[] beanNames = ac.getBeanDefinitionNames();

        for(String name : beanNames) {

            Object bean = ac.getBean(name);
            System.out.println("name : " + name + ", object : " + bean);

        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    public void beanName() {

        MemberService memberService = ac.getBean("memberService",MemberService.class);

        assertNotNull(memberService);
        assertInstanceOf(MemberService.class, memberService);

    }

    @Test
    @DisplayName("타입으로 조회")
    public void beanType() {
        MemberService memberService = ac.getBean(MemberService.class);

        assertInstanceOf(MemberService.class, memberService);
    }

    @Test
    @DisplayName("빈이름으로 조회 안됬을떄")
    public void notBean() {

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxxx",MemberService.class));
    }


    @Test
    @DisplayName("타입으로 조회시 빈 타입이 여러개면 에러 발생")
    public void duplicateBeanType() {

        ac = new AnnotationConfigApplicationContext(AppConfigTest.class);

        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberService.class));
    }

    @Configuration
    static class AppConfigTest {

        @Bean
        public MemberService memberService() {
            return new MemberServiceImpl(new MemoryMemberRepositoryImpl());
        }

        @Bean
        public MemberService memberServiceJdbc() {
            return new MemberServiceImpl(new JdbcMemberRepositoryImpl());
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

}
