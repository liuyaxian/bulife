package com.ruiya.jdk.jdk8;

public class FuncationTest implements  Functional{

    @Override
    public void method() {

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

    public static void  showTime(CurrentTimePrinter printer){
        printer.printCurrentTime();
    }

    public static void  getResult(int a, int b, Cal cal){
        System.out.println(cal.calOperation(a, b));
    }

    public static void  convert(int num, NumbertoString numbertoString){
        System.out.println(numbertoString.convert(num));
    }

    // 函数式编程：先考虑传入的参数 再考虑方法的实现
    public static void main(String[] args) {
        // ()->{}
//        showTime(() -> System.out.println(System.currentTimeMillis()));
//        getResult(10, 3, (a, b) -> a*b);
//        convert(9313, Integer :: toHexString);
        // 消费型接口
        Consumer<String> c = (x) -> System.out.println("hello" + x +"!");
        c.accept("sdfsf");

        // 供给型接口
        Supplier<String> s = () -> "你好呀！";
        System.out.println(s.get());

        // 函数型接口
        Function<String, Integer> f = (x) -> x.hashCode();
        System.out.println(f.apply("hwelle"));

        // 断言型接口
        Predicate<String> p = (x) -> x.length() >30;
        System.out.println(p.test("sksakg abcdefghijkliopxrstuvwxyz"));
    }
}



@FunctionalInterface
interface CurrentTimePrinter{
    /**
     * 有且仅有一个抽象方法
     */
    void printCurrentTime();

    /***
     * 2、java.lang.Object中的方法不算
     * @param var
     * @return
     */
    @Override
    boolean equals(Object var);
    /**
     * 3、java8 接口才可以有默认的方法实现 前提是方法名称必须使用default关键字修饰
     */
    default void defaultMethod() {
        System.out.println("haha");
    }

    /**
     * 4、静态方法
     */
    static void staticMethod() {
    }
}
@FunctionalInterface
interface Cal{
    int calOperation(int a,int b);
}
@FunctionalInterface
interface NumbertoString{
    String convert(int num);
}