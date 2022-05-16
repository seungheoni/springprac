package com.example.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.objenesis.strategy.SingleInstantiatorStrategy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PrototypeTest {

    @Test
    public void prototypeTest() {

        var equalsBean = Optional.of(new AnnotationConfigApplicationContext(PrototypeBean.class))
                .map(context -> {
                    var equals = context.getBean(PrototypeBean.class) == context.getBean(PrototypeBean.class);
                    context.close();
                    return equals;
                });

        assertTrue(!equalsBean.get());

    }

    @Test
    @DisplayName("싱글톤에서 멤버변수로 프로토타입을 사용할때의 문제점")
    public void singleTonClientUsePrototype() {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(PrototypeBean.class,ClientBean.class);

        ClientBean clientBean1 = annotationConfigApplicationContext.getBean(ClientBean.class);
        int count = clientBean1.logic();
        assertEquals(1,count);

        ClientBean clientBean2 = annotationConfigApplicationContext.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        assertEquals(1, count2);

        annotationConfigApplicationContext.close();
    }


    @Test
    @DisplayName("위의 문제를 해결할 provider기능")
    public void ProviderFn() {

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {


        /*
         * 의존관계를 외부에서 주입(DI) 받는게 아니라 이렇게 직접 필요한 의존관계를 찾는 것을 Dependency Lookup (DL) 의존관계 조회(탐색) 이라한다.
         * 이러한 DL만 해주는 ObjectProvider라는 라이브러리를 스프링에서 제공해준다.
         */
        private final ObjectProvider<PrototypeBean> protoObjectProvider;

        public int logic() {

            PrototypeBean prototypeBean = protoObjectProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void addCount() {
            this.count ++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        /*
            소멸 콜백은 프로토타입에서 사용되지 않는다.
            프로토타입은 스프링 컨테이너에서 초기화 콜백까지 진행한 후 더이상 관리 하지 않기 때문
         */
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }
}
