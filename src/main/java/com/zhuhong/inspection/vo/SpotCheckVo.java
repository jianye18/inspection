package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.SpotCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 抽检结果展示类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 15:39
 */
@Data
public class SpotCheckVo extends SpotCheck {

    @ApiModelProperty(name = "productTypeName", value = "产品分类：1-皮肤用化妆品，2-毛发用化妆品，3-指（趾）甲用化妆品，4-口唇用化妆品", example = "皮肤用化妆品")
    private String productTypeName;

}
