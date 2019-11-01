package com.zhuhong.inspection.controller.show;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.condition.LawCondition;
import com.zhuhong.inspection.condition.SpotCheckCondition;
import com.zhuhong.inspection.service.CriterionService;
import com.zhuhong.inspection.service.LawService;
import com.zhuhong.inspection.service.SpotCheckService;
import com.zhuhong.inspection.vo.CriterionVo;
import com.zhuhong.inspection.vo.LawVo;
import com.zhuhong.inspection.vo.SpotCheckVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
