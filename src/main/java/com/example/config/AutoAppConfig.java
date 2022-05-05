package com.example.config;

import com.example.Impl.MemoryMemberRepositoryImpl;
import com.example.role.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Configuration이 들어간 자바 클래스는 빈 등록으로 제외한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
        basePackages = "com.example"
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepositoryImpl")
    MemberRepository memberRepository() {
        return new MemoryMemberRepositoryImpl();
    }

}
