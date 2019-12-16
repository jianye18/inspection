package com.zhuhong.inspection.controller.system;

import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.service.VisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 叶剑
 */
@Api(value = "访客统计数据controller")
@Slf4j
@RestController
@RequestMapping("/visitor/")
public class VisitorController extends BaseController {

    @Autowired
    private VisitorService visitorService;

    @ApiOperation(value = "获取访客统计数量")
    @GetMapping("getVisitorCount/{type}")
    @ApiImplicitParam(name = "type", value = "获取客户访问统计", example = "1")
    public Result getVisitorCount(@PathVariable(value = "type") Integer type) {
        String logMsg = "调用获取访客统计数量接口---getVisitorCount()---，";
        log.debug(logMsg + "上传参数：" + type);
        Result result;
        try {
            result = Result.genSuccessResult(visitorService.getVisitorCount(type));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
