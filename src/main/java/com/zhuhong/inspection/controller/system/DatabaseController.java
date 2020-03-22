package com.zhuhong.inspection.controller.system;

import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.model.DatabaseBak;
import com.zhuhong.inspection.service.DatabaseService;
import com.zhuhong.inspection.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @author 叶剑
 */
@Api(value = "数据库备份controller")
@Slf4j
@RestController
@RequestMapping("/database/")
public class DatabaseController extends BaseController {

    @Autowired
    DatabaseService databaseService;

    @ApiOperation(value = "备份数据库")
    @PostMapping("backUp")
    public ResponseEntity<byte[]> backUp(HttpServletRequest request) {
        String logMsg = "调用备份数据库接口---backUp()---，";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            // headers.add("Content-Disposition", String.format("attachment; file=\"%s\"", "数据库备份.sql"));
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            File file = databaseService.backUp(fileDir, getCurrentUser(request).getId());
            return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            return null;
        }
    }

    @ApiOperation(value = "恢复数据库")
    @PostMapping("recover")
    public Result recover(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String logMsg = "调用恢复数据库接口---recover()---，";
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            if (databaseService.recover(file, fileDir, getCurrentUser(request).getId())) {
                result = Result.genSuccessResultMsg("恢复数据库成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "分页获取备份及恢复的记录数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "BaseCondition")
    @PostMapping("getDatabaseBakPageList")
    public Result<ArticleVo> getDatabaseBakPageList(@RequestBody BaseCondition condition) {
        String logMsg = "调用分页获取备份及恢复的记录数据接口---getDatabaseBakPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<DatabaseBak> list = databaseService.getDatabaseBakPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
