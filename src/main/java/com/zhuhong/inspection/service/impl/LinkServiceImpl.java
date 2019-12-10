package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.LinkCondition;
import com.zhuhong.inspection.mapper.LinkMapper;
import com.zhuhong.inspection.model.Link;
import com.zhuhong.inspection.service.LinkService;
import com.zhuhong.inspection.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author 叶剑
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public boolean saveLink(Link link, Integer currentUserId) {
        Date current = DateUtil.getCurrentDate();
        link.setUpdateId(currentUserId);
        link.setUpdateTime(current);
        if (link.getId() == null) {
            link.setCreateId(currentUserId);
            link.setCreateTime(current);
            return linkMapper.insertSelective(link) > 0;
        } else {
            return linkMapper.updateByPrimaryKey(link) > 0;
        }
    }

    @Override
    public PageInfo<Link> getLinkPageList(LinkCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        Example example = new Example(Link.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("usable", Link.ENABLE_1);
        if (StringUtils.isNotEmpty(condition.getSearchPhrase())) {
            criteria.andLike("name", condition.getSearchPhrase());
        }
        example.setOrderByClause("update_time desc");
        List<Link> list = linkMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    @Override
    public boolean deleteLink(Integer linkId, Integer currentUserId) {
        Link link = new Link();
        link.setId(linkId);
        link.setUpdateId(currentUserId);
        link.setUpdateTime(DateUtil.getCurrentDate());
        link.setUsable(Link.ENABLE_0);
        return linkMapper.updateByPrimaryKeySelective(link) > 0;
    }

    @Override
    public List<Link> getLinkViewList() {
        Example example = new Example(Link.class);
        example.createCriteria().andEqualTo("usable", Link.ENABLE_1).andEqualTo("isView", 1);
        example.setOrderByClause("sort asc");
        return linkMapper.selectByExample(example);
    }
}
