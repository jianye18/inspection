package com.zhuhong.inspection.controller;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.UserService;
import com.zhuhong.inspection.utils.CookieUtil;
import com.zhuhong.inspection.utils.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户登录交互层
 * @Author: jian.ye
 * @Date: 2019/10/8 18:47
 */
@Api(value = "登录controller")
@RestController
@RequestMapping("/login/")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    
    @ApiOperation(value = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户登录账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户登录密码", required = true, dataType = "String")
    })
    @RequestMapping(value = "loginIn", method = RequestMethod.POST)
    public Result loginIn(@RequestBody Map<String, String> map, HttpServletRequest request) {
        String logMsg = "调用系统用户登录接口---loginIn()---，";
        Result result;
        String loginName = map.get("loginName");
        String password = map.get("password");
        log.info(logMsg + "上传参数{loginName=" + loginName + ",password=" + password + "}");
        /*UsernamePasswordToken token = new UsernamePasswordToken(loginName, MD5.getMD5(password));
        token.setRememberMe(true);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
        //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
        //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
        currentUser.login(token);
        result.setData(currentUser.getSession().getId());
        System.out.println(currentUser.getSession().getId());
        //CookieUtil.addCookie(Constants.TOKEN, (String) currentUser.getSession().getId(), 30 * 60 * 1000, response);
        //验证是否登录成功
        if (!currentUser.isAuthenticated()) {
            result = Result.genFailResult("用户无权限");
        }*/
        User user = userService.getUserByNickName(loginName);
        if (null == user) {
            result = Result.genFailResult("用户不存在！");
        } else if (Constants.SYS_USER_STATUS_2.equals(user.getUserStatus())) {
            result = Result.genFailResult("账号禁用中，请联系管理员！");
        } else if (!user.getPassword().equals(MD5.getMD5(password))) {
            result = Result.genFailResult("密码不正确！");
        } else {
            String token = MD5.getMD5(JSON.toJSONString(user));
            request.getSession().setAttribute(token, user);
            request.getSession().setMaxInactiveInterval(24 * 60 * 60 * 1000);
            result = Result.genSuccessResult(token);
        }
        log.info(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "用户退出登录")
    @PostMapping("/loginOut")
    public Result loginOut(HttpServletRequest request, HttpServletResponse response) {
        String logMsg = "调用系统用户退出登录接口---loginOut()---，";
        log.info(logMsg);
        Result result = Result.genSuccessResult();
        try {
            CookieUtil.removeCookie(Constants.TOKEN, request, response);
            /*Subject subject = SecurityUtils.getSubject();
            subject.logout();*/
            request.getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult("用户退出失败");
        }
        log.info(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存用户登录信息")
    @PostMapping("/saveUserLoginLog")
    @SystemLog(description = "用户登录", type = UserLog.USER_LOG_LOGIN)
    public void saveUserLoginLog(){

    }

}
