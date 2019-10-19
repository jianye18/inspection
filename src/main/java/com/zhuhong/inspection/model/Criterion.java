package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * 标准数据实体类
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:15
 */
@Data
@Table(name = "tb_criterion")
public class Criterion extends BaseModel {

    @ApiModelProperty(name = "name", value = "标准名称", example = "标准名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "category", value = "一级分类", example = "1")
    @Column(name = "category")
    private Integer category;
    @ApiModelProperty(name = "type", value = "二级分类", example = "1")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "status", value = "状态", example = "1")
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "1")
    @Column(name = "publish_unit")
    private Integer publishUnit;
    @ApiModelProperty(name = "publishDate", value = "发布日期", example = "2019-10-15")
    @Column(name = "publish_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date publishDate;
    @ApiModelProperty(name = "implementDate", value = "实施日期", example = "2019-10-15")
    @Column(name = "implement_date")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date implementDate;
    @ApiModelProperty(name = "summary", value = "摘要", example = "摘要")
    @Column(name = "summary")
    private String summary;
    @ApiModelProperty(name = "annexName", value = "附件名称", example = "附件名称")
    @Column(name = "annex_name")
    private String annexName;
    @ApiModelProperty(name = "annexPath", value = "附件路径", example = "附件路径")
    @Column(name = "annex_path")
    private String annexPath;

}
