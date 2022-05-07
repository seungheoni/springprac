package com.example.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {


    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterConfig.class);

        BeanA beanA = ac.getBean("beanA", BeanA.class);
        BeanB beanB;

        assertNotNull(beanA);
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class));
    }


    /*
        Component Filter를 통해 스캔시 제외하고 포함하는 정책을 설정 할 수 있다.
        FilterType은 5가지 옵션이 있다.
        - ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다. ex) org.example.SomeAnnotation
        - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다. ex) org.example.SomeClass
        - ASPECTJ: AspectJ 패턴 사용
          ex) org.example..*Service+
        - REGEX: 정규 표현식
          ex) org\.example\.Default.*
        - CUSTOM: TypeFilter 이라는 인터페이스를 구현해서 처리 ex) org.example.MyTypeFilter
     */
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION , classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION , classes = MyExcludeComponent.class)
    )
    static class ComponentFilterConfig {

    }

}
