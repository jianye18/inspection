package com.zhuhong.inspection.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.FlightCheck;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 叶剑
 */
@Data
public class FlightCheckDto extends FlightCheck {

    @ApiModelProperty(name = "annexList", value = "附件集合", example = "[{\"name\":\"111_20191023.txt\", \"name\":\"112_20191023.txt\"}]")
    @JSONField(serialize = false)
    private List<Annex> annexList;

}
