package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.condition.CriterionCondition;
import com.zhuhong.inspection.model.Criterion;
import com.zhuhong.inspection.vo.CriterionVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 标准数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:22
 */
@Repository
public interface CriterionMapper extends Mapper<Criterion> {

    /**
     * 根据条件获取标准数据集合
     * @param condition
     * @return List<CriterionVo>
     * @Author: jian.ye
     * @Date: 2019/10/19 16:08
     */
    List<CriterionVo> getCriterionListByCondition(CriterionCondition condition);

    /**
     * 根据ID获取标准数据
     * @param condition
     * @return CriterionVo
     * @Author: jian.ye
     * @Date: 2019/10/31 16:17
     */
    CriterionVo getCriterionById(CriterionCondition condition);

}
