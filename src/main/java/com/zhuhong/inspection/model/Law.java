package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 法规实体类
 *
 * @Author: jian.ye
 * @Date: 2019/10/22 23:08
 */
@Data
@Table(name = "tb_law")
public class Law extends BaseModel {
    @ApiModelProperty(name = "name", value = "法规名称", example = "法规名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "codeNumber", value = "法规文号", example = "法规文号")
    @Column(name = "code_number")
    private String codeNumber;
    @ApiModelProperty(name = "category", value = "一级分类", example = "1")
    @Column(name = "category")
    private String category;
    @ApiModelProperty(name = "type", value = "二级分类", example = "1")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "status", value = "状态", example = "1")
    @Column(name = "status")
    private String status;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "1")
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
    @ApiModelProperty(name = "process", value = "环节", example = "1")
    @Column(name = "process")
    private Integer process;
    @ApiModelProperty(name = "content", value = "法规内容", example = "法规内容")
    @Column(name = "content")
    private String content;
    @ApiModelProperty(name = "source", value = "来源", example = "1")
    @Column(name = "source")
    private String source;

}
