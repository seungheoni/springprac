package com.example.single;

public class SingleTon {

    private static final SingleTon singleTon = new SingleTon();

    public static SingleTon getInstance() {
        return singleTon;
    }

    private SingleTon() {}
}
