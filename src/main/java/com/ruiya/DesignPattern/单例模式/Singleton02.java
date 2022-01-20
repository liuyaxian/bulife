package com.ruiya.DesignPattern.单例模式;

/**
 * @desc: 饿汉式（静态代码块）
 * @author: admin
 * @since: 2022/1/19 10:49
 * @history:
 */
public class Singleton02 {
    /**
     * 1、构造器私有化（防止new）
     * 2、类的内部创建对象
     * 3、向外暴露一个静态的公共方法，getInstance
     * @param args
     */
    public static void main(String[] args) {
        Singleton02 singleton01 = Singleton02.getInstance();
        Singleton02 singleton011 = Singleton02.getInstance();
        System.out.println(singleton01 == singleton011); //true
        System.out.println(singleton01.hashCode()); // 99747242
        System.out.println(singleton011.hashCode());  // 99747242
    }

    private static Singleton02 instance = null;

    static {
        instance = new Singleton02();
    }
    private Singleton02(){

    }
    public static Singleton02 getInstance(){
        return instance;
    }
}
