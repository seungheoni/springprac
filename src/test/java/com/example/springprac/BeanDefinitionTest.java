package com.example.springprac;

import com.example.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext acX = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    public void AnnotationContextBeanDefinitionTest() {
        String[] definNames = ac.getBeanDefinitionNames();

        for(String name : definNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("name : " + name + " info : "  +beanDefinition);
            }
        }
    }

    @Test
    public void XmlContextBeanDefinitionTest() {

        String[] definNames = acX.getBeanDefinitionNames();

        for(String name : definNames) {
            BeanDefinition beanDefinition = acX.getBeanDefinition(name);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("name : " + name + " info : "  +beanDefinition);
            }
        }
    }

}
