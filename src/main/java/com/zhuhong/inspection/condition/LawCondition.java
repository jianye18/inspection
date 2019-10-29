package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import com.zhuhong.inspection.base.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 法规搜索条件类
 * @Author: jian.ye
 * @Date: 2019/10/23 8:41
 */
@Data
public class LawCondition extends BaseCondition {

    @ApiModelProperty(name = "category", value = "一级分类", example = "1")
    private Integer category;
    @ApiModelProperty(name = "type", value = "二级分类", example = "1")
    private Integer type;
    @ApiModelProperty(name = "status", value = "状态", example = "1")
    private Integer status;
    @ApiModelProperty(name = "process", value = "环节", example = "1")
    private Integer process;
    @ApiModelProperty(name = "source", value = "来源", example = "1")
    private Integer source;
    @ApiModelProperty(name = "publishUnit", value = "发布单位", example = "1")
    private Integer publishUnit;
    @ApiModelProperty(name = "startDate", value = "开始日期", example = "2019-01-01")
    private String startDate;
    @ApiModelProperty(name = "endDate", value = "结束日期", example = "2019-10-10")
    private String endDate;

    private String categoryCode = Constants.LAW_CATEGORY;
    private String code = Constants.LAW_TYPE;
    private String publishUnitCode = Constants.LAW_PUBLISH_UNIT;
    private String sourceCode = Constants.LAW_SOURCE;

}
