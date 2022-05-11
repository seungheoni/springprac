package com.example.autowired;

import com.example.config.AppConfig;
import com.example.config.AutoAppConfig;
import com.example.dto.Member;
import com.example.meta.Grade;
import com.example.role.DiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService service = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "skydrive860", Grade.vip);

        int resPrice = service.discount(member,10000,"fixDiscountPolicy");

        assertEquals(9000, resPrice);
    }

    static class DiscountService {
        private Map<String,DiscountPolicy> discountPolicyMap;
        private List<DiscountPolicy> discountPolicyList;

        @Autowired
        public DiscountService(Map<String,DiscountPolicy> discountPolicyMap, List<DiscountPolicy> discountPolicyList) {
            this.discountPolicyMap = discountPolicyMap;
            this.discountPolicyList  = discountPolicyList;
            System.out.println("discountPolicyMap : " + discountPolicyMap);
            System.out.println("discountPolicyList : " + discountPolicyList);
        }

        public int discount(Member member, int price, String fixDiscountPolicy) {
            DiscountPolicy discountPolicy = discountPolicyMap.get(fixDiscountPolicy);

            return discountPolicy.discountFix(member, price);
        }
    }
}
