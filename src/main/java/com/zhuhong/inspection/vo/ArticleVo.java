package com.zhuhong.inspection.vo;

import com.zhuhong.inspection.model.Article;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 叶剑
 */
@Data
public class ArticleVo extends Article {

    @ApiModelProperty(name = "typeName", value = "文章类型名称", example = "文章类型名称")
    private String typeName;

}
