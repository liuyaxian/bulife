package com.yaruida.company;


import lombok.Data;

@Data
public class Company {
    /** 电话 */
    private String mobileno;
    /** 邮箱 */
    private String email;

    /** 公司名称 */
    private String company;

    /** 经营人 */
    private String manager;

    /** 企业类型 */
    private String businessType;

    /** 成立日期 */
    private String registerDate;

    /** 地址 */
    private String address;

    /** 存续状态 */
    private String survivalStatus;

    /** 分值 */
    private int score;

    public String [] rowHead = {"序号","公司名称","经营人", "企业类型","成立日期","地址","存续状态","分值"};

    public Object[] csvLine() {

        return new Object[]{
                "",
                getCompany(),
                getManager(),
                getBusinessType(),
                getRegisterDate(),
                getAddress(),
                getSurvivalStatus(),
                getScore()};
    }

}
