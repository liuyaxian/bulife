package com.ruiya.bean;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@JSONType(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
public class ManagerInfoDto {
    private long indi_id;
    private String indi_name;
}
