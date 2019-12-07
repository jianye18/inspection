package com.zhuhong.inspection.controller;

import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * 公共交互层
 * @Author: jian.ye
 * @Date: 2019/10/23 14:09
 */
@Api(value = "公共controller")
@Slf4j
@RestController
@RequestMapping("/api/common/")
public class CommonController extends BaseController {

    @Value("${upload_path}")
    private String fileDir;

    @ApiOperation(value = "上传文件", notes = "返回上传结果")
    @PostMapping("uploadSingleFile")
    public Result uploadSingleFile(@RequestParam("file") MultipartFile file) {
        Result result;
        log.debug("上传文件名：" + file.getOriginalFilename());
        try{
            //判断文件是否为空
            if(file.isEmpty()){
                result = Result.genFailResult("文件为空，上传失败!");
            } else {
                //获得path对象，也即是文件保存的路径对象
                /*String[] arr = file.getOriginalFilename().split("\\.");
                String fileName = arr[0];
                String suffix = arr[1];
                String name = fileName + "_" + DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER_1) + "." + suffix;*/
                String name = file.getOriginalFilename();
                if (FileUtil.uploadFile(file, fileDir + "docs/", name)) {
                    result = Result.genSuccessResult(name);
                } else {
                    result = Result.genFailResult("上传失败!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result = Result.genFailResult(e.getMessage());
            log.error("上传文件返回错误信息：", e);
        }
        return result;
    }

    @ApiOperation(value = "删除文件", notes = "返回删除结果")
    @ApiImplicitParam(name = "fileName", value = "文件名")
    @DeleteMapping("deleteFile/{fileName}")
    @SystemLog(description = "删除文件", type = UserLog.USER_LOG_DELETE)
    public Result deleteFile(@PathVariable(value = "fileName") String fileName) {
        Result result;
        log.debug("删除文件的文件名：" + fileName);
        try{
            //判断文件是否为空
            File file = new File(fileDir + "docs/" + fileName);
            file.delete();
            result = Result.genSuccessResultMsg("删除文件成功！");
        }catch (Exception e){
            e.printStackTrace();
            result = Result.genFailResult(e.getMessage());
            log.error("删除文件返回错误信息：", e);
        }
        return result;
    }

}
