package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class UserLogCondition extends BaseCondition {

    @ApiModelProperty(name = "type", value = "日志操作类型", example = "保存")
    private String type;

}
