package com.fenger.java.base.collection;

import com.fenger.entity.User;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayDemo {

    //        数组泛型使用
    public <T> void getType(T[] typeArray) {
        for (T t : typeArray) {
            System.out.println(t);
        }
    }
    public User[] getUserArray() {
        User[] users = new User[]{ new User("小米1", 4), new User("小米1", 10),new User("小米1", 7), new User("小米1", 9), new User("小米1", 10)};
        return users;
    }

    public int[][] getIntErArray() {
        int[][] a3 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        return a3;
    }

    private void printIntArray(int[] number) {
        System.out.println("number: " + number);
        for (int i : number) {
            System.out.print(i + " ");
        }
    }
    private void printDoubleArray(double[] number) {
        System.out.println("number: " + number);
        for (double i : number) {
            System.out.print(i + " ");
        }
    }

    /**
     * 从数组说起
     *
     * @param <T>
     */
    @Test
    public <T> void test1Array() {
        String[] strArray = new String[6];
        getType(strArray);
//        Arrays 使用 转list 需要注意的是这里的list是Arrays里的一个内部类
//       继承关系ArrayList-> AbstractList -> AbstractCollection
//       而AbstractCollection 不支持add操作 会报UnsupportedOperationException异常
        /*
         *   Arrays.asList()方法的使用#
         * 　首先，该方法是将数组转化为list。有以下几点需要注意：
         *      （1）该方法不适用于基本数据类型（byte,short,int,long,float,double,boolean）
         *      （2）该方法将数组与列表链接起来，当更新其中之一时，另一个自动更新
         *      （3）不支持add和remove方法
         *      （4）由此其长度由原数组决定
         */
        List<String> strings = Arrays.asList(strArray);
        strArray[0] = "sdas";
        System.out.println(strings);
//        strings.add("str"); //报错 java.lang.UnsupportedOperationException
    }

    /**
     * 数组转流
     */
    @Test
    public void testStream() {
        String[] strArray = new String[6];
        int[] intArray = new int[10];
        IntStream intStream = Arrays.stream(intArray);
        Stream<String> strStream = Arrays.stream(strArray);
    }

    /**
     * Arrays 二分查找法
     *
     * 适用于对象类型 需要指定比较规则 或者对象本身实现Comparator 接口
     * 重载方法可以指定查找的数组的范围
     *
     * [1] 搜索值不是数组元素，且在数组范围内，从1开始计数，得“ - 插入点索引值”；
     *
     * [2] 搜索值是数组元素，从0开始计数，得搜索值的索引值；
     *
     * [3] 搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1);
     *
     * [4] 搜索值不是数组元素，且小于数组内元素，索引值为 – 1。
     */
    @Test
    public void testBinarySearch() {
        System.out.println(Arrays.binarySearch(new int[]{1, 2, 3, 5, 6, 7, 9}, 4));
        System.out.println(Arrays.binarySearch(new int[]{1, 2, 3, 5, 6, 7, 9}, 5));
        User 小米1 = new User("小米1", 2);
        User[] users = new User[]{小米1, new User("小米1", 4), new User("小米1", 5),new User("小米1", 7), new User("小米1", 9), new User("小米1", 10)};
        System.out.println(Arrays.binarySearch(users, 小米1, Comparator.comparingInt(User::getAge)));
    }

    /**
     * copyOf
     * 数组拷贝 需指定新数组的长度
     *        也可以使用copyOfRange 指定拷贝的范围
     *        对于对象类型 不会生成新的对象 对象的地址是一致的
     */
    @Test
    public void testCopyOf() {
        int[] ints = {1, 2, 3, 5, 6, 7, 9};
        int[] copyInts = Arrays.copyOf(ints, 3);
        int[] copyRangeInts = Arrays.copyOfRange(ints, 3,7);
        System.out.println(ints);
        System.out.println(ints.length);
        System.out.println(copyInts);
        System.out.println(copyInts.length);
        System.out.println(copyRangeInts);
        System.out.println(copyRangeInts.length);

        User[] userArray = getUserArray();
        User[] copyUser = Arrays.copyOf(userArray,10);
        System.out.println(userArray);
        System.out.println(copyUser);

//        二维数组拷贝 时 第二级数组可以按对象理解 地址未变化
        int[][] intErArray = getIntErArray();
        int[][] intCopys1 = Arrays.copyOf(intErArray, 10);
        System.out.println(intErArray);
        System.out.println(intErArray.length);
        System.out.println(intCopys1);
        System.out.println(intCopys1.length);
    }

    /**
     * 数组比较
     * 转换为字符串
     * 赋值
     * 计算hashCode
     */
    @Test
    public void testEquals() {
        int[][] a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] b = new int[][]{{1,2,3},{4,5,6},{7,8,10}};
        System.out.println("Arrays.equals(a,b) :" +Arrays.equals(a,b));
        System.out.println("Arrays.equals(a[1],b[1]) :" +Arrays.equals(a[1],b[1]));
//        deepEquals用于二维基本类型比较或对象比较
        System.out.println("Arrays.deepEquals(a,b) :" +Arrays.deepEquals(a,b));

//        转换为字符串 不适用于基本类型一维数组
        String s = Arrays.deepToString(a);
        System.out.println(s);
//        赋值
        int[] a1 = new int[10];
        Arrays.fill(a1,40);
        System.out.println(a1);
        Arrays.fill(a1,1,4,55);
        System.out.println(a1);

//        计算hashCode
        System.out.println(Arrays.hashCode(a1));
    }

    /**
     *  parallelSort 排序 对象需要指定规则 可以对某一子序列排序
     *  parallelPrefix 按顺序操作数组中的元素
     *  parallelSetAll setAll 由索引值计算一个指定类型的值
     */
    @Test
    public void testSortSet() {
        int[] sortArray = new int[]{1,3,4,67,8};
        Arrays.parallelSort(sortArray);
        printIntArray(sortArray);
        User[] users = getUserArray();
        Arrays.parallelSort(users,1,4,Comparator.comparingInt(User::getAge));
        getType(users);

//        按顺序操作数组中的元素
        int[] number = new int[]{1,3,4,67,8};
        int[] number2 = new int[]{1,3,4,67,8};
        Arrays.parallelPrefix(number,(i1,i2)->i1-i2);
//      result  1 -2 -6 -73 -81
        printIntArray(number);
        Arrays.parallelPrefix(number2,(i1,i2)->i1+i2);
//      result  1 4 8 75 83
        printIntArray(number2);

//        由索引值计算一个指定类型的值
        double[] doubleArray = new double[10];
        Arrays.parallelSetAll(doubleArray,e->e+1);
        printDoubleArray(doubleArray);
    }

    /**
     * Spliterator 实现并行遍历 并不 目前看来只是可以分割的迭代器
     */
    @Test
    public void testSpliterator() {
        int[] sortArray = new int[]{1,3,4,67,8,34,21,54,23,54,66,33};
        Spliterator.OfInt spliterator = Arrays.spliterator(sortArray,1,11);
//        spliterator.tryAdvance((Consumer<? super Integer>) e->System.out.println(e));
//        spliterator.tryAdvance((Consumer<? super Integer>) e->System.out.println(e));
//        spliterator.tryAdvance((Consumer<? super Integer>) e->System.out.println(e));
        while (spliterator.tryAdvance((Consumer<? super Integer>) e->System.out.println(e))) {

        }
        spliterator.forEachRemaining((int e)->{
            System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getId() + " " + e);
        });

        // 这个方法也可以构建一个Stream，然后再构建Spliterator
        Spliterator.OfInt test = IntStream.range(0, 100).spliterator();
        // 进行分割操作
        List<Spliterator.OfInt> ofInts = new ArrayList<>();
//        对迭代器进行分割 执行一次trySplit test分出一半
        Spliterator.OfInt ofInt = test.trySplit();
        Spliterator.OfInt ofInt1 = test.trySplit();
        Spliterator.OfInt ofInt2 = test.trySplit();
    }
}
