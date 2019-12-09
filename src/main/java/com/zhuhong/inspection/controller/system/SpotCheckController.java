package com.zhuhong.inspection.controller.system;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.listener.excel.SpotCheckExcelListener;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.SpotCheckService;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.SpotCheckVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 抽检信息页面交互层
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 13:52
 */
@Api(value = "抽检信息controller")
@Slf4j
@RestController
@RequestMapping("/api/spotCheck/")
public class SpotCheckController extends BaseController {

    @Autowired
    private SpotCheckService spotCheckService;

    @ApiOperation(value = "上传抽检信息excel")
    @PostMapping("/upload")
    @SystemLog(description = "上传抽检数据", type = UserLog.USER_LOG_SAVE)
    public Result upload(MultipartFile file, HttpServletRequest request) {
        String logMsg = "调用上传抽检信息接口---upload()---，";
        Result result;
        SpotCheckExcelListener listener = new SpotCheckExcelListener(spotCheckService, getCurrentUser(request));
        try {
            EasyExcel.read(file.getInputStream(), SpotCheck.class, listener).sheet().doRead();
            result = Result.genSuccessResultMsg("上传成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            int line = listener.getSUCCESS_COUNT() + 1;
            result = Result.genFailResult(line + "行数据有问题，请确认后再上传！");
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存抽检数据")
    @PostMapping("/saveSpotCheck")
    @SystemLog(description = "保存抽检数据", type = UserLog.USER_LOG_UPDATE)
    public Result saveSpotCheck(@RequestBody SpotCheck spotCheck, HttpServletRequest request) {
        String logMsg = "调用保存抽检数据接口---saveSpotCheck()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(spotCheck));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
           if (spotCheckService.saveSpotCheck(spotCheck, getCurrentUser(request).getId())) {
               result = Result.genSuccessResultMsg("保存抽检数据成功");
           }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "分页获取抽检结果数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "SpotCheckCondition")
    @PostMapping("getSpotCheckPageList")
    public Result<SpotCheckVo> getSpotCheckPageList(@RequestBody SpotCheckCondition condition) {
        String logMsg = "调用分页获取抽检结果数据接口---getSpotCheckPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<SpotCheckVo> list = spotCheckService.getSpotCheckPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取抽检数据")
    @ApiImplicitParam(name = "id", value = "抽检数据ID", example = "1")
    @GetMapping("getSpotCheckById/{id}")
    public Result<SpotCheckVo> getSpotCheckById(@PathVariable(value = "id") Integer id) {
        String logMsg = "调用根据ID获取抽检数据接口---getSpotCheckById()---，";
        log.debug(logMsg + "上传参数：" + id);
        Result result;
        try {
            result = Result.genSuccessResult(spotCheckService.getSpotCheckById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取抽检数据的公布机构")
    @GetMapping("getAllInstitution")
    public Result<SelectionLabel> getAllInstitution() {
        String logMsg = "调用获取抽检数据的公布机构接口---getAllInstitution()---，";
        Result result;
        try {
            result = Result.genSuccessResult(spotCheckService.getAllInstitution());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取抽检数据的产品类型集合")
    @GetMapping("getAllProductType")
    public Result<SelectionLabel> getAllProductType() {
        String logMsg = "调用获取抽检数据的产品类型集合接口---getAllProductType()---，";
        Result result;
        try {
            result = Result.genSuccessResult(spotCheckService.getProductTypeList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除抽检数据")
    @ApiImplicitParam(name = "id", value = "抽检数据ID", example = "1")
    @DeleteMapping("deleteSpotCheck/{id}")
    @SystemLog(description = "删除抽检数据", type = UserLog.USER_LOG_DELETE)
    public Result deleteSpotCheck(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        String logMsg = "调用删除抽检数据接口---deleteSpotCheck()---，";
        log.debug(logMsg + "上传参数：id=" + id);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            if (spotCheckService.deleteSpotCheck(id, getCurrentUser(request).getId())) {
                result = Result.genSuccessResultMsg("删除抽检数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
