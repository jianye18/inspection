package com.zhuhong.inspection.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

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
