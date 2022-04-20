package com.ruiya.taobao;

import lombok.Data;

import java.io.Serializable;

@Data
public class User  implements Serializable {
    private  String name;
    private  Integer age;
    private  String gender;
}