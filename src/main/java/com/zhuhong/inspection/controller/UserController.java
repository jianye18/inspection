package com.zhuhong.inspection.controller;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息页面交互层
 * @Author: jian.ye
 * @Date: 2019/10/10 14:04
 */
@Api(value = "用户信息controller")
@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 功能描述：分页获取系统用户信息
     *
     * @return com.shop.common.config.Result
     * @author jianye2
     * @date 2018/9/13 15:18
     * @version 1.0.0
     */
    @ApiOperation(value = "分页获取系统用户信息", notes = "返回系统用户信息列表")
    @ApiImplicitParam(name = "userCondition", value = "查询参数", dataType = "UserCondition")
    @PostMapping("/getUserPageList")
    public Result<User> getSysUserPageList(@RequestBody UserCondition userCondition) {
        String LOG_MSG = "调用分页获取系统用户信息接口---getSysUserPageList()---，";
        log.debug(LOG_MSG + "上传参数：" + userCondition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<User> list = userService.getUserPageListByCondition(userCondition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

}
