package com.ruiya.jdk.jdk8;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;

/**
 * lambda 表达是的测试
 * JDK 中定义了很多函数式接口，主要在 java.util.function包下，
 * 还有 java.util.Comparator 专门用作定制比较器。另外，前面说的 Runnable也是一个函数式接口。
 */
public class LambdaTest {

//    // 无参数， 无返回值
//    () -> log.info("lambda");  等价于 ==>   log.info ("lambda")
//    // 有参数， 有返回值
//    (int a , int b) -> { a+b } 等价于 ==>
//    private int plus (int a, int b) {
//        return  a +b;
//    }


    // 最常见的一个例子就是新建线程
    public void ThreadTest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("新建线程并且启动线程");
            }
        }).start();

        new Thread(() -> System.out.println("新建线程并且启动线程")).start();
    }

    public static void main(String[] args) {
        // 还有很关键的一点，你的引用方法的参数个数、类型，返回值类型要和函数式接口中的方法声明一一对应才行。
        // ::双冒号作为方法引用的符号
        Function<String, Integer> s = Integer :: parseInt;
        Integer i = s.apply("10");
        System.out.println(i);
       Comparator<Integer> comparator =  Integer::compareTo;
        System.out.println(comparator.compare(1213, 121));

        IntBinaryOperator intBinaryOperator = Integer::compare;
        int result = intBinaryOperator.applyAsInt(10,100);
        new YxFunction<LocalDateTime, String, String>(){
            @Override
            public String run(LocalDateTime localDateTime, String s1){
                return localDateTime.toString();
            }
        }.run(LocalDateTime.now(), "yyyy-MM-dd HH:MM:ss");
    }
}

@FunctionalInterface
interface  YxFunction<T, R, S>{
    /**
     * 定义一个双参数的方法
     * @param t
     * @param s
     * @return
     */
    R run(T t,S s);
}
