package com.zhuhong.inspection.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Criterion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 标准数据展示类
 * @Author: jian.ye
 * @Date: 2019/10/19 16:04
 */
@Data
public class CriterionVo extends Criterion {

    @ApiModelProperty(name = "categoryName", value = "一级分类名称", example = "国际标准")
    private String categoryName;
    @ApiModelProperty(name = "typeName", value = "二级分类名称", example = "基础标准")
    private String typeName;
    @ApiModelProperty(name = "publishUnitName", value = "发布单位名称", example = "单位1")
    private String publishUnitName;
    @ApiModelProperty(name = "statusName", value = "状态名称", example = "现行有效")
    private String statusName;
    @ApiModelProperty(name = "annexList", value = "附件集合")
    private List<Annex> annexList;

    @ApiModelProperty(name = "title", value = "App列表展示名称字段")
    private String title;
    @ApiModelProperty(name = "publishTime", value = "App列表展示时间字段", example = "2019-11-19")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date publishTime;
}
