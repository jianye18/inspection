package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.mapper.ArticleMapper;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.service.ArticleService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 叶剑
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean saveArticle(Article article, Integer currentUserId) {
        Date currentDate = DateUtil.getCurrentDate();
        article.setUpdateId(currentUserId);
        article.setUpdateTime(currentDate);
        if (article.getId() == null) {
            article.setCreateId(currentUserId);
            article.setCreateTime(currentDate);
            return articleMapper.insertSelective(article) > 0;
        } else {
            return articleMapper.updateByPrimaryKeySelective(article) > 0;
        }
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        return articleMapper.getArticleById(articleId);
    }

    @Override
    public PageInfo<ArticleVo> getArticlePageList(ArticleCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<ArticleVo> list = articleMapper.getArticleListByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public boolean publishArticle(ArticleCondition condition, Integer currentUserId) {
        Date current = DateUtil.getCurrentDate();
        Article article = new Article();
        article.setId(condition.getArticleId());
        article.setIsPublish(condition.getIsPublish());
        if (condition.getIsPublish() == 1) {
            article.setPublishTime(current);
        }
        article.setUpdateId(currentUserId);
        article.setUpdateTime(current);
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    @Override
    public boolean deleteArticle(Integer articleId, Integer currentUserId) {
        Article article = new Article();
        article.setId(articleId);
        article.setUpdateId(currentUserId);
        article.setUpdateTime(DateUtil.getCurrentDate());
        article.setUsable(Article.ENABLE_0);
        return articleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    @Override
    public List<Article> getHomeArticleList(String orderName, Integer limit) {
        return articleMapper.getArticleListWithOrder(orderName, limit);
    }
}
