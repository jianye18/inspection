package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.BannerCondition;
import com.zhuhong.inspection.model.Banner;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.BannerService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.FileUtil;
import com.zhuhong.inspection.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

/**
 * @author 叶剑
 */
@Api(value = "轮播图数据controller")
@Slf4j
@RestController
@RequestMapping("/banner/")
public class BannerController extends BaseController {

    @Autowired
    private BannerService bannerService;

    @Value("${upload_path}")
    private String fileDir;

    @ApiOperation(value = "上传轮播图文件", notes = "返回上传结果")
    @PostMapping("uploadBanner")
    public Result uploadBanner(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Result result;
        log.debug("上传文件名：" + file.getOriginalFilename());
        try{
            //判断文件是否为空
            if(file.isEmpty()){
                result = Result.genFailResult("文件为空，上传失败!");
            } else {
                //获得path对象，也即是文件保存的路径对象
                String[] arr = file.getOriginalFilename().split("\\.");
                String fileName = arr[0];
                String suffix = arr[1];
                String name = fileName + "_" + DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER_1) + "." + suffix;
                String path = fileDir + "banner/";
                if (FileUtil.uploadFile(file, path, name)) {
                    Banner banner = new Banner();
                    banner.setName(name);
                    banner.setPath("banner/");
                    banner.setSize(file.getSize() / 1000);
                    banner.setType(file.getContentType());
                    if (bannerService.saveBanner(banner, getCurrentUser(request).getId())) {
                        result = Result.genSuccessResult();
                    } else {
                        result = Result.genFailResult("上传轮播图失败!");
                    }
                } else {
                    result = Result.genFailResult("上传轮播图失败!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result = Result.genFailResult(e.getMessage());
            log.error("上传文件返回错误信息：", e);
        }
        return result;
    }

    @ApiOperation(value = "分页获取轮播图数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "BannerCondition")
    @PostMapping("getBannerPageList")
    public Result<Banner> getBannerPageList(@RequestBody BannerCondition condition) {
        String logMsg = "调用分页获取轮播图数据接口---getBannerPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            result = Result.genSuccessResult(bannerService.getBannerPageList(condition));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取轮播图数据")
    @ApiImplicitParam(name = "bannerId", value = "轮播图数据ID", example = "1")
    @GetMapping("getBannerById/{bannerId}")
    public Result<ArticleVo> getBannerById(@PathVariable(value = "bannerId") Integer bannerId) {
        String logMsg = "调用根据ID获取轮播图数据接口---getBannerById()---，";
        log.debug(logMsg + "上传参数：" + bannerId);
        Result result;
        try {
            result = Result.genSuccessResult(bannerService.getBannerById(bannerId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "设置轮播图是否可见")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bannerId", value = "轮播图数据ID", example = "1"),
            @ApiImplicitParam(name = "isView", value = "是否可见", example = "1")
    })
    @GetMapping("viewBanner")
    @SystemLog(description = "设置轮播图是否可见", type = UserLog.USER_LOG_UPDATE)
    public Result viewBanner(Integer bannerId, Integer isView, HttpServletRequest request) {
        String logMsg = "调用设置轮播图是否可见接口---viewBanner()---，";
        log.debug(logMsg + "上传参数：{bannerId=" + bannerId + ",isView=" + isView + "}");
        Result result;
        try {
            result = Result.genSuccessResult(bannerService.viewBanner(bannerId, isView, getCurrentUser(request).getId()));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除轮播图信息")
    @ApiImplicitParam(name = "bannerId", value = "轮播图ID")
    @DeleteMapping("deleteBanner/{bannerId}")
    @SystemLog(description = "删除轮播图信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteBanner(@PathVariable(value = "bannerId") Integer bannerId, HttpServletRequest request) {
        String logMsg = "调用删除轮播图接口---deleteBanner()---，";
        log.debug(logMsg + "上传参数：" + bannerId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = bannerService.deleteBanner(bannerId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除轮播图成功");
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
