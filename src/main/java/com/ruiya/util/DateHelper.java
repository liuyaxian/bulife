package com.ruiya.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateHelper
{
    private static Logger logger = Logger.getLogger(DateHelper.class);

    public static final String YMDHMS = "yyyyMMddHHmmss";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String HHMMSS = "HHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDD_CHN = "yyyy年MM月dd日";

    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    public static final String YMDHMS_CHN = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String YMDHMS_CHN_12_ZERO = "yyyy年MM月dd日 上午00时mm分ss秒";

    public static final String YMDHMS_CHN_12_FORENOON = "yyyy年MM月dd日 上午hh时mm分ss秒";

    public static final String YMDHMS_CHN_12_AFTERNOON = "yyyy年MM月dd日 下午hh时mm分ss秒";

    public static final String MDHM_CHN = "MM月dd日 HH:mm";
    public static final String MDHM_NOSPACE_CHN = "MM月dd日HH:mm";

    public static final String MMDD_CHN = "MM月dd日";
    /** 短信模板日期格式 2021/5/28 10:46*/
    public static final String YMDHM = "yyyy/MM/dd HH:mm";
    /** 短信模板日期格式 2021/5/28*/
    public static final String YMD = "yyyy/MM/dd";

    /** 英文日期格式  May 19 2021 */
    public static final String MMMDYYYY = "MMM d yyyy";


    /**
     * 把Date转换为缺省的日期格式字串，缺省的字转换格式为yyyy-MM-dd HH:mm:ss 如：2004-10-10 20:12:10
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 描述:得到当前日期,格式yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return formatDate(new Date(), "yyyyMMdd");
    }

    public static String getCurrentTime() {
        return formatDate(new Date(), HHMMSS);
    }

    /**
     * 把Date转换为缺省的日期格式字串，缺省的字转换格式为yyyy-MM-dd HH:mm:ss 如：2004-10-10 20:12:10
     *
     * @param dateStr1
     * @param pattern
     * @param dePattern
     * @return
     */
    public static String formatDate(String dateStr1, String pattern, String dePattern) {
        Date date = parseString(dateStr1, pattern);
        return formatDate(date, dePattern);
    }

    /**
     * 根据特定的Pattern,把Date转换为相应的日期格式字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 以缺省的yyyy-MM-dd HH:mm:ss格式转换字符串为Date，若转换成功，则返回相应的Date对象 若转换失败，则返回null。
     *
     * @param dateStr
     * @return
     */
    public static Date parseString(String dateStr) {
        return parseString(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 以特定的格式转换字符串为Date，若转换成功，则返回相应的Date对象 若转换失败，则返回null。
     *
     * @param dateStr
     * @return
     */
    public static Date parseString(String dateStr, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            return dateFormat.parse(dateStr);
        } catch(ParseException ex) {
            logger.error("以特定的格式转换字符串为Date，转换失败,返回null", ex);
        }
        return null;
    }

    /**
     * 获得特定日期的星期字串
     *
     * @param date
     * @return
     */
    public static String getWeekStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        week = week - 1;
        String weekStr = "";
        switch (week) {
            case 0:
                weekStr = "星期日";
                break;
            case 1:
                weekStr = "星期一";
                break;
            case 2:
                weekStr = "星期二";
                break;
            case 3:
                weekStr = "星期三";
                break;
            case 4:
                weekStr = "星期四";
                break;
            case 5:
                weekStr = "星期五";
                break;
            case 6:
                weekStr = "星期六";
        }
        return weekStr;
    }

    /**
     * 取两个日期对象毫秒差数,用时间1-时间2
     *
     * @param date1 日期对象
     * @param date2 日期对象
     * @return 毫秒差数
     */
    public static long getDateMiliDispersion(Date date1, Date date2) {
        /* 如果其中一为空,则返0 */
        if (date1 == null || date2 == null) {
            return 0;
        }

        /* 提取毫秒 */
        long time1 = date1.getTime();
        long time2 = date2.getTime();

        return time1 - time2;
    }

    /**
     * 比较两个时间的差，返回天数，用时间1-时间2
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 两个时间相差的天数
     */
    public static int getDateDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long diff = time1 - time2;

        Long longValue = diff / (60 * 1000 * 60 * 24);
        return longValue.intValue();
    }

    /**
     * 提取当前时间的星期数，7是星期天，1是星期一，如此类推
     *
     * @return 序数
     */
    public static int getCurrentWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        week = week - 1;
        if (week == 0) {
            week = 7;
        }
        return week;
    }

    /**
     * 提取当前的星期数的中文字串，星期一...星期日
     *
     * @return
     */
    public static String getCurrentWeekStr() {
        return getWeekStr(new Date());
    }

    /**
     * 提取当前时间的年份
     *
     * @return 年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 提取当前时间的月份,1:一月份 12：12月份
     *
     * @return 月份
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 提取当前时间的日期
     *
     * @return 日期
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 将格式为2006-12-29 16:30的字符串转换成 20061229163000 字符格式
     *
     * @param dateTime
     * @return
     */
    public static String formatDateTime(String dateTime) {
        if (dateTime != null && dateTime.length() >= 8) {
            String formatDateTime = dateTime.replaceAll("-", "");
            formatDateTime = formatDateTime.replaceAll(":", "");
            String date = formatDateTime.substring(0, 8);
            String time = formatDateTime.substring(8).trim();// 从第8位开始截取，直到遇到空格为止
            time = StringUtils.rightPad(time, 6, "0");
            // for (int i = time.length(); i < 6; i++)// 后6位不足的补充0
            // {
            // time += "0";
            // }
            return date + time;
        } else {
            return "";
        }
    }

    /**
     * 将格式为2006-12-29 16:30的日期转换成 20061229163000字符格式
     *
     * @param date
     * @return
     */
    public static String formatDateTime(Date date) {
        String dateTime = formatDate(date);
        return formatDateTime(dateTime);
    }

    /**
     * 描述:将格式为2007-12-29 16:30格式化日期为20071229
     */
    public static String formatDate(String datetime) {
        if (datetime != null && datetime.length() >= 8) {
            String formatDateTime = datetime.replaceAll("-", "");
            String date = formatDateTime.substring(0, 8);
            return date;
        } else {
            return "";
        }
    }

    /**
     * 将字符串yyyymmdd转换为yyyy-mm-dd
     *
     * @param sDate
     * @return
     */
    public static String formatString(String sDate) {
        if (sDate == null || sDate.length() != 8) {
            return sDate;
        }
        return sDate.substring(0, 4) + "-" + sDate.substring(4, 6) + "-" + sDate.substring(6);
    }

    /**
     * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
     *
     * @param date
     * @param field
     * @param amount
     * @return
     * @create: 2012-11-6 下午01:04:59
     * @author: 徐文凡
     * @history:
     */
    public static Date add(Date date, int field, int amount) {
        if (null == date) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }

    /**
     * 得到指定月份的月初日期
     *
     * @param year
     * @param month
     * @param pattern
     * @return
     * @create: 2012-11-7 下午02:19:11
     * @author: 徐文凡
     * @history:
     */
    public static String monthStart(int year, int month, String pattern) {
        String date = year + (month >= 10 ? String.valueOf(month) : ("0" + month)) + "01";
        if ("yyyyMMdd".equalsIgnoreCase(pattern)) {
            return date;
        } else {
            return formatDate(parseString(date, "yyyyMMdd"), pattern);
        }
    }

    /**
     * 得到指定月份的月末日期
     *
     * @param year
     * @param month
     * @param pattern
     * @return
     * @create: 2012-11-7 下午02:23:49
     * @author: 徐文凡
     * @history:
     */
    public static String monthEnd(int year, int month, String pattern) {
        String endDay = "";
        boolean leap = false;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            endDay = "31";
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            endDay = "30";
        } else if (month == 2) {
            leap = leapYear(year);
            if (leap) {
                endDay = "29";
            } else {
                endDay = "28";
            }
        }
        String date = year + (month >= 10 ? String.valueOf(month) : ("0" + month)) + endDay;
        if ("yyyyMMdd".equalsIgnoreCase(pattern)) {
            return date;
        } else {
            return formatDate(parseString(date, "yyyyMMdd"), pattern);
        }
    }

    /**
     * 得到当前月份月初
     *
     * @param pattern
     * @return
     * @create: 2012-11-7 上午11:36:23
     * @author: 徐文凡
     * @history:
     */
    public static String thisMonthStart(String pattern) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return monthStart(year, month, pattern);
    }

    /**
     * 得到当前月份月末
     *
     * @param pattern
     * @return
     * @create: 2012-11-7 上午11:42:08
     * @author: 徐文凡
     * @history:
     */
    public static String thisMonthEnd(String pattern) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return monthEnd(year, month, pattern);
    }

    /**
     * 功能：判断输入年份是否为闰年<br>
     *
     * @param year
     * @return 是：true 否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    leap = true;
                } else {
                    leap = false;
                }
            } else {
                leap = true;
            }
        } else {
            leap = false;
        }
        return leap;
    }

    /**
     * 转换python的时间
     *
     * @param time    时间戳(秒)
     * @param pattern
     * @return
     * @create: 2012-7-2 上午10:52:16
     * @author: 徐文凡
     * @history:
     */
    public static String parsePythonTime(long time, String pattern) {
        Date date = new Date(time * 1000);
        return formatDate(date, pattern);
    }

    /**
     * 是否是正确的时间格式
     *
     * @param dateStr
     * @param pattern
     * @return
     * @create: 2013-10-25 下午04:03:29
     * @author: 徐文凡
     * @history:
     */
    public static boolean isCorrectFormat(String dateStr, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            dateFormat.parse(dateStr);
        } catch(ParseException ex) {
            logger.warn("ParseException error", ex);
            return false;
        }
        return true;
    }

    public static String formatTimestamp(Timestamp ts) {
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tsStr = sdf.format(ts);
        } catch(Exception e) {
            logger.error("formatTimestamp error!", e);
        }
        return tsStr;
    }

    /**
     * date1 >=date2:?true:false
     *
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     * @create: 2014年3月18日 下午3:40:44
     * @author: Yang Xiaodong
     * @history:
     */
    public static boolean isGE(String dateStr1, String dateStr2, String pattern) {
        Date d1 = parseString(dateStr1, pattern);
        Date d2 = parseString(dateStr2, pattern);
        if (getDateDiff(d1, d2) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * date1 >date2:?true:false
     *
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     * @create: 2014年3月18日 下午3:40:44
     * @author: Yang Xiaodong
     * @history:
     */
    public static boolean isGT(String dateStr1, String dateStr2, String pattern) {
        Date d1 = parseString(dateStr1, pattern);
        Date d2 = parseString(dateStr2, pattern);
        if (getDateDiff(d1, d2) > 0) {
            return true;
        }
        return false;
    }

    public static String getMipDate(int mipDate, String mipCycle) {
        Calendar current = Calendar.getInstance();
        if ("2W".equals(mipCycle) || "WW".equals(mipCycle)) {
            int currentDayOfWeek = current.get(Calendar.DAY_OF_WEEK);
            mipDate = mipDate + 1;
            current.set(Calendar.DAY_OF_WEEK, mipDate);
            if (currentDayOfWeek == 7 || currentDayOfWeek == 0 || mipDate <= currentDayOfWeek) {
                current.add(Calendar.DAY_OF_YEAR, +7);
            }
        } else {
            int currentDayOfMonth = current.get(Calendar.DAY_OF_MONTH);
            current.set(Calendar.DAY_OF_MONTH, mipDate);
            if (mipDate <= currentDayOfMonth) {
                current.add(Calendar.MONTH, +1);
            }
        }
        return formatDate(current.getTime(), "yyyyMMdd");
    }

    public static String dateZhDisplay(Date d) {
        DateFormat longDateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        return longDateFormat.format(d);
    }

    /**
     * 获取当前时间的24小时制中文表示
     *
     * @return 2017年11月28日 上午8:41:38
     * Luojq
     */
    public static String currentDateZh24Display() {
        return formatDate(new Date(), DateHelper.YMDHMS_CHN);
    }

    /**
     * 获取时间的24小时制中文表示
     *
     * @return 2017年11月28日 上午8:41:38
     * Luojq
     */
    public static String currentDateZh24Display(Date date) {
        return formatDate(date, DateHelper.YMDHMS_CHN);
    }

    /**
     * 获取12小时制中文时间
     *
     * @param calendars
     * @return
     * @author 王彦锋
     * @date 2018/1/17 19:11
     */
    public static String currentDateZh12Display(Calendar... calendars) {
        Calendar calendar = null;
        if (calendars == null || calendars.length == 0) {
            calendar = Calendar.getInstance();
            Date now = new Date();
            calendar.setTime(now);
        } else {
            calendar = calendars[0];
        }

        int currHour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println(currHour);
        if (currHour == 0) {
            return formatDate(calendar.getTime(), DateHelper.YMDHMS_CHN_12_ZERO);
        } else if (currHour > 0 && currHour <= 11) {
            return formatDate(calendar.getTime(), DateHelper.YMDHMS_CHN_12_FORENOON);
        } else {
            return formatDate(calendar.getTime(), DateHelper.YMDHMS_CHN_12_AFTERNOON);
        }

    }

    /**
     * 根据选择间隔月份和当前系统时间获取具体年月日
     *
     * @param validperiod
     * @return
     * @create: 2014年11月18日 下午3:00:39
     * @author: Yang Xiaodong
     * @history:
     */
    public static String getValidperiod(int validperiod) {
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, +30 * validperiod);
        return formatDate(current.getTime(), "yyyyMMdd");
    }

    /**
     * 根据选择间隔月份和当前系统时间获取具体年月日
     *
     * @param timestamp
     * @return
     * @create: 2014年11月18日 下午3:00:39
     * @author: Yang Xiaodong
     * @history:
     */
    public static String formatTimestamp2String(Double timestamp) {
        Calendar current = Calendar.getInstance();
        current.setTimeInMillis(timestamp.longValue() * 1000);
        return formatDate(current.getTime(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据选择间隔月份和当前系统时间获取具体年月日
     *
     * @param timestamp
     * @param pattern
     * @return
     * @create: 2014年11月18日 下午3:00:39
     * @author: Yang Xiaodong
     * @history:
     */
    public static String formatTimestamp2String(long timestamp, String pattern) {
        Calendar current = Calendar.getInstance();
        current.setTimeInMillis(timestamp);
        return formatDate(current.getTime(), pattern);
    }

    public static boolean isSameTime(long srcTime, long descTime, String pattern) {

        return formatTimestamp2String(srcTime, pattern).equals(formatTimestamp2String(descTime, pattern));
    }

    public static String formatMessageDate(Date date) {
        Calendar current = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (isSameDayOfMillis(current, calendar)) {
            return "今天 " + formatDate(calendar.getTime(), "HH:mm");
        } else {
            return formatDate(calendar.getTime(), YYYY_MM_DD);
        }
    }

    public static boolean isSameDayOfMillis(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    public static long getTimeParseString(String timeStr, String pattern) {
        Date date = parseString(timeStr, pattern);
        return date.getTime();
    }

    public static String getDecimalFormat(Object object) {
        return new DecimalFormat("###,###.00").format(object);
    }

    public static String currentDateMdhmZh24Display() {
        return formatDate(new Date(), DateHelper.MDHM_CHN);
    }

    public static String currentDateMdhmZh24NoSpaceDisplay() {
        return formatDate(new Date(), DateHelper.MDHM_NOSPACE_CHN);
    }
    /**
     * 比较两个时间的差，返回天数，用时间1-时间2
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return 两个时间相差的天数
     */
    public static long getDateMiliDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }

        long time1 = date1.getTime();
        long time2 = date2.getTime();

        long diff = time1 - time2;
        return  diff;
    }

    /**
     * date1 >date2:?true:false
     * 年月日时分秒
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public static boolean isGDateTime(String dateStr1, String dateStr2, String pattern) {
        Date d1 = parseString(dateStr1, pattern);
        Date d2 = parseString(dateStr2, pattern);
        if (getDateMiliDiff(d1, d2) > 0) {
            return true;
        }
        return false;
    }
    /**
     * date1 >= date2:?true:false
     * 年月日时分秒
     * @param dateStr1
     * @param dateStr2
     * @param pattern
     * @return
     */
    public static boolean isGEDateTime(String dateStr1, String dateStr2, String pattern) {
        Date d1 = parseString(dateStr1, pattern);
        Date d2 = parseString(dateStr2, pattern);
        if (getDateMiliDiff(d1, d2) >= 0) {
            return true;
        }
        return false;
    }


    /**
     * 日期添加或减去指定的时间量。
     * @param dateStr
     * @param pattern
     * @param field
     * @param amount
     * @return
     */
    public static String add(String dateStr,  String pattern, int field, int amount) {
        if (StringUtils.isBlank(dateStr)){
            return "";
        }
        return formatDate(add(parseString(dateStr, pattern), field, amount), pattern);
    }

    /**获取当前整点的时间*/
    public static Date getCurrentDateInHour(int hourOfDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        return DateUtils.truncate(calendar, Calendar.HOUR_OF_DAY).getTime();
    }


    /**获取当前年 */
    public static int getCurrentDateYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
    /**获取当前月 */
    public static int getCurrentDateMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH)+1;
    }
    /**获取当前日期 */
    public static int getCurrentDateDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    /**
     * 根据特定的Pattern,把Date转换为相应的日期格式字符串
     *
     * @param date
     * @return
     */
    public static String formatEnglishDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        return dateFormat.format(date);
    }
    public static void main(String[] args) throws ParseException {

        findDates("2018-10-01","2018-10-25");
        getBetweenDate("2018-10-01","2018-10-25").stream().forEach(s -> System.out.println("1:"+s));
    }

    public static List<String> findDates(String dBegin, String dEnd) throws ParseException {
        //日期工具类准备
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //设置开始时间
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(dBegin));

        //设置结束时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(format.parse(dEnd));

        //装返回的日期集合容器
        List<String> Datelist = new ArrayList<String>();
        Datelist.add(format.format(calBegin.getTime()));
        // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
        while (format.parse(dEnd).after(calBegin.getTime()))  {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Datelist.add(format.format(calBegin.getTime()));
        }

        //打印结果 [2018-10-01, 2018-10-02, 2018-10-03, 2018-10-04, 2018-10-05, 2018-10-06, 2018-10-07, 2018-10-08, 2018-10-09, 2018-10-10, 2018-10-11, 2018-10-12, 2018-10-13, 2018-10-14, 2018-10-15, 2018-10-16, 2018-10-17, 2018-10-18, 2018-10-19, 2018-10-20, 2018-10-21, 2018-10-22, 2018-10-23, 2018-10-24, 2018-10-25]
        System.out.println(Datelist);
        return Datelist;
    }

    public static List<String> getBetweenDate(String begin,String end){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> betweenList = new ArrayList<String>();

        try{
            Calendar startDay = Calendar.getInstance();
            startDay.setTime(format.parse(begin));
            startDay.add(Calendar.DATE, -1);
            while(true){
                startDay.add(Calendar.DATE, 1);
                Date newDate = startDay.getTime();
                String newend=format.format(newDate);
                betweenList.add(newend);
                if(end.equals(newend)){
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        return betweenList;
    }
}

