package com.ruiya.javabase.day01;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

/**
 * @author admin
 */
@Data
@JSONType(serializeEnumAsJavaBean = true, serialzeFeatures= SerializerFeature.BeanToArray)
public class Student extends  Person{
    private int score;

    private String clazz;

}
