package com.zhuhong.inspection.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "返回页面controller")
@Controller
@RequestMapping("/html/")
@Slf4j
public class HtmlController {

    @GetMapping("login")
    public String login() {
        log.debug("返回登录页");
        return "login";
    }

    @GetMapping("main")
    public String main() {
        log.debug("返回首页");
        return "main";
    }

    @GetMapping("header")
    public String header() {
        log.debug("返回头部");
        return "common/header";
    }

}
