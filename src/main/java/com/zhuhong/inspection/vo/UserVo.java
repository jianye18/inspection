package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.Permission;
import com.zhuhong.inspection.model.Role;
import com.zhuhong.inspection.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户展示类
 * @Author: jian.ye
 * @Date: 2019/10/10 9:44
 */
@Data
public class UserVo extends User {

    private static final long serialVersionUID = 2631590509760908280L;

    @ApiModelProperty(name = "roleList", value = "角色集合", hidden = true)
    private List<Role> roleList;
    @ApiModelProperty(name = "permissionList", value = "权限集合", hidden = true)
    private List<Permission> permissionList;

}
