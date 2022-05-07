package com.example.autowired;

import com.example.dto.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    public void autoWiredTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    }


    static class TestConfig {

        /*
            require = false 는 자동 등록 대상이 없으면 빈 주입을 안하는 옵션.
         */
        @Autowired(required = false)
        public Member setNoBean1(Member noBean1) {
            System.out.println("noBean1 :" + noBean1);
            return noBean1;
        }

        /*
            자동 등록 대상이 없으면 null로 등
         */
        @Autowired
        public Member setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 :" + noBean2);
            return noBean2;
        }

        /*
            Optional로 등록.
         */
        @Autowired
        public Optional setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 :" + noBean3);
            return noBean3;
        }

    }

}
