package com.ruiya.DesignPattern.单例模式;

/**
 * @desc: 饿汉式（静态常量）
 * @author: admin
 * @since: 2022/1/19 10:21
 * @history:
 */
public class Singleton01 {
    /**
     * 1、构造器私有化（防止new）
     * 2、类的内部创建对象
     * 3、向外暴露一个静态的公共方法，getInstance
     * @param args
     */
    public static void main(String[] args) {
        Singleton01 singleton01 = Singleton01.getInstance();
        Singleton01 singleton011 = Singleton01.getInstance();
        System.out.println(singleton01 == singleton011); //true
        System.out.println(singleton01.hashCode()); // 99747242
        System.out.println(singleton011.hashCode());  // 99747242
    }

    private static Singleton01 instance = new Singleton01();

    private Singleton01(){

    }
    public static Singleton01 getInstance(){
        return instance;
    }
}
