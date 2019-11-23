package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.UserCondition;
import com.zhuhong.inspection.model.User;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.model.UserRole;
import com.zhuhong.inspection.service.UserService;
import com.zhuhong.inspection.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    
    @ApiOperation(value = "分页获取系统用户信息")
    @ApiImplicitParam(name = "userCondition", value = "查询参数", dataType = "UserCondition")
    @PostMapping("/getUserPageList")
    public Result<PageInfo<User>> getUserPageList(@RequestBody UserCondition condition) {
        String logMsg = "调用分页获取系统用户信息接口---getUserPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<UserVo> list = userService.getUserPageListByCondition(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存用户信息")
    @ApiImplicitParam(name = "user", value = "用户信息", dataType = "User")
    @PostMapping("/saveUser")
    @SystemLog(description = "保存用户信息", type = UserLog.USER_LOG_SAVE)
    public Result saveUser(@RequestBody User user, HttpServletRequest request) {
        String logMsg = "调用保存用户信息接口---saveUser()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(user));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = userService.saveUser(user, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存用户信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除用户信息", notes = "删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID")
    @DeleteMapping("/deleteUser/{userId}")
    @SystemLog(description = "删除用户信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteUser(@PathVariable(value = "userId") Integer userId, HttpServletRequest request) {
        String logMsg = "调用删除用户信息接口---deleteUser()---，";
        log.debug(logMsg + "上传参数：{userId=" + userId + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = userService.deleteUser(userId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除用户信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "判断昵称是否存在", notes = "判断昵称是否存在")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "nickName", value = "用户昵称"),
        @ApiImplicitParam(name = "userId", value = "用户Id")
    })
    @GetMapping("/judgeNickNameIsExist")
    public Result<User> judgeNickNameIsExist(String nickName, Integer userId) {
        String logMsg = "调用判断昵称是否存在接口---judgeNickNameIsExist()---，";
        log.debug(logMsg + "上传参数：{nickName=" + nickName + "&userId=" + userId + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = userService.judgeNickNameIsExist(nickName, userId);
            if (flag) {
                result = Result.genSuccessResultMsg("用户已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取所有用户角色信息", notes = "获取所有用户角色信息")
    @ApiImplicitParam(name = "userId", value = "用户Id")
    @GetMapping("/getUserRoleListByUserId")
    public Result<List<UserRole>> getUserRoleListByUserId(Integer userId) {
        String logMsg = "调用获取所有用户角色信息接口---getUserRoleListByUserId()---，";
        log.debug(logMsg + "上传参数：{userId=" + userId + "}");
        Result result;
        try {
            List<UserRole> list = userService.getUserRoleListByUserId(userId);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
