package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 角色实体类
 * @Author: jian.ye
 * @Date: 2019/10/10 9:14
 */
@Data
@Table(name = "tb_role")
public class Role extends BaseModel {

    @ApiModelProperty(name = "roleName", value = "角色名", example = "超级管理员")
    @Column(name = "role_name")
    private String roleName;
    @ApiModelProperty(name = "roleCode", value = "角色编码", example = "ssdd23rasndfasn2")
    @Column(name = "role_code")
    private String roleCode;
    @ApiModelProperty(name = "description", value = "角色描述", example = "可以任何操作")
    @Column(name = "description")
    private String description;
    @ApiModelProperty(name = "roleStatus", value = "状态：1-启用，2-禁用", example = "1")
    @Column(name = "role_status")
    private Integer roleStatus;

}
