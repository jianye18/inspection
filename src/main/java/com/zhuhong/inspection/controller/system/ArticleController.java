package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.dto.ArticleDto;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.ArticleService;
import com.zhuhong.inspection.utils.FileUtil;
import com.zhuhong.inspection.vo.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 叶剑
 */
@Api(value = "文章数据controller")
@Slf4j
@RestController
@RequestMapping("/api/article/")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "分页获取文章数据")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "ArticleCondition")
    @PostMapping("getArticlePageList")
    public Result<ArticleVo> getArticlePageList(@RequestBody ArticleCondition condition) {
        String logMsg = "调用分页获取文章数据接口---getArticlePageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<ArticleVo> list = articleService.getArticlePageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存文章数据")
    @ApiImplicitParam(name = "articleDto", value = "文章数据", dataType = "ArticleDto")
    @PostMapping("saveArticle")
    @SystemLog(description = "保存文章数据", type = UserLog.USER_LOG_SAVE)
    public Result saveArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        String logMsg = "调用保存文章数据接口---saveArticle()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(articleDto));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = articleService.saveArticle(articleDto, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存文章数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "根据ID获取文章数据")
    @ApiImplicitParam(name = "articleId", value = "文章数据ID", example = "1")
    @GetMapping("getArticleById/{articleId}")
    public Result<ArticleVo> getArticleById(@PathVariable(value = "articleId") Integer articleId) {
        String logMsg = "调用根据ID获取文章数据接口---getArticleById()---，";
        log.debug(logMsg + "上传参数：" + articleId);
        Result result;
        try {
            result = Result.genSuccessResult(articleService.getArticleById(articleId));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "发布或取消发布文章")
    @ApiImplicitParam(name = "condition", value = "文章数据", dataType = "ArticleCondition")
    @PostMapping("publishArticle")
    @SystemLog(description = "发布或取消发布文章", type = UserLog.USER_LOG_UPDATE)
    public Result publishArticle(@RequestBody ArticleCondition condition, HttpServletRequest request) {
        String logMsg = "调用发布或取消发布文章接口---publishArticle()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = articleService.publishArticle(condition, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("发布或取消发布成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除文章信息")
    @ApiImplicitParam(name = "articleId", value = "文章ID")
    @DeleteMapping("deleteArticle/{articleId}")
    @SystemLog(description = "删除文章信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteArticle(@PathVariable(value = "articleId") Integer articleId, HttpServletRequest request) {
        String logMsg = "调用删除文章接口---deleteArticle()---，";
        log.debug(logMsg + "上传参数：" + articleId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = articleService.deleteArticle(articleId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除文章成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "增加文章的阅读量")
    @ApiImplicitParam(name = "articleId", value = "文章ID")
    @GetMapping("addArticleReadCount/{articleId}")
    public Result addArticleReadCount(@PathVariable(value = "articleId") Integer articleId) {
        String logMsg = "调用增加文章的阅读量接口---addArticleReadCount()---，";
        log.debug(logMsg + "上传参数：" + articleId);
        Result result;
        try {
            articleService.addArticleReadCount(articleId);
            result = Result.genSuccessResult();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

}
