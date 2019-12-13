package com.zhuhong.inspection.controller.show;

import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.model.Link;
import com.zhuhong.inspection.model.Statement;
import com.zhuhong.inspection.service.*;
import com.zhuhong.inspection.utils.FileUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 展示页controller
 * @Author: jian.ye
 * @Date: 2019/10/17 17:46
 */
@Api(value = "展示页controller")
@Slf4j
@RestController
@RequestMapping("/api/show/")
public class ShowPageController extends BaseController {

    @Autowired
    private AnnexService annexService;
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private LawService lawService;
    @Autowired
    private CriterionService criterionService;
    @Autowired
    private SpotCheckService spotCheckService;
    @Autowired
    private FlightCheckService flightCheckService;

    @Value("${upload_path}")
    private String fileDir;

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
                String path = fileDir + "docs/";
                if (l == 1) {
                    fileName = list.get(0).getName();
                } else {
                    fileName = System.currentTimeMillis() + ".zip";
                    List<String> filePaths = new ArrayList<>();
                    list.forEach(annex -> filePaths.add(path + annex.getName()));
                    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path + fileName));
                    FileUtil.zipFile(filePaths, zos);
                }
                headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
                File file = new File(path + fileName);
                byte[] b = FileUtils.readFileToByteArray(file);
                if (l > 1) {
                    FileUtil.delAllFile(path + fileName);
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

    @ApiOperation(value = "获取首页轮播图数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "BannerCondition")
    @PostMapping("getViewBannerList")
    public Result<Banner> getViewBannerList(@RequestBody BannerCondition condition) {
        String logMsg = "调用获取首页轮播图数据接口---getViewBannerList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            result = Result.genSuccessResult(bannerService.getViewBannerList(condition));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取首页最新和最热文章信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderName", value = "排序字段"),
            @ApiImplicitParam(name = "limit", value = "查询数量")
    })
    @GetMapping("getHomeArticleList")
    public Result getHomeArticleList(String orderName, Integer limit) {
        String logMsg = "调用获取首页最新和最热文章信息接口---getHomeArticleList()---，";
        log.debug(logMsg + "上传参数：{orderName=" + orderName + ",limit=" + limit + "}");
        Result result;
        try {
            result = Result.genSuccessResult(articleService.getHomeArticleList(orderName, limit));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取所有展示的友情链接")
    @GetMapping("getLinkViewList")
    public Result<List<Link>> getLinkViewList() {
        String logMsg = "调用获取所有展示的友情链接接口---getLinkViewList()---，";
        Result result;
        try {
            result = Result.genSuccessResult(linkService.getLinkViewList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取负责声明信息")
    @GetMapping("getOneViewStatement")
    public Result<Statement> getOneViewStatement() {
        String logMsg = "调用获取负责声明信息接口---getOneViewStatement()---，";
        Result result;
        try {
            result = Result.genSuccessResult(statementService.getOneViewStatement());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取统计数量")
    @GetMapping("getShowCount")
    public Result getShowCount() {
        String logMsg = "调用获取统计数量接口---getShowCount()---，";
        Result result;
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put("lawCount", lawService.getLawTotalCount());
            map.put("criterionCount", criterionService.getCriterionTotalCount());
            map.put("spotCheckCount", spotCheckService.getSpotCheckTotalCount());
            map.put("flightCheckCount", flightCheckService.getFlightCheckTotalCount());
            map.put("articleCount", articleService.getArticleTotalCount());
            result = Result.genSuccessResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
