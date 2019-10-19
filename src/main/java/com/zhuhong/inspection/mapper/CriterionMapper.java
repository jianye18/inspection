package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.Criterion;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 标准数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/19 10:22
 */
@Repository
public interface CriterionMapper extends Mapper<Criterion> {
}
