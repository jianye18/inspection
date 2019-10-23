package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.SystemDataType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统分类常量展示类
 * @Author: jian.ye
 * @Date: 2019/10/23 9:05
 */
@Data
public class SystemDataTypeVo extends SystemDataType {

    @ApiModelProperty(name = "parentName", value = "父级名称", example = "国际标准")
    private String parentName;

}
