package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.dto.LawDto;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.LawService;
import com.zhuhong.inspection.service.LawTypeService;
import com.zhuhong.inspection.vo.LawVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 法规数据交互层
 * @Author: jian.ye
 * @Date: 2019/10/23 14:09
 */
@Api(value = "法规数据controller")
@Slf4j
@RestController
@RequestMapping("/api/law/")
public class LawController extends BaseController {

    @Autowired
    private LawService lawService;
    @Autowired
    private LawTypeService lawTypeService;

    @ApiOperation(value = "分页获取法规数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "LawCondition")
    @PostMapping("/getLawPageList")
    public Result<LawVo> getLawPageList(@RequestBody LawCondition condition) {
        String logMsg = "调用分页获取法规数据接口---getLawPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<LawVo> list = lawService.getLawPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存法规数据")
    @ApiImplicitParam(name = "lawDto", value = "法规数据", dataType = "LawDto")
    @PostMapping("/saveLaw")
    @SystemLog(description = "保存法规数据", type = UserLog.USER_LOG_SAVE)
    public Result saveLaw(@RequestBody LawDto lawDto, HttpServletRequest request) {
        String logMsg = "调用保存法规数据接口---saveLaw()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(lawDto));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = lawService.saveLaw(lawDto, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存法规数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取法规数据")
    @ApiImplicitParam(name = "id", value = "法规数据ID", example = "1")
    @GetMapping("getLawById/{id}")
    public Result<LawVo> getLawById(@PathVariable(value = "id") Integer id) {
        String logMsg = "调用根据ID获取法规数据接口---getLawById()---，";
        log.debug(logMsg + "上传参数：" + id);
        Result result;
        try {
            result = Result.genSuccessResult(lawService.getLawById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除法规数据")
    @ApiImplicitParam(name = "lawId", value = "法规数据ID", example = "1")
    @DeleteMapping("/deleteLaw/{lawId}")
    @SystemLog(description = "删除法规数据", type = UserLog.USER_LOG_DELETE)
    public Result deleteLaw(@PathVariable(value = "lawId") Integer lawId, HttpServletRequest request) {
        String logMsg = "调用删除法规数据接口---deleteLaw()---，";
        log.debug(logMsg + "上传参数：lawId=" + lawId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = lawService.deleteLaw(lawId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除法规数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据法规分类获取子分类")
    @ApiImplicitParam(name = "code", value = "法规分类编码", example = "1")
    @GetMapping("/getLawTypeListByCode/{code}")
    public Result getLawTypeListByCode(@PathVariable(value = "code") String code) {
        String logMsg = "调用根据法规分类获取子分类接口---getLawTypeListByCode()---，";
        log.debug(logMsg + "上传参数{}", code);
        Result result;
        try {
            result = Result.genSuccessResult(lawTypeService.getLawTypeListByCode(code));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
