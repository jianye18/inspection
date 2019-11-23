package com.zhuhong.inspection.controller;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.SystemDataCondition;
import com.zhuhong.inspection.model.SystemData;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.SystemDataService;
import com.zhuhong.inspection.vo.SelectionLabel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 系统数据页面交互层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:37
 */
@Api(value = "系统数据信息controller")
@Slf4j
@RestController
@RequestMapping("/api/system/")
public class SystemDataController extends BaseController {

    @Autowired
    private SystemDataService systemDataService;

    @ApiOperation(value = "保存分类常量数据")
    @ApiImplicitParam(name = "systemData", value = "分类数据实体", dataType = "SystemData")
    @PostMapping("/saveSystemData")
    @SystemLog(description = "保存分类常量数据", type = UserLog.USER_LOG_SAVE)
    public Result saveSystemData(@RequestBody SystemData systemData, HttpServletRequest request) {
        String logMsg = "调用保存分类常量数据接口---saveSystemData()---，";
        log.debug(logMsg + "参数：" + JSON.toJSONString(systemData));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            if (systemDataService.saveSystemData(systemData, getCurrentUser(request).getId())) {
                result = Result.genSuccessResultMsg("保存分类常量数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "分页获取系统分类常量数据")
    @ApiImplicitParam(name = "condition", value = "查询条件", dataType = "SystemDataCondition")
    @PostMapping("/getSystemDataPageList")
    public Result<List<SystemData>> getSystemDataPageList(@RequestBody SystemDataCondition condition) {
        String logMsg = "调用分页获取系统分类常量数据接口---getSystemDataPageList()---，";
        log.debug(logMsg + "参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            result = Result.genSuccessResult(systemDataService.getSystemDataPageList(condition));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取所有系统分类")
    @GetMapping("/getAllSystemType")
    public Result<List<SelectionLabel>> getAllSystemType() {
        String logMsg = "调用获取所有系统分类接口---getAllSystemType()---，";
        Result result;
        try {
            result = Result.genSuccessResult(systemDataService.getAllSystemType());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据父级编码获取常量数据")
    @GetMapping("/getSystemDataByTypeCode/{typeCodes}")
    public Result getSystemDataByTypeCode(@PathVariable(value = "typeCodes") String typeCodes) {
        String logMsg = "调用根据父级编码获取常量数据接口---getSystemDataByTypeCode()---，";
        Result result;
        log.debug(logMsg + "参数：" + typeCodes);
        try {
            result = Result.genSuccessResult(systemDataService.getSystemDataByTypeCode(typeCodes));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
