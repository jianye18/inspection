package com.zhuhong.inspection.service.impl;

import com.zhuhong.inspection.mapper.CriterionMapper;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.service.CriterionService;
import com.zhuhong.inspection.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return criterionMapper.insertSelective(criterion) > 0;
        } else {
            criterion.setCreateId(currentUserId);
            criterion.setCreateTime(DateUtil.getCurrentDate());
            return criterionMapper.updateByPrimaryKey(criterion) > 0;
        }
    }

}
