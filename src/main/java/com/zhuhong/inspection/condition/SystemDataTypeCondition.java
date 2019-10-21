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
public class SystemDataTypeCondition extends BaseCondition {

    @ApiModelProperty(name = "type", value = "类型：1-抽检、2-标准", example = "1")
    private Integer type;

}
