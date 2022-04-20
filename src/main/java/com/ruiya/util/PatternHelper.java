package com.ruiya.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @author: admin
 * @since: 2021/12/24 11:09
 * @history:
 */
public class PatternHelper  {
    private static final Logger logger = Logger.getLogger(PatternHelper.class);

    String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";// 精确验证手机号码

    private static Pattern ID_CARD_18 = Pattern.compile("\\d{17}(\\d|X)");

    private static Pattern ID_CARD_15 = Pattern.compile("\\d{15}");

    /**
     * 验证邮箱
     *
     * @return 如果是符合的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isEmail(String str) {
        String regex = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return match(regex, str);
    }

    /**
     * 验证IP地址
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, str);
    }

    /**
     * 验证网址Url
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    /**
     * 验证电话号码
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsTelephone(String str) {

        String regex = "^(\\d{3,4}-)?\\d{6,8}$";
        // String regex = "^\\d{1,18}$";
        return match(regex, str);
    }

    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198</p>
     * <p>联通：130、131、132、145、155、156、175、176、185、186、166</p>
     * <p>电信：133、153、173、177、180、181、189、199</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 验证手机号（精确）
     * @param str 待验证文本
     * @return {@code true}: 匹配<br>{@code false}: 不匹配
     */
    public static boolean isMobileNo(String str) {
        return StringUtils.isNotBlank(str) && Pattern.matches(REGEX_MOBILE_EXACT, str);
    }

    /**
     * 验证输入密码条件(字符与数据同时出现)
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPassword(String str) {
        String regex = "[A-Za-z]+[0-9]";
        return match(regex, str);
    }

    /**
     * 验证输入密码长度 (6-18位)
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPasswLength(String str) {
        String regex = "^[A-Za-z0-9]{6,18}$";
        return match(regex, str);
    }

    /**
     * 验证输入邮政编号
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsPostalcode(String str) {
        String regex = "^\\d{6}$";
        return match(regex, str);
    }

    /**
     * 验证输入
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsHandset(String str) {
        // String regex = "^[0,1]+[3,5]+\\d{9}$";
        // String regex = "^\\d{1,18}$";
        String regex = "^1\\d{10}$";

        return match(regex, str);
    }


    /**
     * 验证输入一社会信用代码
     * @param str
     * @return
     */
    public static boolean IsUnifedSocialCard(String str) {
        String regex = "^[A-Za-z0-9]{15,30}$";
        return match(regex, str);
    }

    /**
     * 验证输入身份证号
     *
     * @param id 待验证的字符串
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsIDcard(String id) {
        // String regex = "(^\\d{18}$)|(^\\d{15}$)";
        // String regex =
        // "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
        // return match(regex, str);
        return id != null && ((id.length() == 18 && check18(id)) || (id.length() == 15 && check15(id)));
    }

    public static boolean check15(String id) {
        String id15 = id;
        Matcher matcher = ID_CARD_15.matcher(id15);
        if (!matcher.matches()) {
            return false;
        }
        // 检查生日
        String birthday = "19" + id15.substring(6, 12);
        // 检查年份月份日期合法性
        if (!isValidDate(birthday)) {
            return false;
        }
        // 生日不得大于当期日期
        long currentDate = Long.parseLong(DateHelper.getCurrentDate());
        long birthdayLong = Long.parseLong(birthday);
        if (currentDate < birthdayLong) {
            return false;
        }
        // 计算校验位
        // String verifyCode = getVerify(id15.substring(0, 6) + "19" +
        // id15.substring(6));
        // 计算18位身份证
        // String fifteenId = id;
        // String eighteenId = new StringBuffer(id15.substring(0,
        // 6)).append("19")
        // .append(id15.substring(6)).append(verifyCode).toString();
        // 计算性别
        // String sex = eighteenId.substring(16, 17);
        // boolean isMale = "1".equals(sex) || "3".equals(sex) ||
        // "5".equals(sex) || "7".equals(sex)
        // || "9".equals(sex);
        return true;
    }

    public static boolean check18(String id) {
        String id18 = id;
        // 将小写x转换成大写X
        id18 = id18.replace('x', 'X');
        Matcher matcher = ID_CARD_18.matcher(id18);
        if (!matcher.matches()) {
            return false;
        }
        // 检查生日
        String birthday = id18.substring(6, 14);
        // 检查年份月份日期合法性
        if (!isValidDate(birthday)) {
            return false;
        }
        // 生日不得大于当期日期
        long currentDate = Long.parseLong(DateHelper.getCurrentDate());
        long birthdayLong = Long.parseLong(birthday);
        if (currentDate < birthdayLong) {
            return false;
        }
        // 检查校验位
        String verifyCode = id18.substring(17);
        if (verifyCode == null || !verifyCode.equals(getVerify(id18.substring(0, 17)))) {
            return false;
        }
        // 计算15位身份证
        // String eighteenId = id18;
        // String fifteenId = id18.substring(0, 6) + id18.substring(8, 17);
        // 计算性别
        // String sex = eighteenId.substring(16, 17);
        // boolean isMale = "1".equals(sex) || "3".equals(sex) ||
        // "5".equals(sex) || "7".equals(sex)
        // || "9".equals(sex);
        return true;

    }

    public static String getVerify(String eightcardid) {
        int remaining = 0;
        int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
        int[] vi = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
        int[] ai = new int[18];
        try {
            if (eightcardid.length() == 18) {
                eightcardid = eightcardid.substring(0, 17);
            }
            if (eightcardid.length() == 17) {
                int sum = 0;
                String k;
                for (int i = 0; i < 17; i++) {
                    k = eightcardid.substring(i, i + 1);
                    ai[i] = Integer.parseInt(k);
                }
                for (int i = 0; i < 17; i++) {
                    sum = sum + wi[i] * ai[i];
                }
                remaining = sum % 11;
            }
            return remaining == 2 ? "X" : String.valueOf(vi[remaining]);
        } catch(Exception ex) {
            logger.error("GetVerify:" + eightcardid + " error.", ex);
        }
        return null;
    }

    /**
     * 验证输入身份证号
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsIDcard15(String str) {
        // String regex = "(^\\d{18}$)|(^\\d{15}$)";
        String regex = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((([02468][048])|([13579][26]))0229))|(([0-9][0-9])|([0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))(\\d{3})";
        return match(regex, str);
    }

    /**
     * 验证输入两位小数
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsDecimal(String str) {
        if (StringUtils.isNotBlank(str)) {
            if (str.contains("E") || str.contains("e")) {
                String sciRegex = "^[0-9]+(.[0-9]*)?[Ee]{1}(-?\\d+)$";
                if (match(sciRegex, str)) {
                    // 兼容科学计数法
                    str = new BigDecimal(str).toPlainString();
                }
            }
        }
        String regex = "^[0-9]+(.[0-9]{1,2})?$";
        return match(regex, str);
    }

    /**
     * 验证输入一年的12个月
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsMonth(String str) {
        String regex = "^(0?[[1-9]|1[0-2])$";
        return match(regex, str);
    }

    /**
     * 验证输入一个月的31天
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsDay(String str) {
        String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
        return match(regex, str);
    }

    /**
     * 验证日期时间
     *
     * @return 如果是符合网址格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isDate(String str) {
        String regex = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
        return match(regex, str);
    }

    /**
     * 验证数字输入
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证非零的正整数
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsIntNumber(String str) {
        String regex = "^\\+?[1-9][0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证大写字母
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsUpChar(String str) {
        String regex = "^[A-Z]+$";
        return match(regex, str);
    }

    /**
     * 验证小写字母
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLowChar(String str) {
        String regex = "^[a-z]+$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字母
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLetter(String str) {
        String regex = "^[A-Za-z]+$";
        return match(regex, str);
    }

    /**
     * 验证验证输入汉字
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsChinese(String str) {
        String regex = "^[\u4e00-\u9fa5,·]{0,}$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字符串
     *
     * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsLength(String str) {
        String regex = "^.{8,}$";
        return match(regex, str);
    }

    /**
     * @param regex
     *            正则表达式字符串
     * @param str
     *            要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 检查字符串是否含有HTML标签
     * @return
     */
    public static boolean checkHtmlTag(String str) {
        String regex = "^[a-zA-Z0-9],{0,}$";
        return match(regex, str);
    }

    /**
     * 检查输入的数据中是否有特殊字符
     *
     * @param qString
     *            要检查的数据
     * @param regx
     *            特殊字符正则表达式
     * @return boolean 如果包含正则表达式 <code> regx </code> 中定义的特殊字符，返回true； 否则返回false
     */
    public static boolean hasCrossScriptRisk(String qString, String regx) {
        if (qString != null) {
            qString = qString.trim();
            Pattern p = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(qString);
            return m.find();
        }
        return false;
    }

    /**
     * 检查输入的数据中是否有特殊字符
     *
     * @param qString
     *            要检查的数据
     * @return boolean 如果包含正则表达式 <code> regx </code> 中定义的特殊字符，返回true； 否则返回false
     */
    public static boolean checkString(String qString) {
        String regx = "!|！|@|◎|#|＃|(\\$)|￥|%|％|(\\^)|……|(\\&)|※|(\\*)|×|(\\()|（|(\\))|）|_|——|(\\+)|＋|(\\|)|§ ";
        return hasCrossScriptRisk(qString, regx);
    }

    /**
     * 校验密码
     * @param password
     * @return
     * @create: 2014年5月28日 下午4:21:31
     * @author: Yang Xiaodong
     * @history:
     */
    public static String verifyPassword(String password, String idno) {
        if (password.contains(" ")) {
            return "密码不能包含空格";
        }
        if (password.length() < 6) {
            return "密码至少需要6位";
        }
        if (match("^\\d*$", password)) {
            return "密码不能全是数字";
        }
        if (match("^[a-zA-Z]*$", password)) {
            return "密码不能全是字母";
        }
        if (idno.equalsIgnoreCase(password)) {
            return "密码不能为身份证号";
        }
        return "";
    }

    /**
     * 校验年月日合法性
     * @param inputDate
     * @return
     * @create: 2014年6月16日 上午10:22:08
     * @author: Yang Xiaodong
     * @history:
     */
    public static boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            if (inputDate.equals(sdf.format(sdf.parse(inputDate)))) {
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            logger.error("para inputDate error:" + inputDate, e);
            return false;
        }
    }

    public static boolean verifyTradePassword(String password) {
        return 6 == StringUtils.length(password) && StringUtils.isNumeric(password);
    }
}

