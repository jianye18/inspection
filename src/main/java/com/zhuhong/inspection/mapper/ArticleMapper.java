package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 叶剑
 */
@Repository
public interface ArticleMapper extends Mapper<Article> {

    /**
     * 根据条件获取文章集合
     * @param condition
     * @return List<ArticleVo>
     * @Author: jian.ye
     * @Date: 2019/11/19 14:39
     */
    List<ArticleVo> getArticleListByCondition(ArticleCondition condition);

    /**
     * 根据ID获取文章信息
     * @param articleId
     * @return ArticleVo
     * @Author: jian.ye
     * @Date: 2019/11/19 16:22
     */
    ArticleVo getArticleById(@Param(value = "articleId") Integer articleId);

}
