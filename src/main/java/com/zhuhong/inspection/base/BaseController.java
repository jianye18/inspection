package com.zhuhong.inspection.base;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.utils.CookieUtil;
import com.zhuhong.inspection.vo.UserVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 基础controller
 * @Author: jian.ye
 * @Date: 2019/10/10 12:02
 */
@Api(value = "基础controller")
@Slf4j
public class BaseController {

    protected static final String FAIL_MESSAGE = "系统发生错误，请稍后重试！";

    @Value("${upload_path}")
    protected String fileDir;

    /**
     * 获取当前用户信息
     * @param request
     * @return UserVo
     * @Author: jian.ye
     * @Date: 2019/10/10 12:00
     */
    protected User getCurrentUser(HttpServletRequest request) {
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        log.debug("获取当前用户信息的cookie保存内容：" + accessToken);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(accessToken);
        log.debug("获取当前用户信息：" + JSON.toJSONString(user));
        return user;
    }

    /**
     * 下载文件进行编码格式化
     * @Author: jian.ye
     * @Date: 2019/11/4 14:16
     */
    public static String encodeFileName(HttpServletRequest request,
                                        String fileName) throws UnsupportedEncodingException {
        String agent = request.getHeader("USER-AGENT");

        if (null != agent && -1 != agent.toUpperCase().indexOf("MSIE")) {
            return URLEncoder.encode(fileName, "UTF8");
        } else {
            return new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
    }

}
