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

/***
 *   新增了四个重要的函数式接口：函数形接口 、供给形接口、消费型接口、判断型接口
 */

// 函数形接口   有参数且需要返回值
@FunctionalInterface
interface Function<T, R>{
    R apply(T t);
}


// 供给形接口  无参数，指定返回类型，经常用于只注意过程的代码
@FunctionalInterface
interface Supplier<T>{
    T get();
}


// 消费形接口
@FunctionalInterface
interface Consumer<T>{
    void accept(T t);
    // 不需要返回值， 有参数，经常用于迭代
}

// 判断形接口
@FunctionalInterface
interface Predicate<T>{
    boolean test(T t);
  // 返回true false 经常用于判断
}
