package com.ruiya.javase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class MathUtils {
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
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return CN_ZEOR_FULL;
        }
        // 这里会进行金额的四舍五入
        long number = numberOfMoney.movePointRight(MONEY_PRECISION).setScale(0, 4).abs().longValue();
        // 得到小数点后两位值
        long scale = number % 100;
        int numUnit = 0;
        int numIndex = 0;
        boolean getZero = false;
        // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
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

    public static String convertNumberToMoney(BigDecimal numberOfMoney) {
        if (numberOfMoney.compareTo(BigDecimal.ZERO) == 0 ){
            return CN_ZEOR_FULL;
        }
        StringBuffer retval = new StringBuffer();
        String numberStr = numberOfMoney.multiply(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP).abs().toPlainString();
        char[] numberCharArr = numberStr.toCharArray();
        if (numberCharArr.length == 0) {
            return CN_ZEOR_FULL;
        }

        for (int i=0; i<numberCharArr.length; i++) {
            int inum = Character.getNumericValue(numberCharArr[i]);
            int unitIndex = numberCharArr.length-1-i;
            // 无小数，追加字符“整”，返回XXX元整
            if (unitIndex == 1 && inum == 0 && Character.getNumericValue(numberCharArr[numberCharArr.length-1]) == 0) {
                retval.append(CN_FULL);
                break;
            }
            // 如果后面数据全部为零，
           char [] numberNewCharArr =  Arrays.copyOfRange(numberCharArr, i+1, numberCharArr.length);
            boolean notZero = false;
            for (int j = 0; j < numberNewCharArr.length; j++) {
                if (numberNewCharArr[j] != '0'){
                    notZero = true;
                    break;
                }
            }
            if(!notZero){
                retval.append(CN_UPPER_MONETRAY_UNIT[unitIndex]);
            } else {
                // 数字+单位
                retval.append(CN_UPPER_NUMBER[inum]).append(CN_UPPER_MONETRAY_UNIT[unitIndex]);
            }
        }
        if (numberOfMoney.doubleValue() < 0) {
            retval.insert(0, CN_NEGATIVE);
        }
        return retval.toString();
    }

    public static char []  numberToCharArray(BigDecimal numberOfMoney) {
        int signum = numberOfMoney.signum();
        // 零元整的情况
        if (signum == 0) {
            return new char[] {'0'};
        }
        // 这里会进行金额的四舍五入
        String num  = String.valueOf(numberOfMoney.setScale(2, RoundingMode.HALF_UP));
        return  num.toCharArray();
    }

    public static void main(String[] args) {

        for (int i =0; i < 200; ){
            double num = 1.23098 * Math.pow(i*10,2);
            i = i +10;
            BigDecimal numberOfMoney = BigDecimal.valueOf(num);
            String s = number2CNMontrayUnit(numberOfMoney);
            System.out.println("小写：" + numberToCharArray(numberOfMoney));
            System.out.println("number2CNMontrayUnit你输入的金额为：【" + numberOfMoney + "】   #--# [" + s.toString() + "]");
            System.out.println(" convertNumberToMoney: 你输入的金额为：【" + numberOfMoney + "】   #--# [" + convertNumberToMoney(numberOfMoney) + "]");
        }

        for (int i =0; i < 200; ){
            double num = 1.000 * Math.pow(i,10);
            i = i +10;
            BigDecimal numberOfMoney = BigDecimal.valueOf(num);
            String s = number2CNMontrayUnit(numberOfMoney);
            System.out.println("小写：" + numberToCharArray(numberOfMoney));
            System.out.println("number2CNMontrayUnit你输入的金额为：【" + numberOfMoney + "】   #--# [" + s.toString() + "]");
            System.out.println(" convertNumberToMoney: 你输入的金额为：【" + numberOfMoney + "】   #--# [" + convertNumberToMoney(numberOfMoney) + "]");
        }
        for (int i =0; i < 2; i++){
            double num = 1.000 * i;
            BigDecimal numberOfMoney = BigDecimal.valueOf(num);
            String s = number2CNMontrayUnit(numberOfMoney);
            System.out.println("小写：" + numberToCharArray(numberOfMoney));
            System.out.println("number2CNMontrayUnit你输入的金额为：【" + numberOfMoney + "】   #--# [" + s.toString() + "]");
            System.out.println(" convertNumberToMoney: 你输入的金额为：【" + numberOfMoney + "】   #--# [" + convertNumberToMoney(numberOfMoney) + "]");
        }
    }

}
