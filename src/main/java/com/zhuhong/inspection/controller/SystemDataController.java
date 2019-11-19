package com.zhuhong.inspection.controller;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.SystemDataTypeCondition;
import com.zhuhong.inspection.model.SystemDataType;
import com.zhuhong.inspection.service.SystemDataService;
import com.zhuhong.inspection.vo.SystemDataTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/system/")
public class SystemDataController extends BaseController {

    @Autowired
    private SystemDataService systemDataService;

    /**
     * 获取抽检数据分类数据
     *
     * @Author: jian.ye
     * @Date: 2019/10/16 17:34
     */
    @ApiOperation(value = "获取分类数据", notes = "返回数据分类数据列表")
    @ApiImplicitParam(name = "type", value = "类型：1-抽检、2-标准、3-法规", example = "1")
    @GetMapping("/getAllSystemDataTypeList/{type}")
    public Result getAllSystemDataTypeList(@PathVariable(value = "type", required = true) Integer type) {
        String LOG_MSG = "调用获取分类数据数据---getAllSystemDataTypeList()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            Map<String, List> map = systemDataService.getAllSystemDataTypeList(type, null, null);
            result = Result.genSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 分页查询分类数据
     * @Author: jian.ye
     * @Date: 2019/10/21 15:45
     */
    @ApiOperation(value = "分页查询分类数据", notes = "分页查询分类数据")
    @ApiImplicitParam(name = "condition", value = "分类数据查询条件类", dataType = "SystemDataTypeCondition")
    @PostMapping("/getSystemDataTypePageList")
    public Result<SystemDataTypeVo> getSystemDataTypePageList(@RequestBody SystemDataTypeCondition condition) {
        String LOG_MSG = "调用分页查询分类数据接口---getSystemDataTypePageList()---，";
        log.debug(LOG_MSG + "参数：" + condition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<SystemDataTypeVo> list = systemDataService.getSystemDataTypePageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 保存分类数据
     * @Author: jian.ye
     * @Date: 2019/10/21 15:35
     */
    @ApiOperation(value = "保存分类数据", notes = "保存分类数据")
    @ApiImplicitParam(name = "systemDataType", value = "分类数据实体", dataType = "SystemDataType")
    @PostMapping("/saveSystemDataType")
    public Result saveSystemDataType(@RequestBody SystemDataType systemDataType, HttpServletRequest request) {
        String LOG_MSG = "调用保存分类数据接口---saveSystemDataType()---，";
        log.debug(LOG_MSG + "参数：" + systemDataType.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            if (systemDataService.saveSystemDataType(systemDataType, getCurrentUser(request).getId())) {
                result = Result.genSuccessResultMsg("保存分类数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 获取首页筛选项
     * @Author: jian.ye
     * @Date: 2019/10/30 14:12
     */
    @ApiOperation(value = "获取首页筛选项", notes = "获取首页筛选项")
    @GetMapping("/getHomePageFilterItem")
    public Result getHomePageFilterItem() {
        String LOG_MSG = "调用获取首页筛选项接口---getHomePageFilterItem()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(systemDataService.getHomePageFilterItem());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 根据条件查询分类数据
     * @Author: jian.ye
     * @Date: 2019/11/7 20:40
     */
    @ApiOperation(value = "根据条件查询分类数据", notes = "根据条件查询分类数据")
    @ApiImplicitParam(name = "systemDataType", value = "查询条件", dataType = "SystemDataType")
    @PostMapping("/getLawCategoryData")
    public Result getLawCategoryData(@RequestBody SystemDataType systemDataType) {
        String LOG_MSG = "调用根据条件查询分类数据接口---getLawCategoryData()---，";
        log.debug(LOG_MSG + "参数：" + systemDataType.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(systemDataService.getLawCategoryData(systemDataType));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 根据父级编码获取常量数据
     * @Author: jian.ye
     * @Date: 2019/10/30 14:12
     */
    @ApiOperation(value = "根据父级编码获取常量数据", notes = "根据父级编码获取常量数据")
    @GetMapping("/getSystemDataByTypeCode/{typeCodes}")
    public Result getSystemDataByTypeCode(@PathVariable(value = "typeCodes") String typeCodes) {
        String LOG_MSG = "调用根据父级编码获取常量数据接口---getSystemDataByTypeCode()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        log.debug(LOG_MSG + "参数：" + typeCodes);
        try {
            result = Result.genSuccessResult(systemDataService.getSystemDataByTypeCode(typeCodes));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

}
