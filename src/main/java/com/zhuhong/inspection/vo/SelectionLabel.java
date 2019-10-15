package com.zhuhong.inspection.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 下拉框展示类
 *
 * @Author: jian.ye
 * @Date: 2019/10/13 18:54
 */
@Data
public class SelectionLabel {
    @ApiModelProperty(name = "value", value = "下拉框-值", example = "1")
    private String value;
    @ApiModelProperty(name = "label", value = "下拉框-文本", example = "用户管理")
    private String label;

}
