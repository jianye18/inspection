package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 抽检查询条件类
 *
 * @Author: jian.ye
 * @Date: 2019/10/16 15:36
 */
@Data
public class SpotCheckCondition extends BaseCondition {

    @ApiModelProperty(name = "productType", value = "产品分类：1-皮肤用化妆品，2-毛发用化妆品，3-指（趾）甲用化妆品，4-口唇用化妆品", example = "1")
    private Integer productType;
    @ApiModelProperty(name = "institution", value = "公布机构", example = "国家药品监督管理局")
    private String institution;
    @ApiModelProperty(name = "checkResult", value = "抽检结果：0-不合格，1-合格", example = "1")
    private Integer checkResult;
    @ApiModelProperty(name = "isFake", value = "是否涉嫌假冒：0-否，1-是", example = "0")
    private Integer isFake;

    private String code = Constants.SPOT_CHECK;
    private String param = Constants.PRODUCT_TYPE;

}
