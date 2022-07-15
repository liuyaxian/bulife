package com.yaruida.energy;

import lombok.Data;

import java.io.Serializable;

/**
 * 能量表
 */
@Data
public class EnergyInfo implements Serializable {

    /**食物名称 */
    private String foodName;
    /**热量（大卡） */
    private float foodenergy;
    /**碳水化合物(克) */
    private float carbohydrate;
    /**脂肪(克 */
    private float fat;
    /**蛋白质(克) */
    private float protein;
    /** 纤维素(克) */
    private float cellulose;

}
