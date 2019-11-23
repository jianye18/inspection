package com.zhuhong.inspection.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author 叶剑
 */
@Data
@Table(name = "tb_article")
public class Article extends BaseModel {

    @ApiModelProperty(name = "title", value = "文章标题", example = "文章标题")
    @Column(name = "title")
    private String title;
    @ApiModelProperty(name = "author", value = "文章作者", example = "文章作者")
    @Column(name = "author")
    private String author;
    @ApiModelProperty(name = "content", value = "文章内容", example = "文章内容")
    @Column(name = "content")
    private String content;
    @ApiModelProperty(name = "typeCode", value = "文章类型", example = "1")
    @Column(name = "type_code")
    private String typeCode;
    @ApiModelProperty(name = "subject", value = "文章关键词", example = "[\"关键词1\",\"关键词2\"]")
    @Column(name = "subject")
    private String subject;
    @ApiModelProperty(name = "isPublish", value = "是否发布：0-否，1-是", hidden = true)
    @Column(name = "is_publish")
    private Integer isPublish;
    @ApiModelProperty(name = "publishTime", value = "发布时间", example = "2019-11-19 12:12:12")
    @Column(name = "publish_time")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
    @ApiModelProperty(name = "readCount", value = "阅读数量", hidden = true)
    @Column(name = "readCount")
    private Integer readCount;
    @ApiModelProperty(name = "links", value = "相关链接", example = "[\"链接1\",\"链接2\"]")
    @Column(name = "links")
    private String links;

}
