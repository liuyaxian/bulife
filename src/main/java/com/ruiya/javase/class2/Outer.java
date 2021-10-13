package com.ruiya.javase.class2;

//外部类
public class Outer {
    private  String name ="namename";
    private String sex ="男";

    private void show(){
        // 局部变量
        // 不能添加任何修饰符  不可以修改， 常量
         final  String address = "深圳";
         class Inner{
             // 局部内部类的属性
            private String phone = "3234234";
            private String emial = "112@126.com";
            public void show2(){
                // 访问外部类的属性
                System.out.println("name = " + Outer.this.name);
                System.out.println("sex = " + Outer.this.sex);
                // 访问内部类的属性
                System.out.println("phone = " + this.phone);
                System.out.println("emial = " + this.emial);
                // 访问局部内部变量 jdk 1.7 要求必须是常量 final，  jdk 1.8 之后 不需要系统自动添加final
                System.out.println("address = " + address);
            }
        }
        // 创建局部内部类对象
        Inner inner = new Inner();
        inner.show2();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.show();

    }
}
