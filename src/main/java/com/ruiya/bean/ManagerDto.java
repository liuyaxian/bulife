package com.ruiya.bean;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.List;

@Data
@JSONType(serialzeFeatures = SerializerFeature.WriteNullListAsEmpty)
public class ManagerDto {
    private List<ManagerInfoDto> current_manager;
}
