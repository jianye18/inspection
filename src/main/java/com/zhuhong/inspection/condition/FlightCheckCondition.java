package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 飞检数据查询条件类
 *
 * @Author: jian.ye
 * @Date: 2019/11/9 19:46
 */
@Data
public class FlightCheckCondition extends BaseCondition {

    @ApiModelProperty(name = "id", value = "1",hidden = true)
    private Integer id;
    @ApiModelProperty(name = "currentId", value = "1",hidden = true)
    private Integer currentId;
    @ApiModelProperty(name = "precautions", value = "处理措施：1-无，2-未明示，3-责令整改，4-限期整改，5-停产整改", example = "3")
    private Integer precautions;

    private String code = Constants.FLIGHT_CHECK;
    private String param = Constants.PRECAUTIONS;

}
