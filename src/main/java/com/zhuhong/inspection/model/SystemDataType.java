package com.zhuhong.inspection.model;

import com.zhuhong.inspection.base.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 系统分类数据实体类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 16:40
 */
@Data
@Table(name = "tb_system_data_type")
public class SystemDataType extends BaseModel {

    @ApiModelProperty(name = "type", value = "类型：1-抽检、2-标准", example = "1")
    @Column(name = "type")
    private Integer type;
    @ApiModelProperty(name = "value", value = "值", example = "1")
    @Column(name = "value")
    private Integer value;
    @ApiModelProperty(name = "code", value = "编码", example = "")
    @Column(name = "code")
    private String code;
    @ApiModelProperty(name = "name", value = "名称", example = "皮肤用化妆品")
    @Column(name = "name")
    private String name;
    @ApiModelProperty(name = "description", value = "描述", example = "产品分类")
    @Column(name = "description")
    private String description;

}
