package com.example.springprac;

import com.example.Impl.MemberServiceImpl;
import com.example.config.AppConfig;
import com.example.role.MemberService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingleToneTest {

    @Test
    @DisplayName("스프링 없는 순수한 컨테이너")
    public void pureContainer() {

        AppConfig appConfig = new AppConfig();

        //호출 할때마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        //호출 할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        /*
            참조값 확인 . 메모리 주소가 다르다는 것을 알 수 있다.
         */
        System.out.println(memberService);
        System.out.println(memberService2);

        assertNotSame(memberService, memberService2);

        /*
            객체가 생성될때 메모리 공간에 새로 할당을 함
            그런데 요청을 할때마다 이렇게 객체가 만들어지고 소멸되고를 반복하면 엄청나게 부하가 심할것임.

            어떤 서비스는 5만 tps 초당 5만개의 객체가 만들어진다는것

            따라서 해결방안은 객체를 한개만 생성을 하고. 공유하도록 설계하면 된다. -> (싱글턴 패턴)
         */

    }
}
