package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类数据查询条件类
 * @Author: jian.ye
 * @Date: 2019/10/21 15:37
 */
@Data
public class SystemDataCondition extends BaseCondition {

    @ApiModelProperty(name = "typeCode", value = "系统类型", example = "1")
    private String typeCode;

}
