package com.zhuhong.inspection.controller.system;

import com.alibaba.excel.EasyExcel;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.listener.excel.SpotCheckExcelListener;
import com.zhuhong.inspection.model.SpotCheck;
import com.zhuhong.inspection.service.SpotCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/spotCheck/")
public class SpotCheckController extends BaseController {

    @Autowired
    private SpotCheckService spotCheckService;

    /**
     * 上传抽检结果数据
     *
     * @Author: jian.ye
     * @Date: 2019/10/16 16:04
     */
    @ApiOperation(value = "上传抽检信息excel", notes = "上传抽检信息")
    @PostMapping("/upload")
    public Result upload(MultipartFile file, HttpServletRequest request) {
        String LOG_MSG = "调用上传抽检信息接口---upload()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        SpotCheckExcelListener listener = new SpotCheckExcelListener(spotCheckService, getCurrentUser(request));
        try {
            EasyExcel.read(file.getInputStream(), SpotCheck.class, listener).sheet().doRead();
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

}
