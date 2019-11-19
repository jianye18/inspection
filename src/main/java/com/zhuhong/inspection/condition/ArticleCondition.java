package com.zhuhong.inspection.condition;

import com.zhuhong.inspection.base.BaseCondition;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class ArticleCondition extends BaseCondition {

    @ApiModelProperty(name = "articleId", value = "1",hidden = true)
    private Integer articleId;
    @ApiModelProperty(name = "typeCode", value = "文章类型", example = "1")
    private String typeCode;
    @ApiModelProperty(name = "isPublish", value = "是否发布：0-否，1-是", hidden = true)
    private Integer isPublish;

}
