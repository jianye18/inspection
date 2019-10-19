package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 标准数据搜索条件类
 * @Author: jian.ye
 * @Date: 2019/10/19 12:02
 */
@Data
public class CriterionCondition extends BaseCondition {

    @ApiModelProperty(name = "category", value = "一级分类", example = "1")
    private Integer category;
    @ApiModelProperty(name = "type", value = "二级分类", example = "1")
    private Integer type;
    @ApiModelProperty(name = "status", value = "status", example = "1")
    private Integer status;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "发布单位")
    private String publishUnit;
    @ApiModelProperty(name = "startDate", value = "开始日期", example = "2019-01-01")
    private String startDate;
    @ApiModelProperty(name = "endDate", value = "结束日期", example = "2019-10-10")
    private String endDate;

    private String categoryCode = Constants.CRITERION_TYPE_CODE_1;
    private String typeCode = Constants.CRITERION_TYPE_CODE_2;
    private String publishUnitCode = Constants.CRITERION_TYPE_CODE_3;

}
