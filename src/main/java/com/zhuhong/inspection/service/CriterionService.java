package com.zhuhong.inspection.service;

import com.zhuhong.inspection.model.Criterion;

/**
 * 标准数据业务接口
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:23
 */
public interface CriterionService {

    /**
     * 保存标准数据
     *
     * @param criterion
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/19 10:25
     */
    boolean saveCriterion(Criterion criterion, Integer currentUserId);

}
