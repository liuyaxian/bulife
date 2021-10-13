package com.ruiya.javase.class1;

public class Outer {
    private String name = "11";
    private int age = 23;

    // 静态内部类，和外部类相同
    static  class Inner{
        private String address = "address";
        private String phone = "12455555";

        public void  show(){
            // 外部类的方法
            // 1、 创建再访问
            Outer outer = new Outer();
            System.out.println("outer。 = " + outer.age);
            //访问静态内部类的方法，
            System.out.println("outer = " + address);
        }
    }

    public static void main(String[] args) {
        // 直接创建静态内部类的对象
        Outer.Inner inner = new Outer.Inner();
        // 调用方法
        inner.show();
        System.loadLibrary("Outer.java");//不写文件的后缀，程序会自动加上.dll的。
    }

}
