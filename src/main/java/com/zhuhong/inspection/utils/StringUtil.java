package com.zhuhong.inspection.utils;

/**
 * 获取字符串处理类
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 9:43
 */
public class StringUtil {

    /**
     * 根据字符串获取模糊查询的字符串
     *
     * @param str
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/13 9:43
     */
    public static String getLikeString(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("%");
        stringBuffer.append(str);
        stringBuffer.append("%");
        return stringBuffer.toString();
    }

}
