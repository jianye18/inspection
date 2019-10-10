package com.zhuhong.inspection.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 角色权限关联表
 * @Author: jian.ye
 * @Date: 2019/10/10 11:45
 */
@Data
public class RolePermission {

    @ApiModelProperty(name = "id", value = "主键ID", hidden = true)
    @Id
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(name = "roleId", value = "角色ID", hidden = true)
    @Column(name = "role_id")
    private Integer roleId;
    @ApiModelProperty(name = "permissionId", value = "权限ID", hidden = true)
    @Column(name = "permission_id")
    private Integer permissionId;

}
