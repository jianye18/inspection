package com.zhuhong.inspection.aop;

import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.UserLogService;
import com.zhuhong.inspection.utils.CookieUtil;
import com.zhuhong.inspection.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Autowired
    private UserLogService userLogService;

    @Pointcut("@annotation(com.zhuhong.inspection.aop.SystemLog)")
    public void webLog() {
    }

    @Around("webLog()")
    public Object saveLog(ProceedingJoinPoint pjp) {
        // 此处joinPoint的实现类是MethodInvocationProceedingJoinPoint
        Signature signature = pjp.getSignature();
        // 获取参数名
        MethodSignature methodSignature = (MethodSignature) signature;
        SystemLog log = methodSignature.getMethod().getAnnotation(SystemLog.class);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String accessToken = CookieUtil.getCookieValue(Constants.TOKEN, request);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(accessToken);
        UserLog userLog = new UserLog();
        Integer userId = user.getId();
        userLog.setUserId(userId);
        userLog.setUserName(user.getUserName());
        String ip = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ip)) {
            ip = request.getRemoteAddr();
        }
        userLog.setIp(ip);
        userLog.setType(log.type());
        userLog.setDescription(log.description());
        userLog.setCreateId(userId);
        userLog.setCreateTime(DateUtil.getCurrentDate());
        userLogService.saveUserLog(userLog);
        try {
            // 直接pjp.proceed()，没有return ，前台页面获取不到ajax数据
            return pjp.proceed();
        } catch (Throwable e) {
            doAfterThrowing(e);
            return null;
        }
    }

    @Pointcut("execution(public * com.zhuhong.inspection.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.debug("### 请求地址 : " + request.getRequestURL().toString());
        log.debug("### 请求类型 : " + request.getMethod());
        log.debug("### 请求IP : " + request.getRemoteAddr());
        log.debug("### 请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.debug("### 请求参数 : " + Arrays.toString(joinPoint.getArgs()));

    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("### 请求返回结果 : " + ret);
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrowing (Throwable e) {
        log.error("### 返回异常信息：", e);
    }

}
