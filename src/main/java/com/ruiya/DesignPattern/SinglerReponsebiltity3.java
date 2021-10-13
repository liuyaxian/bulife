package com.ruiya.DesignPattern;

public class SinglerReponsebiltity3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("轮船");
        vehicle2.runWater("飞机");

    }
}

/***
 * 方式三
 * 1、修改方法没有对原来的类做大的修改，
 *  2、 虽然没有类中实现单一职责， 方法中遵守了单一职责原则
 *
 */
class Vehicle2{
    public void  run(String vehicle){
        System.out.println(vehicle +"在路上，，" );
    }
    public void  runAir(String vehicle){
        System.out.println(vehicle +"天空，，" );
    }
    public void  runWater(String vehicle){
        System.out.println(vehicle +"水中，，" );
    }
}



