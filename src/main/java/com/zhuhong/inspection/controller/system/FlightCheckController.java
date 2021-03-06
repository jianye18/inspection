package com.zhuhong.inspection.controller.system;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.dto.FlightCheckDto;
import com.zhuhong.inspection.listener.excel.FlightCheckExcelListener;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.model.MultiMedia;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.FlightCheckService;
import com.zhuhong.inspection.utils.ImageUtil;
import com.zhuhong.inspection.vo.FlightCheckVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 飞检数据页面交互层
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 20:39
 */
@Api(value = "飞检数据controller")
@Slf4j
@RestController
@RequestMapping("/api/flightCheck/")
public class FlightCheckController extends BaseController {

    @Autowired
    private FlightCheckService flightCheckService;

    @Value("${upload_path}")
    private String fileDir;

    @ApiOperation(value = "分页获取飞检数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "FlightCheckCondition")
    @PostMapping("getFlightCheckPageList")
    public Result<FlightCheckVo> getFlightCheckPageList(@RequestBody FlightCheckCondition condition) {
        String logMsg = "调用分页获取飞检数据接口---getFlightCheckPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<FlightCheckVo> list = flightCheckService.getFlightCheckPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "上传飞检信息excel")
    @PostMapping("/uploadFlightCheck")
    @SystemLog(description = "新增飞检数据", type = UserLog.USER_LOG_SAVE)
    public Result uploadFlightCheck(MultipartFile file, HttpServletRequest request) {
        String logMsg = "调用上传飞检信息接口---uploadFlightCheck()---，";
        Result result;
        FlightCheckExcelListener listener = new FlightCheckExcelListener(flightCheckService, getCurrentUser(request));
        try {
            EasyExcel.read(file.getInputStream(), FlightCheck.class, listener).sheet().doRead();
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

    @ApiOperation(value = "上传缺陷问题图片")
    @PostMapping("uploadMediaFile")
    @SystemLog(description = "上传缺陷问题图片", type = UserLog.USER_LOG_SAVE)
    public Result uploadMediaFile(@RequestParam("file") MultipartFile file) {
        Result result;
        try{
            Map<String, String> map = ImageUtil.saveImage(file, fileDir, String.valueOf(System.currentTimeMillis()), true);
            log.debug("上传图片返回结果：" + map.toString());
            MultiMedia multiMedia = new MultiMedia();
            multiMedia.setMediaName(map.get("mediaName"));
            multiMedia.setThumbnail(map.get("thumbnail"));
            multiMedia.setSize(file.getSize());
            multiMedia.setMediaType(file.getContentType());
            result = Result.genSuccessResult(multiMedia);
        }catch (Exception e){
            e.printStackTrace();
            result = Result.genFailResult(e.getMessage());
            log.error("上传媒体文件返回错误信息：", e);
        }
        return result;
    }

    @ApiOperation(value = "保存飞检数据")
    @ApiImplicitParam(name = "flightCheck", value = "飞检数据", dataType = "FlightCheck")
    @PostMapping("saveFlightCheck")
    @SystemLog(description = "保存飞检数据", type = UserLog.USER_LOG_SAVE)
    public Result saveFlightCheck(@RequestBody FlightCheckDto flightCheckDto, HttpServletRequest request) {
        String logMsg = "调用保存飞检数据接口---saveFlightCheck()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(flightCheckDto));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = flightCheckService.saveFlightCheck(flightCheckDto, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存飞检数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取飞检数据")
    @ApiImplicitParam(name = "id", value = "飞检数据ID", example = "1")
    @GetMapping("getFlightCheckById/{id}")
    public Result<FlightCheckVo> getFlightCheckById(@PathVariable(value = "id") Integer id) {
        String logMsg = "调用根据ID获取飞检数据接口---getFlightCheckById()---，";
        log.debug(logMsg + "上传参数：" + id);
        Result result;
        try {
            result = Result.genSuccessResult(flightCheckService.getFlightCheckById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除飞检数据")
    @ApiImplicitParam(name = "flightCheckId", value = "飞检数据ID", example = "1")
    @DeleteMapping("/deleteFlightCheck/{flightCheckId}")
    @SystemLog(description = "删除飞检数据", type = UserLog.USER_LOG_DELETE)
    public Result deleteFlightCheck(@PathVariable(value = "flightCheckId") Integer flightCheckId, HttpServletRequest request) {
        String logMsg = "调用删除法规数据接口---deleteFlightCheck()---，";
        log.debug(logMsg + "上传参数：flightCheckId=" + flightCheckId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = flightCheckService.deleteFlightCheck(flightCheckId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除飞检数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取飞检数据的发布单位")
    @GetMapping("/getAllPublishUnit")
    public Result getAllPublishUnit() {
        String logMsg = "调用获取飞检数据的发布单位接口---getAllPublishUnit()---，";
        Result result;
        try {
            result = Result.genSuccessResult(flightCheckService.getAllPublishUnit());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取飞检数据的处理措施")
    @GetMapping("/getPrecautionsList")
    public Result getPrecautionsList() {
        String logMsg = "调用获取飞检数据的处理措施接口---getPrecautionsList()---，";
        Result result;
        try {
            result = Result.genSuccessResult(flightCheckService.getPrecautionsList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取飞检类型集合")
    @GetMapping("/getTypeList")
    public Result getTypeList() {
        String logMsg = "调用获取飞检类型集合接口---getTypeList()---，";
        Result result;
        try {
            result = Result.genSuccessResult(flightCheckService.getTypeList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据企业名称获取相关统计数据")
    @ApiImplicitParam(name = "businessName", value = "企业名称", example = "上海裕华日用化学品厂")
    @GetMapping("getCountByBusinessName")
    public Result getCountByBusinessName(String businessName) {
        String logMsg = "调用根据企业名称获取相关统计数据接口---getCountByBusinessName()---，";
        log.debug(logMsg + "上传参数：businessName=" + businessName);
        Result result;
        try {
            result = Result.genSuccessResult(flightCheckService.getCountByBusinessName(businessName));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
