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
@Table(name = "tb_statement")
public class Statement extends BaseModel {

    @ApiModelProperty(name = "content", value = "声明内容", example = "声明内容")
    @Column(name = "content")
    private String  content;

}
