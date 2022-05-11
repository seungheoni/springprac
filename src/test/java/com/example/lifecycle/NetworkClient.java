package com.example.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 패키지명이 javax일경우 자바 진형에서 아예 공식적으로 지원하는 것이기 떄문에 프레임워크가 달라도 사용할수있다.
/*
    스프링 초기의 초기화,종료 콜백 방법
    implements InitializingBean, DisposableBean
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message: " + message);
    }

    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
        의존관계 주입이 다끝나고 호출되는 초기화 콜백. InitializingBean을 implements 하여 사용한다.
        이방법들은 스프링 초기에 나온 방법들이고 이보다 더 나은 방법이 나와서 잘 사용하지는 않는다.
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        connect();
//        call("초기화 연결 메시지");
//    }
//
//    /*
//        종료될떄 호출되는 종료 콜백. DisposableBean implements 하여 사용한다.
//        이방법들은 스프링 초기에 나온 방법들이고 이보다 더 나은 방법이 나와서 잘 사용하지는 않는다.
//     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient destory");
//        disconnect();
//    }


    /*
        최근의 초기화 콜
     */

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    /*
        종료 콜백
     */

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
