package com.ruiya.DesignPattern;

public class SinglerReponse {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("car");
        vehicle.run("摩托车");
        vehicle.run("飞机");

    }
}

// 违反单一职责
class Vehicle{
    public void  run(String vehicle){
        System.out.println(vehicle +"在路上，，" );
    }
    // 按照方式不同写多个类

}