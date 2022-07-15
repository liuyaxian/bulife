package com.ruiya.stream;


import lombok.Data;

@Data
public class Point {

    String x, y, z;
    Point(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
