package com.example.springprac;

import com.example.config.AppConfig;
import com.example.role.MemberService;
import static org.junit.jupiter.api.Assertions.*;

import com.example.single.StateFulService;
import com.example.single.SingleTon;
import com.example.single.StateLocalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class SingleToneTest {

    @Test
    @DisplayName("싱글 없는 순수한 컨테이너")
    public void pureContainer() {

        AppConfig appConfig = new AppConfig();

        //호출 할때마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        //호출 할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        /*
            참조값 확인 . 메모리 주소가 다르다는 것을 알 수 있다.
         */
        System.out.println(memberService);
        System.out.println(memberService2);

        assertNotSame(memberService, memberService2);

        /*
            객체가 생성될때 메모리 공간에 새로 할당을 함
            그런데 요청을 할때마다 이렇게 객체가 만들어지고 소멸되고를 반복하면 엄청나게 부하가 심할것임.

            어떤 서비스는 5만 tps 초당 5만개의 객체가 만들어진다는것

            따라서 해결방안은 객체를 한개만 생성을 하고. 공유하도록 설계하면 된다. -> (싱글턴 패턴)
         */

    }

    @Test
    @DisplayName("싱글톤 패턴 테스트 코드")
    public void singleTonTest() {
        SingleTon singleTon1 = SingleTon.getInstance();
        SingleTon singleTon2 = SingleTon.getInstance();

        /*
            참조 주소 확인
         */
        System.out.println(singleTon1);
        System.out.println(singleTon2);

        assertSame(singleTon1, singleTon2);
    }

    @Test
    @DisplayName("스프링 컨테이너의 싱글톤 이용 테스트 코드")
    public void SpringContextTestCode() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조 주소 확인
        System.out.println(memberService1);
        System.out.println(memberService2);
    }

    @Test
    @DisplayName("스프링 싱글톤의 상태를 유지하는 값을 사용할떄의 문제점 중요!")
    public void StateFulServiceTest() {

        ApplicationContext ac = new AnnotationConfigApplicationContext(SingleDi.class);
        StateFulService o1 = ac.getBean("stateFulService", StateFulService.class);
        StateFulService o2 = ac.getBean("stateFulService", StateFulService.class);

        o1.setOrder(1000);
        o2.setOrder(2000);

        assertNotEquals(1000, o1.getOrder());
    }

    @Test
    public void StateFulLocal() {

        /*
            스프링 빈은 언제나 무상태(어떠한 값을 계속 유지하는 것이 아닌)
            로 설계 해야한다
            구체적으로는 값을 담을떄 지역변수와 파라미터, ThreadLocal등을 사용한다.
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SingleDi.class);
        StateLocalService stateLocalService1 = ac.getBean("stateFulLocalService",StateLocalService.class);
        StateLocalService stateLocalService2 = ac.getBean("stateFulLocalService",StateLocalService.class);

        int order1 = stateLocalService1.getOrder(1000);
        int order2 = stateLocalService2.getOrder(2000);

        System.out.println(order1 + " " + order2);
        assertNotEquals(order1, order2);

    }

    static class SingleDi {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }

        @Bean
        public StateLocalService stateFulLocalService() {
            return new StateLocalService();
        }
    }
}