package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
 * 标准数据实体类
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:15
 */
@Data
public class Criterion extends BaseModel {

    @ApiModelProperty(name = "name", value = "标准名称", example = "标准名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "type", value = "分类", example = "1")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "status", value = "状态", example = "1")
    @Column(name = "status")
    private Integer status;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "发布单位")
    @Column(name = "publish_unit")
    private String publishUnit;
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
    @Column(name = "annexName")
    private String annexName;
    @ApiModelProperty(name = "annexPath", value = "附件路径", example = "附件路径")
    @Column(name = "annexPath")
    private String annexPath;

}
