package com.zhuhong.inspection.mapper;

import com.zhuhong.inspection.model.Law;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * 法规数据访问层
 *
 * @Author: jian.ye
 * @Date: 2019/10/22 23:09
 */
@Repository
public interface LawMapper extends Mapper<Law> {
}
