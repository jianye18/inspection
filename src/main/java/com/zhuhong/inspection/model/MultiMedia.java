package com.zhuhong.inspection.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 叶剑
 */
@Data
@Table(name = "tb_multi_media")
public class MultiMedia {

    @Id
    @ApiModelProperty(name = "id", hidden = true)
    private Integer id;
    @ApiModelProperty(name = "businessId", value = "业务ID", example = "1")
    @Column(name = "business_id")
    private Integer businessId;
    @ApiModelProperty(name = "businessType", value = "业务数据类型", example = "1")
    @Column(name = "business_type")
    private Integer businessType;
    @ApiModelProperty(name = "category", value = "多媒体类型：1-图片，2-视频", example = "1")
    @Column(name = "category")
    private Integer category;
    @ApiModelProperty(name = "thumbnail", value = "缩略图/视频展示图名称", example = "1")
    @Column(name = "thumbnail")
    private String thumbnail;
    @ApiModelProperty(name = "mediaName", value = "原图片/视频存储名称频", example = "1")
    @Column(name = "media_name")
    private String mediaName;
    @ApiModelProperty(name = "mediaType", value = "数据类型，例：image/jpeg", example = "image/jpeg")
    @Column(name = "media_type")
    private String mediaType;
    @ApiModelProperty(name = "size", value = "大小(KB)", example = "7")
    @Column(name = "size")
    private Long size;

}
