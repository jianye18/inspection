package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Law;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 法规展示类
 * @Author: jian.ye
 * @Date: 2019/10/23 8:39
 */
@Data
public class LawVo extends Law {

    @ApiModelProperty(name = "annexList", value = "附件集合")
    private List<Annex> annexList;
}
