package com.zhuhong.inspection.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间日期处理类
 * @Author: jian.ye
 * @Date: 2019/10/8 15:17
 */
@Slf4j
public class DateUtil {

    public static final String DATE_FORMATER = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMATER = "yyyy-MM-dd HH:mm:ss";

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

}
