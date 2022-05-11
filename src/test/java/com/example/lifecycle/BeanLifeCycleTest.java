package com.example.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class BeanLifeCycleTest {

    @Test
    public void beanLifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean("networkClient",NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {


        @Bean//@Bean(initMethod = "init" , destroyMethod = "close") // 초기화 콜백과 소멸전 콜백을 이름을 정의하고 해당 빈에 정의해놓으면 된다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://lsh-test.com");
            return networkClient;
        }

    }

}