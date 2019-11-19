package com.zhuhong.inspection.controller.system;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.dto.CriterionDto;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.service.CriterionService;
import com.zhuhong.inspection.vo.CriterionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 标准数据交互层
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 11:58
 */
@Api(value = "标准数据controller")
@Slf4j
@RestController
@RequestMapping("/api/criterion/")
public class CriterionController extends BaseController {

    @Autowired
    private CriterionService criterionService;

    @ApiOperation(value = "分页获取标准数据", notes = "返回标准数据列表")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "CriterionCondition")
    @PostMapping("getCriterionPageList")
    public Result<CriterionVo> getCriterionPageList(@RequestBody CriterionCondition condition) {
        String LOG_MSG = "调用分页获取标准数据接口---getSpotCheckPageList()---，";
        log.debug(LOG_MSG + "上传参数：" + condition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<CriterionVo> list = criterionService.getCriterionPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "判断标准数据名称是否存在", notes = "判断标准数据名称是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "标准数据名称"),
            @ApiImplicitParam(name = "criterionId", value = "标准数据ID")
    })
    @GetMapping("judgeCriterionNameIsExist")
    public Result<Criterion> judgeCriterionNameIsExist(String name, Integer criterionId) {
        String LOG_MSG = "调用判断标准数据名称是否存在接口---judgeCriterionNameIsExist()---，";
        log.debug(LOG_MSG + "上传参数：{name=" + name + "&criterionId=" + criterionId + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = criterionService.judgeCriterionNameIsExist(name, criterionId);
            if (flag) {
                result = Result.genSuccessResultMsg("标准数据已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存标准数据", notes = "保存标准数据")
    @ApiImplicitParam(name = "criterionDto", value = "标准数据", dataType = "CriterionDto")
    @PostMapping("saveCriterion")
    public Result saveCriterion(@RequestBody CriterionDto criterionDto, HttpServletRequest request) {
        String LOG_MSG = "调用保存标准数据接口---saveCriterion()---，";
        log.debug(LOG_MSG + "上传参数：" + criterionDto.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = criterionService.saveCriterion(criterionDto, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存标准数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取标准数据", notes = "根据ID获取标准数据")
    @ApiImplicitParam(name = "id", value = "标准数据ID", example = "1")
    @GetMapping("getCriterionById/{id}")
    public Result<CriterionVo> getCriterionById(@PathVariable(value = "id") Integer id) {
        String LOG_MSG = "调用根据ID获取标准数据接口---getCriterionById()---，";
        log.debug(LOG_MSG + "上传参数：" + id);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(criterionService.getCriterionById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除标准数据", notes = "删除标准数据")
    @ApiImplicitParam(name = "criterionId", value = "标准数据ID", example = "1")
    @DeleteMapping("deleteCriterion/{criterionId}")
    public Result deleteCriterion(@PathVariable(value = "criterionId", required = true) Integer criterionId, HttpServletRequest request) {
        String LOG_MSG = "调用删除标准数据接口---deleteCriterion()---，";
        log.debug(LOG_MSG + "上传参数：criterionId=" + criterionId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = criterionService.deleteCriterion(criterionId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除标准数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

}
