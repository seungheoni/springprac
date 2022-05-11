package com.example.Impl;

import com.example.dto.Member;
import com.example.dto.Order;
import com.example.role.DiscountPolicy;
import com.example.role.MemberRepository;
import com.example.role.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // 자동으로 final이 붙은 필드에 대한 생성자를 주입해줌 @Autowired와 생성자 모두 생략가능 -> Lombok기
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

   // @Qualifier("mainDiscountPolicy")
    private final DiscountPolicy discountPolicy;
    /*
        같은 빈 타입이 2개 조회 되었을때 필드명으로 구현체 이름을 지정할 수도 있음. 이럴경우 알아서 스프링이 해당 필드명을 보고 구현체를 넣어줌.
        private final DiscountPolicy fixDiscountPolicy;

        @Qualifier("mainDiscountPolicy")
        생성자에 @Qualifier를 지정해서 사용 가능하다
        단. 자동주입 생성자와 스프링 빈으로 등록시에 양쪽다 @Qualifier를 줘야한다.

        롬북 에서 @Qualifier 사용시 방법이 있으니 wiki 참조
     */


    @Override
    public Order createOrder(Member member, String itemName, int itemPrice) {

        Member m = memberRepository.findById(member.getId());
        int fixDiscountPrice = discountPolicy.discountFix(m,itemPrice);

        return new Order(m.getId(), itemName, itemPrice, fixDiscountPrice);
    }

    //테스트용

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public DiscountPolicy getDiscountPolicy() {
        return discountPolicy;
    }
}
