package com.ruiya.javabase.day01;


import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author admin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JSONType(serialzeFeatures= SerializerFeature.WriteNullListAsEmpty)
public class Person {
    private String name;
    private String gender;
    private  int age;

    private void setName(String name){
        this.name = name;
    }
    private void eat(){
        System.out.println("person eat!!");
    }
}
