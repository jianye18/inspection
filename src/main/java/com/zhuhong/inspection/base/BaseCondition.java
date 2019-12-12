package com.zhuhong.inspection.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础搜索参数类
 * @Author: jian.ye
 * @Date: 2019/10/10 13:50
 */
@Data
public class BaseCondition {

    @ApiModelProperty(name = "pageNum", value = "当前页", example = "1")
    private Integer pageNum;
    @ApiModelProperty(name = "pageSize", value = "分页大小", example = "10")
    private Integer pageSize;
    @ApiModelProperty(name = "searchPhrase", value = "搜索条件", example = "admin")
    private String searchPhrase;
    @ApiModelProperty(name = "orderName", value = "排序字段", example = "updateTime")
    private String orderName;
    @ApiModelProperty(name = "orderType", value = "排序类型", example = "desc")
    private String orderType;

}
