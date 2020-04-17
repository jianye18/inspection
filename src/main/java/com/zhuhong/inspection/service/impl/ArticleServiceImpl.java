package com.zhuhong.inspection.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.base.Constants;
import com.zhuhong.inspection.condition.ArticleCondition;
import com.zhuhong.inspection.dto.ArticleDto;
import com.zhuhong.inspection.mapper.ArticleMapper;
import com.zhuhong.inspection.model.Annex;
import com.zhuhong.inspection.model.Article;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.service.AnnexService;
import com.zhuhong.inspection.service.ArticleService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    private AnnexService annexService;
    @Value("${http_url}")
    private String httpUrl;

    @Override
    public boolean saveArticle(ArticleDto articleDto, Integer currentUserId) {
        boolean flag = false;
        Date currentDate = DateUtil.getCurrentDate();
        List<Annex> annexList = articleDto.getAnnexList();
        Article article = JSONObject.parseObject(JSONObject.toJSONString(articleDto), Article.class);
        article.setUpdateId(currentUserId);
        article.setUpdateTime(currentDate);
        if (article.getId() == null) {
            article.setCreateId(currentUserId);
            article.setCreateTime(currentDate);
            int r = articleMapper.insertSelective(article);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(false, annexList, article.getId(), Constants.BASE_TYPE_5);
            }
        } else {
            int r = articleMapper.updateByPrimaryKeySelective(article);
            if (r > 0) {
                flag = true;
                annexService.saveAnnex(true, annexList, article.getId(), Constants.BASE_TYPE_5);
            }
        }
        return flag;
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        ArticleVo articleVo = articleMapper.getArticleById(articleId);
        articleVo.setContent(articleVo.getContent().replaceAll("src=\\\"", "src=\\\"" + httpUrl));
        List<Annex> annexList = annexService.getAnnexList(articleVo.getId(), Constants.BASE_TYPE_5);
        if (annexList.size() > 0) {
            articleVo.setAnnexList(annexList);
        }
        return articleVo;
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
    public List<ArticleVo> getHomeArticleList(String orderName, Integer limit) {
        return articleMapper.getArticleListWithOrder(orderName, limit);
    }

    @Override
    public int getArticleTotalCount(Integer isNew) {
        return articleMapper.getArticleCount(isNew);
    }

    @Override
    public void addArticleReadCount(Integer articleId) {
        articleMapper.addArticleReadCount(articleId);
    }
}
