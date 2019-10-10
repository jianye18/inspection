package com.zhuhong.inspection.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 用户角色关联表
 * @Author: jian.ye
 * @Date: 2019/10/10 11:44
 */
@Data
public class UserRole {

    @ApiModelProperty(name = "id", value = "主键ID", hidden = true)
    @Id
    @Column(name = "id")
    private Integer id;
    @ApiModelProperty(name = "userId", value = "用户ID", hidden = true)
    @Column(name = "user_id")
    private Integer userId;
    @ApiModelProperty(name = "roleId", value = "角色ID", hidden = true)
    @Column(name = "role_id")
    private Integer roleId;

}
