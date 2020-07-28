package com.fenger.demo;

import com.alibaba.fastjson.JSONObject;
import com.fenger.entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class OptionalDemo
{
    public static void main( String[] args )
    {
//        User nullUser = null;
//        User user = new User();
//        Optional<User> op = Optional.ofNullable(nullUser);
//        Optional<User> op2 = Optional.ofNullable(user);
////        User user2 = op.orElse(user);
////        User user2 = op.orElseThrow(() -> new RuntimeException());
////        User user3 = op.get();
////        System.out.println(user2);
//
//        op.ifPresent((e)-> System.out.println(e.getAge()));
//        op2.ifPresent((e)-> System.out.println(e.getAge()));
//        Optional<User> user1 = op2.filter((e) -> Optional.ofNullable(e).isPresent());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("123","dsada");
//        Optional.ofNullable(jsonObject.getString("456")).filter()
        System.out.println(test(null));
        User user = new User();
        user.setName("ainst swresds");
        Optional<List<String>> strings1 = Optional.ofNullable(user).filter(e -> !e.getName().isEmpty()).map(e -> e.getName())
                .flatMap(e -> {
                    String[] s = e.split(" ");
                    return Optional.ofNullable(Arrays.asList(s));
                });
        Optional<List<String>> strings = Optional.ofNullable(user).filter(e -> !e.getName().isEmpty()).map(e -> e.getName())
                .map(e -> {
                    String[] s = e.split(" ");
                    return Arrays.asList(s);
                });
        System.out.println(test(user));
    }

    // 以前的代码v2
    public static String test3(User user) {
        if (user != null && user.getName() != null) {
            return user.getName().toUpperCase();
        } else {
            return null;
        }
    }

    // 现在的代码
    public static String test(User user) {
        Optional<User> user2 = Optional.ofNullable(user);
        String s3 = Optional.ofNullable(user).map(User::getName).map(String::toLowerCase).orElse(null);
        return s3;
    }
}
