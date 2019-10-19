package com.zhuhong.inspection.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.mapper.CriterionMapper;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.service.CriterionService;
import com.zhuhong.inspection.utils.DateUtil;
import com.zhuhong.inspection.vo.CriterionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 标准数据业务实现层
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:24
 */
@Service
public class CriterionServiceImpl implements CriterionService {

    @Autowired
    private CriterionMapper criterionMapper;

    @Override
    public boolean saveCriterion(Criterion criterion, Integer currentUserId) {
        criterion.setUpdateId(currentUserId);
        criterion.setUpdateTime(DateUtil.getCurrentDate());
        if (criterion.getImplementDate() != null) {
            criterion.setCreateId(currentUserId);
            criterion.setCreateTime(DateUtil.getCurrentDate());
            return criterionMapper.insertSelective(criterion) > 0;
        } else {
            return criterionMapper.updateByPrimaryKey(criterion) > 0;
        }
    }

    @Override
    public PageInfo<CriterionVo> getCriterionPageList(CriterionCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<CriterionVo> list = criterionMapper.getCriterionListByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public boolean judgeCriterionNameIsExist(String name, Integer criterionId) {
        Example example = new Example(Criterion.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        if (criterionId != 0 ) {
            criteria.andNotEqualTo("id", criterionId);
        }
        if (criterionMapper.selectByExample(example).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCriterion(Integer criterionId, Integer currentUserId) {
        Criterion criterion = new Criterion();
        criterion.setId(criterionId);
        criterion.setUpdateId(currentUserId);
        criterion.setUpdateTime(DateUtil.getCurrentDate());
        criterion.setUsable(Criterion.ENABLE_0);
        return criterionMapper.updateByPrimaryKeySelective(criterion) > 0;
    }

}
