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
public class SystemData extends BaseModel {

    @ApiModelProperty(name = "code", value = "编码", example = "1574147128829")
    @Column(name = "code")
    private String code;
    @ApiModelProperty(name = "name", value = "名称", example = "名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "typeCode", value = "父级编码", example = "WZLX")
    @Column(name = "type_code")
    private String typeCode;
    @ApiModelProperty(name = "sort", value = "排序", example = "1")
    @Column(name = "sort")
    private Integer sort;
    @ApiModelProperty(name = "remark", value = "字典说明", example = "字典说明")
    @Column(name = "remark")
    private String remark;

}
