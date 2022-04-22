package com.yaruida.utils;

import com.alibaba.csp.sentinel.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;

public class BankCardUtils {

    public static void main(String[] args) {
//        System.out.printf("银行卡号: " + handleBankCardNum("3333332323222323"));

        System.out.println("4051 ::   " + getAppVersion("4051", "1000"));
        System.out.println("411 ::   " + getAppVersion("120121", "1000"));
        System.out.println("100211 ::   " + getAppVersion("100211", "1000"));
        System.out.println("100011 ::   " + getAppVersion("100011", "1000"));
        System.out.println("40101 ::   " + getAppVersion("40101", "1000"));
        System.out.println();
        System.out.println();

        System.out.println("4051 ::   " + getAppVersion("4051"));
        System.out.println("120121 ::   " + getAppVersion("120121"));
        System.out.println("100211 ::   " + getAppVersion("www"));
        System.out.println("100011 ::   " + getAppVersion("100011"));
        System.out.println("40101 ::   " + getAppVersion("40101"));

        }

    /**
     * desc: 处理银行卡号格式
     * @param bankCardNum
     * @return {@link String}
     */
    private static String handleBankCardNum(String bankCardNum) {
        if (StringUtil.isBlank(bankCardNum)) {
            return "";
        }
        return handleBankCard(bankCardNum);
    }

    private static String handleBankCard(String bankCardNum) {
        String str = "";
        if (StringUtil.isBlank(bankCardNum)) {
            return str;
        }
        if (bankCardNum.length() >= 4) {
            str = bankCardNum.substring(0, 4) + " ";
            String substring = bankCardNum.substring(4);
            str += handleBankCard(substring);
        } else {
            str += bankCardNum;
        }
        return str;
    }



    private static String getAppVersion(String appversionCode, String mctcode) {
        if (StringUtils.isNotBlank(appversionCode) && "1000".equals(mctcode)) {
            //左起第一位*1000 + 第二位*10+第三位*1
            int appversion = NumberUtils.toInt(appversionCode);
            int first =  appversion / 1000;
            int second =  (appversion % 1000)/10;
            int third  =  (appversion % 1000) %10;
            return first+"."+ second+ '.'+ third;
        }
        return appversionCode;
    }

    private static String getAppVersion(String appversionCode){
        int appversion = NumberUtils.toInt(appversionCode);
        int first =  appversion / 1000;
        int appVersionSecond  = (appversion - (first * 1000));
        int second =  appVersionSecond / 10;
        int third  = (appVersionSecond - (second * 10));
        return first+"."+ second+ '.'+ third;
    }
}
