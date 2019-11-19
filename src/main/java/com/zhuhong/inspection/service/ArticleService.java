package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.vo.ArticleVo;

/**
 * @author 叶剑
 */
public interface ArticleService {

    /**
     * 保存文章数据
     * @param article
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/19 14:15
     */
    boolean saveArticle(Article article, Integer currentUserId);

    /**
     * 根据id获取文章信息
     * @param articleId
     * @return Article
     * @Author: jian.ye
     * @Date: 2019/11/19 14:25
     */
    ArticleVo getArticleById(Integer articleId);

    /**
     * 分页获取文章列表数据
     * @param condition
     * @return PageInfo<ArticleVo>
     * @Author: jian.ye
     * @Date: 2019/11/19 14:19
     */
    PageInfo<ArticleVo> getArticlePageList(ArticleCondition condition);

    /**
     * 发布或取消发布文章
     * @param condition
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/19 14:20
     */
    boolean publishArticle(ArticleCondition condition, Integer currentUserId);

    /**
     * 删除文章信息
     * @param articleId
     * @param currentUserId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/11/19 14:19
     */
    boolean deleteArticle(Integer articleId, Integer currentUserId);

}