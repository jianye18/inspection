package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.FlightCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 飞检数据展示类
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:41
 */
@Data
public class FlightCheckVo extends FlightCheck {

    @ApiModelProperty(name = "precautionsName", value = "处理措施", example = "处理措施")
    private String precautionsName;
    @ApiModelProperty(name = "typeName", value = "飞检类型")
    private String typeName;

}
