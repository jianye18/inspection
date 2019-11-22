package com.zhuhong.inspection.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Criterion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 叶剑
 */
@Data
public class CriterionDto extends Criterion {

    @ApiModelProperty(name = "annexList", value = "附件集合", example = "[{\"111_20191023.txt\", \"112_20191023.txt\"}]")
    @JSONField(serialize = false)
    private List<Annex> annexList;

}
