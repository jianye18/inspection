package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.UserLogCondition;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.UserLogService;
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
 * @author 叶剑
 */
@Api(value = "操作日志数据controller")
@Slf4j
@RestController
@RequestMapping("/userLog/")
public class UserLogController {

    @Autowired
    private UserLogService userLogService;

    @ApiOperation(value = "分页获取操作日志数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "UserLogCondition")
    @PostMapping("getUserLogPageList")
    public Result<UserLog> getUserLogPageList(@RequestBody UserLogCondition condition) {
        String logMsg = "调用分页获取操作日志数据接口---getUserLogPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            result = Result.genSuccessResult(userLogService.getUserLogPageList(condition));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
