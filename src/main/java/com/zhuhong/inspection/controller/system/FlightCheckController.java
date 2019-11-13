package com.zhuhong.inspection.controller.system;

import com.alibaba.excel.EasyExcel;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.listener.excel.FlightCheckExcelListener;
import com.zhuhong.inspection.listener.excel.SpotCheckExcelListener;
import com.zhuhong.inspection.model.FlightCheck;
import com.zhuhong.inspection.model.MultiMedia;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.service.FlightCheckService;
import com.zhuhong.inspection.utils.ImageUtil;
import com.zhuhong.inspection.utils.VideoUtil;
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
@RequestMapping("/flightCheck/")
public class FlightCheckController extends BaseController {

    @Autowired
    private FlightCheckService flightCheckService;

    @Value("${upload_path}")
    private String FILE_DIR;

    /**
     * 上传飞检结果数据
     *
     * @Author: jian.ye
     * @Date: 2019/10/16 16:04
     */
    @ApiOperation(value = "上传飞检信息excel", notes = "上传飞检信息")
    @PostMapping("/uploadFlightCheck")
    public Result uploadFlightCheck(MultipartFile file, HttpServletRequest request) {
        String LOG_MSG = "调用上传飞检信息接口---uploadFlightCheck()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        FlightCheckExcelListener listener = new FlightCheckExcelListener(flightCheckService, getCurrentUser(request));
        try {
            EasyExcel.read(file.getInputStream(), FlightCheck.class, listener).sheet().doRead();
            result = Result.genSuccessResultMsg("上传成功！");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            int line = listener.getSUCCESS_COUNT() + 1;
            result = Result.genFailResult(line + "行数据有问题，请确认后再上传！");
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 上传缺陷问题图片
     * @Author: jian.ye
     * @Date: 2019/11/13 15:26
     */
    @ApiOperation(value = "上传缺陷问题图片", notes = "返回上传结果")
    @PostMapping("uploadMediaFile")
    public Result uploadMediaFile(@RequestParam("file") MultipartFile file) {
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try{
            //Map<String, String> map = ImageUtil.saveImage(file, FILE_DIR, String.valueOf(System.currentTimeMillis()), true);
            Map<String, String> map = VideoUtil.uploadVideo(file, FILE_DIR, String.valueOf(System.currentTimeMillis()));
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

    /**
     * 保存飞检数据
     * @Author: jian.ye
     * @Date: 2019/11/09 15:23
     */
    @ApiOperation(value = "保存飞检数据", notes = "保存飞检数据")
    @ApiImplicitParam(name = "flightCheck", value = "飞检数据", dataType = "FlightCheck")
    @PostMapping("saveFlightCheck")
    public Result saveFlightCheck(@RequestBody FlightCheck flightCheck, HttpServletRequest request) {
        String LOG_MSG = "调用保存飞检数据接口---saveFlightCheck()---，";
        log.debug(LOG_MSG + "上传参数：" + flightCheck.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = flightCheckService.saveFlightCheck(flightCheck, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存飞检数据成功");
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
     * 删除飞检数据
     * @Author: jian.ye
     * @Date: 2019/11/09 16:46
     */
    @ApiOperation(value = "删除飞检数据", notes = "删除飞检数据")
    @ApiImplicitParam(name = "flightCheckId", value = "飞检数据ID", example = "1")
    @DeleteMapping("/deleteFlightCheck/{flightCheckId}")
    public Result deleteFlightCheck(@PathVariable(value = "flightCheckId", required = true) Integer flightCheckId, HttpServletRequest request) {
        String LOG_MSG = "调用删除法规数据接口---deleteFlightCheck()---，";
        log.debug(LOG_MSG + "上传参数：flightCheckId=" + flightCheckId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = flightCheckService.deleteFlightCheck(flightCheckId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除飞检数据成功");
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
