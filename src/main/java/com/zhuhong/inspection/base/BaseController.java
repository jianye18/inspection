package com.zhuhong.inspection.base;

import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.utils.CookieUtil;
import com.zhuhong.inspection.vo.UserVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础controller
 * @Author: jian.ye
 * @Date: 2019/10/10 12:02
 */
@Api(value = "基础controller")
@Slf4j
public class BaseController {

    protected static final String FAIL_MESSAGE = "系统发生错误，请稍后重试！";

    /**
     * 获取当前用户信息
     * @param request
     * @return UserVo
     * @Author: jian.ye
     * @Date: 2019/10/10 12:00
     */
    protected UserVo getCurrentUser(HttpServletRequest request) {
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        log.debug("获取当前用户信息的cookie保存内容：" + accessToken);
        Session session = SecurityUtils.getSubject().getSession();
        UserVo userVo = (UserVo) session.getAttribute(accessToken);
        log.debug("获取当前用户信息：" + userVo.toString());
        return userVo;
    }

}
