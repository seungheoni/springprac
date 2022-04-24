package com.example.role;

import com.example.dto.Member;
import com.example.dto.Order;

public interface OrderService {

    Order createOrder(Member member, String itemName, int price);
}
