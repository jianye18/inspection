package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.LinkCondition;
import com.zhuhong.inspection.model.Link;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 叶剑
 */
@Api(value = "友情链接数据controller")
@Slf4j
@RestController
@RequestMapping("/link")
public class LinkController extends BaseController {

    @Autowired
    private LinkService linkService;

    @ApiOperation(value = "分页获取友情链接信息")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "LinkCondition")
    @PostMapping("/getLinkPageList")
    public Result<Link> getLinkPageList(@RequestBody LinkCondition condition) {
        String logMsg = "调用分页获取友情链接信息接口---getLinkPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<Link> list = linkService.getLinkPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存友情链接信息")
    @ApiImplicitParam(name = "link", value = "友情链接信息", dataType = "Link")
    @PostMapping("/saveLink")
    @SystemLog(description = "保存友情链接信息", type = UserLog.USER_LOG_SAVE)
    public Result saveLink(@RequestBody Link link, HttpServletRequest request) {
        String logMsg = "调用保存友情链接信息接口---saveLink()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(link));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = linkService.saveLink(link, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存友情链接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除友情链接信息")
    @ApiImplicitParam(name = "linkId", value = "友情链接ID", example = "1")
    @DeleteMapping("/deleteLink/{linkId}")
    @SystemLog(description = "删除友情链接信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteLink(@PathVariable(value = "linkId") Integer linkId, HttpServletRequest request) {
        String logMsg = "调用删除友情链接信息接口---deleteLink()---，";
        log.debug(logMsg + "上传参数：linkId=" + linkId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = linkService.deleteLink(linkId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除友情链接成功");
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
