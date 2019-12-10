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
@Table(name = "tb_links")
public class Link extends BaseModel {

    @ApiModelProperty(name = "name", value = "链接名称", example = "链接名称")
    @Column(name = "name")
    private String  name;
    @ApiModelProperty(name = "path", value = "链接地址", example = "链接地址")
    @Column(name = "path")
    private String  path;
    @ApiModelProperty(name = "sort", value = "排序", example = "1")
    @Column(name = "sort")
    private Integer  sort;
    @ApiModelProperty(name = "isView", value = "是否展示：0-否，1-是", example = "1")
    @Column(name = "is_view")
    private Integer  isView;
    @ApiModelProperty(name = "remark", value = "备注", example = "备注")
    @Column(name = "remark")
    private String  remark;

}
