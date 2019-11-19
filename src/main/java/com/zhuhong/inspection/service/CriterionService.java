package com.zhuhong.inspection.service;

import com.github.pagehelper.PageInfo;
import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.dto.CriterionDto;
import com.zhuhong.inspection.vo.CriterionVo;

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
     * @param criterionDto
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/19 10:25
     */
    boolean saveCriterion(CriterionDto criterionDto, Integer currentUserId);

    /**
     * 根据条件获取标准数据信息
     * @param condition
     * @return PageInfo<CriterionVo>
     * @Author: jian.ye
     * @Date: 2019/10/19 12:03
     */
    PageInfo<CriterionVo> getCriterionPageList(CriterionCondition condition);

    /**
     * 判断标准数据名称是否存在
     * @param name
     * @param criterionId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/19 14:40
     */
    boolean judgeCriterionNameIsExist(String name, Integer criterionId);

    /**
     * 删除标准数据
     * @param criterionId
     * @return boolean
     * @Author: jian.ye
     * @Date: 2019/10/19 16:39
     */
    boolean deleteCriterion(Integer criterionId, Integer currentUserId);

    /**
     * 根据ID获取标准数据
     * @param id
     * @return CriterionVo
     * @Author: jian.ye
     * @Date: 2019/10/31 16:17
     */
    CriterionVo getCriterionById(Integer id);

}
