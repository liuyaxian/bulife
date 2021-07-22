package com.ruiya.bean;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "product_list_v2")
@JSONType(serialzeFeatures = SerializerFeature.WriteNullStringAsEmpty)
public class ProductlistV2Dto {
    private ManagerDto manager;
    private ProductBaseInfoDto base_info;
}