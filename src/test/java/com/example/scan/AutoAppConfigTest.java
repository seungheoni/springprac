package com.example.scan;

import com.example.Impl.MemberServiceImpl;
import com.example.config.AutoAppConfig;
import com.example.role.MemberService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    @DisplayName("ComponentScan을 통한 자동 빈 등록")
    public void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);

    }
}
