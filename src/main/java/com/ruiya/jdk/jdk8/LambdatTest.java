package com.ruiya.jdk.jdk8;

public class LambdatTest {

    //   语法：( object str,....)[参数列表]   ->[箭头符号]     代码块或表达式
    /**
     *  Lambda表达式（基于函数的匿名表达式）
     *  特性：Lambda 的类型是从使用 Lambda 的上下文推断出来的。
     *  上下文中 Lambda 表达式需要的类型称为目标类型
     *  （一个 Lambda表达式所在的类的类型。并且必须存在一个目标类型）;
     *  匿名、函数、传递、简洁。
     */

    public static void main(String[] args) {
        TestLambda test = (str) -> System.out.println(str);
        test.test("你好");
    }
    /***
     * 新增方法引用
     * https://blog.csdn.net/jun55xiu/article/details/93201032
     */
    // 构造器引用

}
