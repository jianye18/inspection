package com.zhuhong.inspection.controller.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.aop.SystemLog;
import com.zhuhong.inspection.base.BaseController;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.base.Result;
import com.zhuhong.inspection.base.ResultCode;
import com.zhuhong.inspection.condition.RoleCondition;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.UserLog;
import com.zhuhong.inspection.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色信息页面交互层
 * @Author: jian.ye
 * @Date: 2019/10/11 20:47
 */
@Api(value = "角色信息controller")
@Slf4j
@RestController
@RequestMapping("/role/")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "分页获取角色信息")
    @ApiImplicitParam(name = "roleCondition", value = "查询参数", dataType = "RoleCondition")
    @PostMapping("/getRolePageList")
    public Result<PageInfo<Role>> getRolePageList(@RequestBody RoleCondition condition) {
        String logMsg = "调用分页获取角色信息接口---getRolePageList()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(condition));
        Result result;
        try {
            PageInfo<Role> list = roleService.getRolePageList(condition);
            result = Result.genSuccessResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "保存角色信息")
    @ApiImplicitParam(name = "role", value = "角色信息", dataType = "Role")
    @PostMapping("/saveRole")
    @SystemLog(description = "保存角色信息", type = UserLog.USER_LOG_SAVE)
    public Result saveRole(@RequestBody Role role, HttpServletRequest request) {
        String logMsg = "调用保存角色信息接口---saveRole()---，";
        log.debug(logMsg + "上传参数：" + JSON.toJSONString(role));
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            boolean flag = roleService.saveRole(role, getCurrentUser(request).getId());
            if (flag) {
                result = Result.genSuccessResult("保存角色信息成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "删除角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID")
    @DeleteMapping("/deleteRole/{roleId}")
    @SystemLog(description = "删除角色信息", type = UserLog.USER_LOG_DELETE)
    public Result deleteRole(@PathVariable(value = "roleId") Integer roleId, HttpServletRequest request) {
        String logMsg = "调用删除角色信息接口---deleteRole()---，";
        log.debug(logMsg + "上传参数：{roleId=" + roleId + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            int flag = roleService.deleteRole(roleId, getCurrentUser(request).getId());
            if (flag == Constants.DELETE_USER_1) {
                result = Result.genSuccessResult("删除角色信息成功");
            } else if (flag == Constants.DELETE_USER_3) {
                result = Result.genResult(ResultCode.USER_ROLE_EXIST, "存在用户绑定该角色，无法删除", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    @ApiOperation(value = "角色绑定权限信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleId", value = "角色ID"),
        @ApiImplicitParam(name = "permissionIds", value = "多个权限ID拼接的字符串啊")
    })
    @PostMapping("/saveRolePermission")
    @SystemLog(description = "角色绑定权限", type = UserLog.USER_LOG_UPDATE)
    public Result saveRolePermission(Integer roleId, String permissionIds) {
        String logMsg = "调用角色绑定权限信息角色绑定权限信息接口---saveRolePermission()---，";
        log.debug(logMsg + "上传参数：{roleId=" + roleId + ",permissionIds=" + permissionIds + "}");
        Result result = Result.genFailResult(FAIL_MESSAGE);
        try {
            if (roleService.saveRolePermission(roleId, permissionIds)) {
                result = Result.genSuccessResultMsg("绑定权限成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(logMsg + "返回错误信息：", e);
            result = Result.genFailResult(e.getMessage());
        }
        log.debug(logMsg + "返回结果信息：" + result.toString());
        return result;
    }

    /**
     * 获取所有角色信息
     *
     * @Author: jian.ye
     * @Date: 2019/10/14 22:16
     */
    @ApiOperation(value = "获取所有角色信息", notes = "返回所有角色信息")
    @GetMapping("/getAllRoleList")
    public Result<List<Role>> getAllRoleList() {
        String logMsg = "调用分页获取角色信息接口---getAllRoleList()---，";
        Result result;
        try {
            List<Role> list = roleService.getAllRoleList();
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
