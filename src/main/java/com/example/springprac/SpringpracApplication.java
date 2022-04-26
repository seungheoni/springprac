package com.example.springprac;

import com.example.config.AppConfig;
import com.example.role.MemberService;
import com.example.role.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringpracApplication {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        /*
           앱구동시 생성자 주입!
         */
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        SpringApplication.run(SpringpracApplication.class, args);
    }

}
