package com.ruiya.jdk.jdk8;


/**
 * @author admin
 */ // 用作检查代码块
@FunctionalInterface
public interface Functional {
    /**
     * 特性说明：
     *
     * 1,函数式接口仅仅只有一个方法(非默认或静态方法)，用于显示转换成ladbma表达式。
     *
     * 2, java.lang.Runnable接口 java.util.concurrent.Callable接口是两个最典型的函数式接口。
     *
     * 3.如果一个函数式接口添加一个普通方法，就变成了非函数式接口（一般定义的接口）。
     *
     * 4.Jdk8 规范里添加了注解@FunctionalInterface来限制函数式接口不能修改为普通的接口

     */
    void method();

}
