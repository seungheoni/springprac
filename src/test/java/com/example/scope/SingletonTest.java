package com.example.scope;

import com.example.single.SingleTon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonTest {

    @Test
    public void singleTonTest() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingleTonBean.class);

        SingleTonBean singleTon = ac.getBean(SingleTonBean.class);
        SingleTonBean singleTon2 = ac.getBean(SingleTonBean.class);

        System.out.println("address = " + singleTon);
        System.out.println("address = " + singleTon2);
        assertEquals(singleTon, singleTon2);

        ac.close();
    }


    @Scope("singleton")
    static class SingleTonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingleTonCls.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingleTonCls.destroy");
        }

    }
}
