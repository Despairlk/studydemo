package com.fenger.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private Integer age;
    private String clazz;
    private boolean passed;

    public User(){

    }
    public User(String name,Integer age) {
        this.name = name;
        this.age = age;
    }
}
