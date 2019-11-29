package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author 叶剑
 */
@Data
@Table(name = "tb_banner")
public class Banner extends BaseModel {

    @ApiModelProperty(name = "name", value = "轮播图名称", example = "123.png")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "path", value = "轮播图地址", example = "/images/banner/123.png")
    @Column(name = "path")
    private String path;
    @ApiModelProperty(name = "size", value = "轮播图大小", example = "1024")
    @Column(name = "size")
    private Long size;
    @ApiModelProperty(name = "type", value = "轮播图类型", example = "images/png")
    @Column(name = "type")
    private String type;
    @ApiModelProperty(name = "isView", value = "是否展示：0-否，1-是", example = "1")
    @Column(name = "is_view")
    private Integer isView;
    @ApiModelProperty(name = "remark", value = "备注", example = "备注")
    @Column(name = "remark")
    private String remark;

}
