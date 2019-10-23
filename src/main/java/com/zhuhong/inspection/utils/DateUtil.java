package com.zhuhong.inspection.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * 时间日期处理类
 * @Author: jian.ye
 * @Date: 2019/10/8 15:17
 */
@Slf4j
public class DateUtil {

    public static final String DATE_FORMATER = "yyyy-MM-dd";
    public static final String DATE_FORMATER_1 = "yyyyMMdd";
    public static final String DATE_TIME_FORMATER = "yyyy-MM-dd HH:mm:ss";
    public static final String SECONDS = "Seconds";
    public static final String MINUTES = "Minutes";
    public static final String HOURS = "Hours";
    public static final String DAYS = "Days";
    public static final String WEEKS = "Weeks";
    public static final String MONTHS = "Months";
    public static final String YEARS = "Years";
    public static final JSONObject TIME_JSON = new JSONObject();

    static {
        TIME_JSON.put(SECONDS, ChronoUnit.SECONDS);
        TIME_JSON.put(MINUTES, ChronoUnit.MINUTES);
        TIME_JSON.put(HOURS, ChronoUnit.HOURS);
        TIME_JSON.put(DAYS, ChronoUnit.DAYS);
        TIME_JSON.put(WEEKS, ChronoUnit.WEEKS);
        TIME_JSON.put(MONTHS, ChronoUnit.MONTHS);
        TIME_JSON.put(YEARS, ChronoUnit.YEARS);
    }

    /**
     * 返回当前日期时间
     * @return Date
     * @Author: jian.ye
     * @Date: 2019/10/8 15:22
     */
    public static Date getCurrentDate() {
        try {
            return new Date();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("返回当前时间，异常信息:", e);
            return null;
        }
    }

    /**
     * 时间类型转字符串
     * @param localDateTime 待转时间
     * @param patter 格式
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/8 15:35
     */
    public static String toDateString(LocalDateTime localDateTime, String patter) {
        try {
            log.debug("指定时间转字符串：{localDateTime=" + localDateTime + ",patter" + patter + "}");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patter);
            String str = formatter.format(localDateTime);
            log.debug("指定时间转字符串，返回的字符串：" + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("指定时间转字符串，异常信息:", e);
            return "";
        }
    }

    /**
     * 时间类型转字符串
     * @param localDate 待转时间
     * @param patter 格式
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/8 15:35
     */
    public static String toDateString(LocalDate localDate, String patter) {
        try {
            log.debug("指定时间转字符串：{localDate=" + localDate + ",patter" + patter + "}");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patter);
            String str = formatter.format(localDate);
            log.debug("指定时间转字符串，返回的字符串：" + str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("指定时间转字符串，异常信息:", e);
            return "";
        }
    }

    /**
     * 给日期字符串增加指定数量的日期类型
     * @param dateString 日期字符串
     * @param format 格式类型
     * @param type 待增加的日期类型
     * @param num 待增加的数量
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/19 12:28
     */
    public static String addDay(int category, String dateString, String format, String type, int num){
        try {
            log.debug("指定时间字符串和加的天数：{dateString=" + dateString + ",category=" + category  + ",format=" + format  + ",type=" + type  + ",num=" + num + "}");
            String dayString = "";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            if (category == 1) {
                LocalDateTime localDate = LocalDateTime.parse(dateString, formatter);
                LocalDateTime date = localDate.plus(num, (TemporalUnit) TIME_JSON.get(type));
                dayString = formatter.format(date);
            } else {
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                LocalDate date = localDate.plus(num, (TemporalUnit) TIME_JSON.get(type));
                dayString = formatter.format(date);
            }

            log.debug("指定时间转字符串，返回的字符串：" + dayString);
            return dayString;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("指定时间转字符串，异常信息:", e);
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(addDay(2, "2019-10-19", DATE_FORMATER, DAYS, 1));
    }

}
