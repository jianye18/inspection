package com.zhuhong.inspection.controller;

import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.service.SystemDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统数据页面交互层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:37
 */
@Api(value = "系统数据信息controller")
@Slf4j
@RestController
@RequestMapping("/system/")
public class SystemDataController extends BaseController {

    @Autowired
    private SystemDataService systemDataService;

    /**
     * 获取抽检数据分类数据
     *
     * @Author: jian.ye
     * @Date: 2019/10/16 17:34
     */
    @ApiOperation(value = "获取抽检数据分类数据", notes = "返回抽检数据分类数据列表")
    @GetMapping("/getAllSystemDataTypeList")
    public Result getAllSystemDataTypeList() {
        String LOG_MSG = "获取抽检数据分类数据---getAllSystemDataTypeList()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            Map<String, List> map = systemDataService.getAllSystemDataTypeList();
            result = Result.genSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

}
