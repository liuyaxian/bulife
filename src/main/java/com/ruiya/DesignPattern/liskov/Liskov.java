package com.ruiya.DesignPattern.liskov;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/5 18:27
 * @history:
 */
public class Liskov {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.fun1(1,3));
    }
}


class A {
    public int  fun1(int a , int b){
        return a-b;
    }
}

class B extends  A{
    public int fun2(int a, int b){
        return  1+2;
    }
}