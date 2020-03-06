package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.vo.ArticleVo;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取首页最新和最热文章信息
     * @param orderName
     * @param limit
     * @return List<Article>
     * @Author: jian.ye
     * @Date: 2019/11/29 14:35
     */
    List<ArticleVo> getHomeArticleList(String orderName, Integer limit);

    /**
     * 获取文章总数
     * @param isNew
     * @return int
     * @Author: jian.ye
     * @Date: 2019/12/13 14:59
     */
    int getArticleTotalCount(Integer isNew);

    /**
     * 增加文章的阅读量
     * @param articleId
     * @Author: jian.ye
     * @Date: 2019/12/13 16:47
     */
    void addArticleReadCount(Integer articleId);

}
