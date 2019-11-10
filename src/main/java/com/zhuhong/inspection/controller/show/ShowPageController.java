package com.zhuhong.inspection.controller.show;

import com.alibaba.excel.util.FileUtils;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.condition.FlightCheckCondition;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.service.*;
import com.zhuhong.inspection.utils.FileUtil;
import com.zhuhong.inspection.vo.CriterionVo;
import com.zhuhong.inspection.vo.FlightCheckVo;
import com.zhuhong.inspection.vo.LawVo;
import com.zhuhong.inspection.vo.SpotCheckVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 展示页controller
 * @Author: jian.ye
 * @Date: 2019/10/17 17:46
 */
@Api(value = "展示页controller")
@Slf4j
@RestController
@RequestMapping("/show/")
public class ShowPageController extends BaseController {

    @Autowired
    private SpotCheckService spotCheckService;
    @Autowired
    private CriterionService criterionService;
    @Autowired
    private LawService lawService;
    @Autowired
    private AnnexService annexService;
    @Autowired
    private FlightCheckService flightCheckService;

    @Value("${upload_path}")
    private String FILE_DIR;

    /**
     * 分页获取抽检结果数据
     *
     * @Author: jian.ye
     * @Date: 2019/10/16 16:08
     */
    @ApiOperation(value = "分页获取抽检结果数据", notes = "返回抽检结果数据列表")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "SpotCheckCondition")
    @PostMapping("getSpotCheckPageList")
    public Result<SpotCheckVo> getSpotCheckPageList(@RequestBody SpotCheckCondition condition) {
        String LOG_MSG = "调用分页获取抽检结果数据接口---getSpotCheckPageList()---，";
        log.debug(LOG_MSG + "上传参数：" + condition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<SpotCheckVo> list = spotCheckService.getSpotCheckPageList(condition);
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
     * 分页获取标准数据
     * @Author: jian.ye
     * @Date: 2019/10/19 12:57
     */
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

    /**
     * 分页获取法规数据
     * @Author: jian.ye
     * @Date: 2019/10/19 12:57
     */
    @ApiOperation(value = "分页获取法规数据", notes = "返回法规数据列表")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "LawCondition")
    @PostMapping("/getLawPageList")
    public Result<LawVo> getLawPageList(@RequestBody LawCondition condition) {
        String LOG_MSG = "调用分页获取法规数据接口---getLawPageList()---，";
        log.debug(LOG_MSG + "上传参数：" + condition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<LawVo> list = lawService.getLawPageList(condition);
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
     * 分页获取飞检数据
     *
     * @Author: jian.ye
     * @Date: 2019/11/09 16:08
     */
    @ApiOperation(value = "分页获取飞检数据", notes = "返回分页获取飞检数据列表")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "FlightCheckCondition")
    @PostMapping("getFlightCheckPageList")
    public Result<FlightCheckVo> getFlightCheckPageList(@RequestBody FlightCheckCondition condition) {
        String LOG_MSG = "调用分页获取飞检数据接口---getFlightCheckPageList()---，";
        log.debug(LOG_MSG + "上传参数：" + condition.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            PageInfo<FlightCheckVo> list = flightCheckService.getFlightCheckPageList(condition);
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
     * 根据ID获取抽检数据
     * @Author: jian.ye
     * @Date: 2019/10/31 16:15
     */
    @ApiOperation(value = "根据ID获取抽检数据", notes = "根据ID获取抽检数据")
    @ApiImplicitParam(name = "id", value = "抽检数据ID", example = "1")
    @GetMapping("getSpotCheckById/{id}")
    public Result<SpotCheckVo> getSpotCheckById(@PathVariable(value = "id", required = true) Integer id) {
        String LOG_MSG = "调用根据ID获取抽检数据接口---getSpotCheckById()---，";
        log.debug(LOG_MSG + "上传参数：" + id);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(spotCheckService.getSpotCheckById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 根据ID获取标准数据
     * @Author: jian.ye
     * @Date: 2019/10/31 16:15
     */
    @ApiOperation(value = "根据ID获取标准数据", notes = "根据ID获取标准数据")
    @ApiImplicitParam(name = "id", value = "标准数据ID", example = "1")
    @GetMapping("getCriterionById/{id}")
    public Result<CriterionVo> getCriterionById(@PathVariable(value = "id", required = true) Integer id) {
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

    /**
     * 根据ID获取法规数据
     * @Author: jian.ye
     * @Date: 2019/11/1 14:08
     */
    @ApiOperation(value = "根据ID获取法规数据", notes = "根据ID获取法规数据")
    @ApiImplicitParam(name = "id", value = "法规数据ID", example = "1")
    @GetMapping("getLawById/{id}")
    public Result<LawVo> getLawById(@PathVariable(value = "id", required = true) Integer id) {
        String LOG_MSG = "调用根据ID获取法规数据接口---getLawById()---，";
        log.debug(LOG_MSG + "上传参数：" + id);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(lawService.getLawById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 根据ID获取飞检数据
     * @Author: jian.ye
     * @Date: 2019/11/09 14:08
     */
    @ApiOperation(value = "根据ID获取飞检数据", notes = "根据ID获取飞检数据")
    @ApiImplicitParam(name = "id", value = "飞检数据ID", example = "1")
    @GetMapping("getFlightCheckById/{id}")
    public Result<FlightCheckVo> getFlightCheckById(@PathVariable(value = "id", required = true) Integer id) {
        String LOG_MSG = "调用根据ID获取飞检数据接口---getFlightCheckById()---，";
        log.debug(LOG_MSG + "上传参数：" + id);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            result = Result.genSuccessResult(flightCheckService.getFlightCheckById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 下载附件文件
     * @Author: jian.ye
     * @Date: 2019/11/4 15:12
     */
    @ApiOperation(value = "下载附件文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务数据ID"),
            @ApiImplicitParam(name = "baseType", value = "业务数据类型")
    })
    @GetMapping("downloadFile")
    public ResponseEntity<byte[]> downloadFile(Integer businessId, Integer baseType) {
        String LOG_MSG = "下载附件文件---downloadFile()---，";
        log.debug("下载文件的参数：{businessId=" + businessId + ",baseType=" + baseType + "}");
        List<Annex> list = annexService.getAnnexList(businessId, baseType);
        int l = list.size();
        if (l > 0) {
            try {
                String fileName = "";
                HttpHeaders headers = new HttpHeaders();
                headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
                headers.add("Expires", "0");
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                if (l == 1) {
                    fileName = list.get(0).getName();
                } else {
                    fileName = System.currentTimeMillis() + ".zip";
                    List<String> filePaths = new ArrayList<>();
                    list.forEach(annex -> filePaths.add(annex.getPath()));
                    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(FILE_DIR + fileName));
                    FileUtil.zipFile(filePaths, zos);
                }
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
                File file = new File(FILE_DIR + fileName);
                byte[] b = FileUtils.readFileToByteArray(file);
                if (l > 1) {
                    FileUtil.delAllFile(FILE_DIR + fileName);
                }
                return new ResponseEntity<byte[]>(b, headers, HttpStatus.OK);
            }  catch (Exception e) {
                e.printStackTrace();
                log.error(LOG_MSG + "异常信息，", e);
                return null;
            }
        } else {
            return null;
        }
    }

}
