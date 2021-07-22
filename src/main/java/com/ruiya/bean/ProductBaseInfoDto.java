package com.ruiya.bean;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@JSONType(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
public class ProductBaseInfoDto {
    @Field("fundname")
    private String fundname;
}
