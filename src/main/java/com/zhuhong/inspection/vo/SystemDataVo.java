package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.SystemData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class SystemDataVo extends SystemData {

    @ApiModelProperty(name = "typeName", value = "类型名称", example = "类型名称")
    private String typeName;

}
