package com.example.single;

public class StateFulService {

    private int price;

    public void setOrder(int price) {
        this.price = price;
    }

    public int getOrder() {
        return this.price;
    }
}
