package com.ruiya.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/***
 * 数字转换为汉语中人民币的大写
 */
public class NumberToCN {

    /**
     * 汉语中数字大写
     */
    private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
            "伍", "陆", "柒", "捌", "玖" };
    /**
     * 汉语中货币单位大写，这样的设计类似于占位符
     */
    private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
            "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
            "佰", "仟" };
    /**
     * 特殊字符：整
     */
    private static final String CN_FULL = "整";
    /**
     * 特殊字符：负
     */
    private static final String CN_NEGATIVE = "负";
    /**
     * 金额的精度，默认值为2
     */
    private static final int MONEY_PRECISION = 2;
    /**
     * 特殊字符：零元整
     */
    private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

    /**
     * 把输入的金额转换为汉语中人民币的大写
     *
     * @param numberOfMoney
     *            输入的金额
     * @return 对应的汉语大写
     */
    public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
        StringBuffer sb = new StringBuffer();
        // 正负数函数
        /***
         *    Returns the signum function of this BigDecimal.
         *                 Returns:
         *         -1, 0, or 1 as the value of this BigDecimal is negative, zero, or positive.
         */
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        // 这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0 ,RoundingMode.HALF_UP).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四种情况：00 = 0, 01 = 1, 10, 11
        if (!(scale > 0)) {
            numIndex = 2;
            number = number / 100;
            getZero = true;
        }
        if ((scale > 0) && (!(scale % 10 > 0))) {
            numIndex = 1;
            number = number / 10;
            getZero = true;
        }
        int zeroSize = 0;
        while (true) {
            if (number <= 0) {
                break;
            }
            // 每次获取到最后一个数
            numUnit = (int) (number % 10);
            if (numUnit > 0) {
                if ((numIndex == 9) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
                }
                if ((numIndex == 13) && (zeroSize >= 3)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
                }
                sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                getZero = false;
                zeroSize = 0;
            } else {
                ++zeroSize;
                if (!(getZero)) {
                    sb.insert(0, CN_UPPER_NUMBER[numUnit]);
                }
                if (numIndex == 2) {
                    if (number > 0) {
                        sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                    }
                } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
                    sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
                }
                getZero = true;
            }
            // 让number每次都去掉最后一个数
            number = number / 10;
            ++numIndex;
        }
        // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
        if (signum == -1) {
            sb.insert(0, CN_NEGATIVE);
        }
        // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
        if (!(scale > 0)) {
            sb.append(CN_FULL);
        }
        return sb.toString();
    }


    public static char []  numberToCharArray(BigDecimal numberOfMoney) {
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return new char[] {'0'};
        }
        String num  = String.valueOf(numberOfMoney.setScale(2, RoundingMode.HALF_UP));
        return  num.toCharArray();
    }

    /***
     * 小写数字
     * @param subAmt
     * @param fieldMap
     */
    public static void setSubAmtChar(BigDecimal subAmt, Map<String, String> fieldMap){
        char [] subAmtChar  = numberToCharArray(subAmt);
//        for (int i = subAmtChar.length -1;  i >= 0; i--) {
//            String  keys = "subAmt" + i;
//            fieldMap.put(keys, String.valueOf(subAmtChar[i]));
//        }

        for (int i = 0 ; i< subAmtChar.length ; i++) {
            String  keys = "subAmt" + (subAmtChar.length -i);
            fieldMap.put(keys, String.valueOf(subAmtChar[i]));
        }
    }

    public static void main(String[] args) {
        BigDecimal money = BigDecimal.valueOf(100.09);
        System.out.println(number2CNMontrayUnit(money));
//
//        BigDecimal numberOfMoney = new BigDecimal(100.00);
//        Map<String, String> fieldMap  = new HashMap<>();
//        setSubAmtChar(numberOfMoney,  fieldMap);
//        fieldMap.forEach((key ,value) -> {
//            System.out.println(key + ":" + value);
//        });

        // 传统的Map迭代方式
//        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
//            System.out.println(entry.getKey() + "：" + entry.getValue());
//        }

//        String s = NumberToCN.number2CNMontrayUnit(numberOfMoney);
//        System.out.println("你输入的金额为：【" + money + "】   大写：[" + s.toString()
//                + "]");
//        System.out.println("sss");
//        char []  c = numberToCharArray(numberOfMoney);
//        for (char c1 : c) {
//            System.out.println("c1  :" + c1);
//        }
    }
}

