package com.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("test");
        helloLombok.setAge(21);

        System.out.println("helloLombok name : " + helloLombok.getName());
        System.out.println("helloLombok age : " + helloLombok.getAge());
        System.out.println(helloLombok);
    }

}
