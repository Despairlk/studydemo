package com.fenger.demo;

import com.fenger.entity.User;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("周杰伦" + i);
            user.setAge(i);
            user.setClazz("三年二班");
            user.setId("32"+i + "");
            user.setPassed(i % 2 == 0);
            users.add(user);
        }
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("扁嘴伦" + i);
            user.setAge(i);
            user.setClazz("三年三班");
            user.setId("33"+i + "");
            user.setPassed(i % 2 == 0);
            users.add(user);
        }
        return users;
    }

    public static <T> void printList(List<T> list){
        list.stream().forEach(System.out::println);
    }
    @Test
    public void testMatch(){
        List<User> users = getUsers();
        boolean age0 = users.stream().allMatch(e -> e.getAge() > 0);
        boolean clazz1 = users.stream().allMatch(e -> e.getClazz().contains("三年"));
        boolean age1 = users.stream().anyMatch(e -> e.getAge() > 0);
        System.out.println(age0);
        System.out.println(age1);
        System.out.println(clazz1);
        System.out.println(users.stream().noneMatch(e->e.getAge()>30));
        System.out.println(users.stream().noneMatch(e->e.getAge()>10));
    }
    @Test
    public void test3(){
        List<User> users = getUsers();
        // 过滤满足条件的
        List<User> collect = users.stream().filter(User::isPassed).collect(Collectors.toList());
        System.out.println(collect.size());
        // count统计条数
        long count = users.stream().filter(User::isPassed).count();
        System.out.println(count);
        List<User> user2 = getUsers();
        user2.addAll(users);
        System.out.println(user2);
        // 去重复
        List<User> distinctedList = user2.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctedList);
    }
    @Test
    public void test4(){
        List<User> users = getUsers();
        Optional<User> any = users.stream().findAny();
        Optional<User> first = users.stream().findFirst();
        User user = any.get();
        User user1 = first.get();
        System.out.println(user);
        System.out.println(user1);
//        List<User> empty = new ArrayList<>();
//        Optional<User> any1 = empty.stream().findAny();
//        System.out.println(any1.get());
//        user2.stream().
    }
    @Test
    public void test5(){
        List<User> users = getUsers();
        // 1.需要实现接口 不然会报错
//        List<User> sorted = users.stream().sorted().collect(Collectors.toList());
        // 2.在接口中指定比较方法
        List<User> sorted2 = users.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
        List<User> sorted3 = users.stream().sorted((e1,e2)->{
            return e1.getAge()-e2.getAge();
        }).collect(Collectors.toList());
//        System.out.println(sorted);

        // 取一个最大值/最小值
        User maxAge = users.stream().max(Comparator.comparingInt(User::getAge)).get();
        System.out.println(maxAge);
        User minAge = users.stream().min(Comparator.comparingInt(User::getAge)).get();
        System.out.println(minAge);

//         截取前10个
        List<User> limit10 = users.stream().limit(10L).collect(Collectors.toList());
//         裁掉前10个
        List<User> skip10 = users.stream().skip(10L).collect(Collectors.toList());
        System.out.println(limit10.size());

//        使用 limit和skip可以实现分页
//        e.g:
        Long size = 5L;
        Long currentNum = 2L;
        List<User> users2 = users.stream().skip(size * (currentNum - 1)).limit(size).collect(Collectors.toList());
        System.out.println(String.format("分页第%s页 每页%s条",currentNum,size));
        printList(users2);
    }

    @Test
    public void test01() {
        List<User> aopiList = new ArrayList<>();

        User aopi = new User("1", 1);
        User aop2 = new User("2", 2);
        User aop3 = new User("3", 3);
        User aop4 = new User("4", 4);

        aopiList.addAll(Arrays.asList(aopi, aop2, aop3, aop4));

        //第一种方式
        System.out.println(aopiList);
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        aopiList.forEach(item -> item.setName(item.getName() + "_test"));
        System.out.println(
                aopiList.stream().min((o1, o2) -> {
                    if (Objects.equals(o1.getAge(), o2.getAge()))
                        return 0;
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                }).get().toString()
        );

        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        System.out.println(aopiList);
        //第二种方式
        System.out.println(
                aopiList.stream().peek(item -> item.setName(item.getName() + "_test")).min((o1, o2) -> {
                    if (Objects.equals(o1.getAge(), o2.getAge()))
                        return 0;
                    return o1.getAge() > o2.getAge() ? 1 : -1;
                }).get().toString()
        );
        System.out.println(aopiList);
    }
}
