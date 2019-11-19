package com.zhuhong.inspection.dto;

import com.zhuhong.inspection.model.Criterion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class CriterionDto extends Criterion {

    @ApiModelProperty(name = "annexs", value = "附件集合", example = "[{\"111_20191023.txt\", \"112_20191023.txt\"}]")
    private String annexs;

}
