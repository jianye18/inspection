package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 权限实体类
 * @Author: jian.ye
 * @Date: 2019/10/10 9:41
 */
@Data
@Table(name = "tb_permission")
public class Permission extends BaseModel {

    @ApiModelProperty(name = "name", value = "权限名", example = "用户新增")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "code", value = "权限编码", example = "user_add")
    @Column(name = "code")
    private String code;
    @ApiModelProperty(name = "description", value = "描述", example = "用户新增权限")
    @Column(name = "description")
    private String description;
    @ApiModelProperty(name = "parentId", value = "上级权限ID", example = "3")
    @Column(name = "parentId")
    private Integer parentId;

}
