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
    @Column(name = "parent_id")
    private Integer parentId;
    @ApiModelProperty(name = "type", value = "权限类型：1-page,2-link,3-button", example = "3")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "requestUrl", value = "请求url", example = "/user/saveUser")
    @Column(name = "request_url")
    private String requestUrl;
    @ApiModelProperty(name = "requestType", value = "请求方式：POST,GET,PUT,DELETE", example = "POST")
    @Column(name = "request_type")
    private String requestType;
    @ApiModelProperty(name = "iconClass", value = "图标类", example = "ios-add")
    @Column(name = "icon_class")
    private String iconClass;
    @ApiModelProperty(name = "sort", value = "排序字段", example = "1")
    @Column(name = "sort")
    private Integer sort;
    @ApiModelProperty(name = "status", value = "状态：1-启用，2-禁用", example = "1")
    @Column(name = "status")
    private Integer status;

}
