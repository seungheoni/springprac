package com.example.springprac;

import com.example.Impl.MemberServiceImpl;
import com.example.config.AppConfig;
import com.example.dto.Member;
import com.example.role.MemberService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringXmlContextBeanTest {

    @Test
    @DisplayName("xml 컨텍스트")
    public void XmlContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);

        assertInstanceOf(MemberServiceImpl.class, memberService);
    }
}
