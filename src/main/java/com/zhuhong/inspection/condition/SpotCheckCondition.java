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

    @ApiModelProperty(name = "id", value = "1",hidden = true)
    private Integer id;
    @ApiModelProperty(name = "currentId", value = "1",hidden = true)
    private Integer currentId;
    @ApiModelProperty(name = "productType", value = "产品分类,关联数据常量表", example = "01")
    private String productType;
    @ApiModelProperty(name = "institution", value = "公布机构", example = "国家药品监督管理局")
    private String institution;
    @ApiModelProperty(name = "checkResult", value = "抽检结果：0-不合格，1-合格", example = "1")
    private Integer checkResult;
    @ApiModelProperty(name = "isFake", value = "是否涉嫌假冒：0-否，1-是", example = "0")
    private Integer isFake;

}
