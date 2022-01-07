package com.ruiya.DesignPattern.inversion;

/**
 * @desc: 依赖倒置原则
 * @author: admin
 * @since: 2022/1/5 16:43
 * @history:
 */
public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Email{
     public String getInfo(){
         return "nihao 电子邮件！";
     }
}

/****
 * 完成Person 接受消息的功能
 * 方式1 分析
 * 1、扩展性差
 * 解决思路： 引入一个抽象的接口， B表示接受者。 Person 依赖接口
 */
class Person{

    public void receive(Email email){
        System.out.println(email.getInfo());
    }
}
