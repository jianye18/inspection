package com.zhuhong.inspection.controller.system;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.model.Law;
import com.zhuhong.inspection.service.LawService;
import com.zhuhong.inspection.vo.LawVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 法规数据交互层
 * @Author: jian.ye
 * @Date: 2019/10/23 14:09
 */
@Api(value = "法规数据controller")
@Slf4j
@RestController
@RequestMapping("/law/")
public class LawController extends BaseController {

    @Autowired
    private LawService lawService;

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
     * 保存法规数据
     * @Author: jian.ye
     * @Date: 2019/10/19 15:23
     */
    @ApiOperation(value = "保存法规数据", notes = "保存法规数据")
    @ApiImplicitParam(name = "law", value = "法规数据", dataType = "Law")
    @PostMapping("/saveLaw")
    public Result saveLaw(@RequestBody Law law, HttpServletRequest request) {
        String LOG_MSG = "调用保存法规数据接口---saveLaw()---，";
        log.debug(LOG_MSG + "上传参数：" + law.toString());
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = lawService.saveLaw(law, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("保存法规数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 删除法规数据
     * @Author: jian.ye
     * @Date: 2019/10/19 16:46
     */
    @ApiOperation(value = "删除法规数据", notes = "删除法规数据")
    @ApiImplicitParam(name = "lawId", value = "法规数据ID", example = "1")
    @DeleteMapping("/deleteLaw/{lawId}")
    public Result deleteLaw(@PathVariable(value = "lawId", required = true) Integer lawId, HttpServletRequest request) {
        String LOG_MSG = "调用删除法规数据接口---deleteLaw()---，";
        log.debug(LOG_MSG + "上传参数：criterionId=" + lawId);
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = lawService.deleteLaw(lawId, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResultMsg("删除法规数据成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(LOG_MSG + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(LOG_MSG + "返回结果信息：" + result.toString());
        return result;
    }

}
