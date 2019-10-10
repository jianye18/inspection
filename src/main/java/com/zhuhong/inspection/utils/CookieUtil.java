package com.zhuhong.inspection.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie工具类
 * @Author: jian.ye
 * @Date: 2019/10/8 16:19
 */
public class CookieUtil {

    /**
     * 增加一个cookie
     * @param name 名称
     * @param value 值
     * @param maxAge 失效时间(秒)
     * @param response 返回处理类
     * @Author: jian.ye
     * @Date: 2019/10/8 16:31
     */
    public static void addCookie(String name, String value, int maxAge, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(value)) {
            Cookie cookie = new Cookie(name, value);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    /**
     * 获取指定cookie的值
     * @param name 名称
     * @param request 请求处理类
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/8 16:33
     */
    public static String getCookieValue(String name, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(name)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        return cookie.getValue();
                    }
                }
            }
        }
        return "";
    }

    /**
     * 删除指定的cookie
     * @param name 名称
     * @param request 请求处理类
     * @param response 返回处理类
     * @return String
     * @Author: jian.ye
     * @Date: 2019/10/8 16:33
     */
    public static void removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotEmpty(name)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (name.equals(cookie.getName())) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
    }

}
