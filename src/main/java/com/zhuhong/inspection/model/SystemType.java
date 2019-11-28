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
@Table(name = "tb_system_type")
public class SystemType extends BaseModel {

    @ApiModelProperty(name = "code", value = "编码", example = "WZLX")
    @Column(name = "code")
    private String code;
    @ApiModelProperty(name = "name", value = "名称", example = "名称")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "type", value = "类型：SC-抽检、CC-标准、LW-法规、FC-飞检、AC-文章", hidden = true)
    @Column(name = "type")
    private String type;
    @ApiModelProperty(name = "remark", value = "字典说明", example = "字典说明")
    @Column(name = "remark")
    private String remark;

}
