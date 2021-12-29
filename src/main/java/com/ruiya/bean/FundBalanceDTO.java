package com.ruiya.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/27 16:57
 * @history:
 */
@Data
public class FundBalanceDTO {
    private String   _id;
    private BigDecimal balance = BigDecimal.ZERO;
    private BigDecimal mktvalue = BigDecimal.ZERO;
}