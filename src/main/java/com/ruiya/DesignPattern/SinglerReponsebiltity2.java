package com.ruiya.DesignPattern;

public class SinglerReponsebiltity2 {
    public static void main(String[] args) {
        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("小车");
        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("船");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");
    }
}
// 遵守单一职责原则
// 但是修改点大，
// 改进， 直接修改Vehicle，
class RoadVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路");
    }
}
class WaterVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle + "在水里游");
    }
}

class AirVehicle{
    public void run(String vehicle) {
        System.out.println(vehicle + "天上飞");
    }
}