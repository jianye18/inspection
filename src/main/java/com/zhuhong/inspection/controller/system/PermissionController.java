package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.base.ResultCode;
import com.zhuhong.inspection.condition.PermissionCondition;
import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.PermissionService;
import com.zhuhong.inspection.vo.SelectionLabel;
import com.zhuhong.inspection.vo.TreeData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限信息页面交互层
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 10:50
 */
@Api(value = "权限信息controller")
@Slf4j
@RestController
@RequestMapping("/permission/")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "分页获取权限信息")
    @ApiImplicitParam(name = "permissionCondition", value = "查询参数", dataType = "PermissionCondition")
    @PostMapping("/getPermissionPageList")
    public Result<PageInfo<Permission>> getPermissionPageList(@RequestBody PermissionCondition permissionCondition) {
        String logMsg = "调用分页获取权限信息接口---getPermissionPageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(permissionCondition));
        Result result;
        try {
            PageInfo<Permission> list = permissionService.getPermissionPageList(permissionCondition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存权限信息")
    @ApiImplicitParam(name = "permission", value = "权限信息", dataType = "Permission")
    @PostMapping("/savePermission")
    @SystemLog(description = "保存权限信息", type = UserLog.USER_LOG_SAVE)
    public Result savePermission(@RequestBody Permission permission, HttpServletRequest request) {
        String logMsg = "调用保存权限信息接口---saveRole()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(permission));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = permissionService.savePermission(permission, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResult("保存权限信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除权限信息")
    @ApiImplicitParam(name = "pId", value = "权限ID")
    @DeleteMapping("/deletePermission/{pId}")
    @SystemLog(description = "删除权限信息", type = UserLog.USER_LOG_DELETE)
    public Result deletePermission(@PathVariable(value = "pId") Integer pId, HttpServletRequest request) {
        String logMsg = "调用删除权限信息接口---deletePermission()---，";
        log.debug(logMsg + "上传参数：{pId=" + pId + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            int flag = permissionService.deletePermission(pId, getCurrentUser(request).getId());
            if (flag == Constants.DELETE_USER_1) {
                result = Result.genSuccessResult("删除权限信息成功");
            } else if (flag == Constants.DELETE_USER_3) {
                result = Result.genResult(ResultCode.ROLE_PERMISSION_EXIST, "权限被使用，无法删除", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取页面权限集合")
    @GetMapping("/getPermissionWithPageType")
    public Result<List<SelectionLabel>> getPermissionWithPageType() {
        String logMsg = "调用获取页面权限集合接口---getPermissionWithPageType()---，";
        Result result;
        try {
            List<SelectionLabel> list = permissionService.getPermissionWithPageType();
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "获取权限树数据集合")
    @GetMapping("/getPermissionTreeData/{roleId}")
    public Result<List<TreeData>> getPermissionTreeData(@PathVariable(value = "roleId") Integer roleId) {
        String logMsg = "调用获取权限树数据集合接口---getPermissionTreeData()---，";
        log.debug(logMsg + "上传参数：{roleId=" + roleId + "}");
        Result result;
        try {
            List<TreeData> list = permissionService.getPermissionTreeData(roleId);
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
