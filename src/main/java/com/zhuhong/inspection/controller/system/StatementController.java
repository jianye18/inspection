package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.StatementCondition;
import com.zhuhong.inspection.model.Statement;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.StatementService;
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
@Api(value = "负责声明数据controller")
@Slf4j
@RestController
@RequestMapping("/statement")
public class StatementController extends BaseController {

    @Autowired
    private StatementService statementService;

    @ApiOperation(value = "分页获取负责声明信息")
    @ApiImplicitParam(name = "condition", value = "查询参数", dataType = "StatementCondition")
    @PostMapping("/getStatementPageList")
    public Result<Statement> getStatementPageList(@RequestBody StatementCondition condition) {
        String logMsg = "调用分页获取负责声明信息接口---getStatementPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<Statement> list = statementService.getStatementPageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存负责声明信息")
    @ApiImplicitParam(name = "statement", value = "负责声明信息", dataType = "Statement")
    @PostMapping("/saveStatement")
    @SystemLog(description = "保存负责声明信息", type = UserLog.USER_LOG_SAVE)
    public Result saveStatement(@RequestBody Statement statement, HttpServletRequest request) {
        String logMsg = "调用保存负责声明信息接口---saveStatement()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(statement));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = statementService.saveStatement(statement, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存负责声明成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除负责声明信息")
    @ApiImplicitParam(name = "statementId", value = "负责声明ID", example = "1")
    @DeleteMapping("/deleteStatement/{statementId}")
    @SystemLog(description = "删除负责声明信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteStatement(@PathVariable(value = "statementId") Integer statementId, HttpServletRequest request) {
        String logMsg = "调用删除负责声明信息接口---deleteStatement()---，";
        log.debug(logMsg + "上传参数：statementId=" + statementId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = statementService.deleteStatement(statementId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除负责声明成功");
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
