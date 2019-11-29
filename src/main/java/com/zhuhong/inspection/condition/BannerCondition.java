package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class BannerCondition extends BaseCondition {

    @ApiModelProperty(name = "baseUrl", hidden = true)
    private String baseUrl;
    @ApiModelProperty(name = "limit", value = "显示数量", example = "3")
    private Integer limit;
    @ApiModelProperty(name = "isView", value = "是否展示：0-否，1-是", example = "1")
    private Integer isView;

}
