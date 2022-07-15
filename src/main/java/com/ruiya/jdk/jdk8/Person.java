package com.ruiya.jdk.jdk8;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/9 11:03
 * @history:
 */
@Data
@NoArgsConstructor
public class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区
    // 构造方法
    public Person(String name, int salary, int age,String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.area = area;
        this.age = age;

    }

    public Person(String name, int salary, String sex,String area) {
        this.name = name;
        this.salary = salary;
        this.sex = sex;
        this.area = area;

    }
}
