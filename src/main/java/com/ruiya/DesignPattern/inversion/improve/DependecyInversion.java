package com.ruiya.DesignPattern.inversion.improve;

/**
 * @desc:
 * @author: admin
 * @since: 2022/1/5 17:11
 * @history:
 */

public class DependecyInversion {

    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Weixin());
        person.receive(new Email());

    }
}

// 定义一个接口
interface  IRecevier{
    public String getInfo();
}

class Email implements IRecevier{
    @Override
    public String getInfo(){
        return "nihao 电子邮件！";
    }
}

class Weixin implements IRecevier{
    @Override
    public String getInfo(){
        return "Weixin Weixin！";
    }
}
class Person{
    public void receive(IRecevier iRecevier){
        System.out.println(iRecevier.getInfo());
    }
}

