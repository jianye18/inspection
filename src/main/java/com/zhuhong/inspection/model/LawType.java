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
@Table(name = "tb_system_data")
public class LawType extends BaseModel {

    @ApiModelProperty(name = "code", value = "编码,关联数据常量表", example = "1574147128829")
    @Column(name = "code")
    private String code;
    @ApiModelProperty(name = "name", value = "名称", example = "名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "value", value = "值", example = "WZLX")
    @Column(name = "value")
    private Integer value;
    @ApiModelProperty(name = "sort", value = "排序", example = "1")
    @Column(name = "sort")
    private Integer sort;
    @ApiModelProperty(name = "remark", value = "字典说明", example = "字典说明")
    @Column(name = "remark")
    private String remark;

}
