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
    @ApiModelProperty(name = "description", value = "角色描述", example = "可以任何操作")
    @Column(name = "description")
    private String description;

}
