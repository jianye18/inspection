package com.zhuhong.inspection.controller;

import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共交互层
 * @Author: jian.ye
 * @Date: 2019/10/23 14:09
 */
@Api(value = "公共controller")
@Slf4j
@RestController
@RequestMapping("/common/")
public class CommonController extends BaseController {

    @Value("${upload_path}")
    private String FILE_DIR;

    /**
     * 上传文件
     * @Author: jian.ye
     * @Date: 2019/10/23 14:16
     */
    @ApiOperation(value = "上传文件", notes = "返回上传结果")
    @PostMapping("uploadSingleFile")
    public Result uploadSingleFile(@RequestParam("file") MultipartFile file) {
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try{
            //判断文件是否为空
            if(file.isEmpty()){
                result = Result.genFailResult("文件为空，上传失败!");
            } else {
                //获得文件的字节流
                byte[] bytes=file.getBytes();
                //获得path对象，也即是文件保存的路径对象
                String fileName = file.getOriginalFilename().split(".")[0];
                String suffix = file.getOriginalFilename().split(".")[1];
                String name = fileName + "_" + DateUtil.toDateString(LocalDate.now(), DateUtil.DATE_FORMATER_1) + suffix;
                Path path= Paths.get(FILE_DIR + name);
                //调用静态方法完成将文件写入到目标路径
                Files.write(path,bytes);
                result = Result.genSuccessResult(name);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = Result.genFailResult(e.getMessage());
            log.error("上传文件返回错误信息：", e);
        }
        return result;
    }

}
