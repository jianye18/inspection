package com.zhuhong.inspection.interceptor;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qq892
 */
@Component
@Slf4j
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        log.debug("获取当前用户信息的cookie保存内容：" + accessToken);
        if (StringUtils.isNotEmpty(accessToken)) {
            User user = (User) request.getSession().getAttribute(accessToken);
            if (user == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        log.debug("获取当前用户信息的cookie保存内容：" + accessToken);
        User user = (User) request.getSession().getAttribute(accessToken);
        System.out.println(JSON.toJSONString(user));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        log.debug("获取当前用户信息的cookie保存内容：" + accessToken);
        User user = (User) request.getSession().getAttribute(accessToken);
        System.out.println(JSON.toJSONString(user));
    }

}
